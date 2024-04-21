package com.victorfernandesneto.malrandomizer.service;

import com.google.gson.Gson;
import com.victorfernandesneto.malrandomizer.title.TitleDTO;
import org.apache.coyote.Request;

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
    private static int ceil = 1000;
    private static Gson gson = new Gson();
    private static boolean validJson = false;



    private static int randomizeNumber(int ceil){
        Random rand = new Random();
        ceil = rand.nextInt(ceil);
        return ceil;
    }

    private static String createApiQuery(boolean isAnime, String username, int ceil){
        int randomNumber = randomizeNumber(ceil);
        String url = URL_BASE.replace("{username}", username).replace("{offset}", String.valueOf(randomNumber));
        if(!isAnime){
            url = url.replace("/animelist", "/mangalist");
        }
        return url;
    }

    public static TitleDTO getRandomAnime(String username, String clientId) throws IOException, InterruptedException {
        String apiURL = RequestService.createApiQuery(true, username, ceil);
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiURL))
                .header("X-MAL-CLIENT-ID", clientId)
                .build();

        String response = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        List<Map<String, Object>> dataList = extractResponse(response);
        if(validateResponse(dataList)){
            return formatJson(dataList);
        } else{
            return RequestService.getRandomAnime(username, clientId);
        }
    }

    public static TitleDTO getRandomManga(String username, String clientId) throws IOException, InterruptedException {
        String apiURL = RequestService.createApiQuery(false, username, ceil);
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiURL))
                .header("X-MAL-CLIENT-ID", clientId)
                .build();

        String response = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        List<Map<String, Object>> dataList = extractResponse(response);
        if(validateResponse(dataList)){
            return formatJson(dataList);
        } else{
            return RequestService.getRandomManga(username, clientId);
        }
    }

    public static List<Map<String, Object>> extractResponse(String body){
        Map<String, Object> responseMap = gson.fromJson(body, Map.class);
        List<Map<String, Object>> dataList = (List<Map<String, Object>>) responseMap.get("data");
        return dataList;
    }

    public static boolean validateResponse(List<Map<String, Object>> dataList){
        return !dataList.isEmpty();
    }

    public static TitleDTO formatJson(List<Map<String, Object>> response){
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