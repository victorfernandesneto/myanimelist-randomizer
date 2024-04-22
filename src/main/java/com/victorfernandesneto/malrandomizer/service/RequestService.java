package com.victorfernandesneto.malrandomizer.service;

import com.google.gson.Gson;
import com.victorfernandesneto.malrandomizer.exception.UserNotFoundException;
import com.victorfernandesneto.malrandomizer.exception.UserPrivateOrEmptyListException;
import com.victorfernandesneto.malrandomizer.title.TitleDTO;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RequestService {
    private static final String URL_BASE = "https://api.myanimelist.net/v2/users/{username}/animelist?limit=1&offset={offset}";
    private static final Gson gson = new Gson();


    private static int randomizeNumber(int ceil) throws UserPrivateOrEmptyListException {
        Random rand = new Random();
        try {
            return rand.nextInt(ceil);
        } catch (IllegalArgumentException e) {
            throw new UserPrivateOrEmptyListException("User profile is either private or the user's list is empty.");
        }
    }

    private static String createApiQuery(boolean isAnime, String username, int randomNumber) {
        String url = URL_BASE.replace("{username}", username).replace("{offset}", String.valueOf(randomNumber));
        if (!isAnime) {
            url = url.replace("/animelist", "/mangalist");
        }
        return url;
    }

    public static TitleDTO getRandomAnime(String username, String clientId, int ceil) throws IOException, InterruptedException, UserPrivateOrEmptyListException, UserNotFoundException {
        int randomNumber = randomizeNumber(ceil);
        String apiURL = RequestService.createApiQuery(true, username, randomNumber);
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiURL))
                .header("X-MAL-CLIENT-ID", clientId)
                .build();

        String response = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        List<Map<String, Object>> dataList = extractResponse(response);
        if (validateResponse(dataList)) {
            return formatJson(dataList);
        } else {
            return RequestService.getRandomAnime(username, clientId, randomNumber); // now randomNumber becomes the new "ceil"
        }
    }

    public static TitleDTO getRandomManga(String username, String clientId, int ceil) throws IOException, InterruptedException, UserPrivateOrEmptyListException, UserNotFoundException {
        int randomNumber = randomizeNumber(ceil);
        String apiURL = RequestService.createApiQuery(false, username, randomNumber);
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiURL))
                .header("X-MAL-CLIENT-ID", clientId)
                .build();

        String response = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        List<Map<String, Object>> dataList = extractResponse(response);
        if (validateResponse(dataList)) {
            return formatJson(dataList);
        } else {
            return RequestService.getRandomManga(username, clientId, randomNumber);
        }
    }

    public static List<Map<String, Object>> extractResponse(String body) {
        Map<String, Object> responseMap = gson.fromJson(body, Map.class);
        List<Map<String, Object>> dataList = (List<Map<String, Object>>) responseMap.get("data");
        return dataList;
    }

    public static boolean validateResponse(List<Map<String, Object>> dataList) throws UserNotFoundException {
        try {
            return !dataList.isEmpty();
        } catch (NullPointerException e) {
            throw new UserNotFoundException("User not found.");
        }
    }

    public static TitleDTO formatJson(List<Map<String, Object>> response) {
        Map<String, Object> animeMap = response.get(0);

        Map<String, Object> node = (Map<String, Object>) animeMap.get("node");
        // returning this would be fine, but I want the JSON response to be organized
        Double d = (double) node.get("id");
        int id = d.intValue();
        String title = (String) node.get("title");
        Map<String, Object> mainPictureMap = (Map<String, Object>) node.get("main_picture");
        Map<String, String> mainPicture = new HashMap<>();

        if (mainPictureMap != null) {
            String mediumUrl = (String) mainPictureMap.get("medium");
            mainPicture.put("medium", mediumUrl);
            String largeUrl = (String) mainPictureMap.get("large");
            mainPicture.put("large", largeUrl);
        }

        TitleDTO titleObj = new TitleDTO(id, title, mainPicture);
        return titleObj;
    }
}