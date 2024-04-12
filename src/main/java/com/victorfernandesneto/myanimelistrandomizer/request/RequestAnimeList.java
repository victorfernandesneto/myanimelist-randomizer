package com.victorfernandesneto.myanimelistrandomizer.request;

import com.victorfernandesneto.myanimelistrandomizer.util.CallResource;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;


public class RequestAnimeList {
    public static HttpResponse<String> getRandomAnime(String username) throws IOException, InterruptedException {
        Random rand = new Random();
        String apiURL = CallResource.getResource("api.url");
        String clientId = CallResource.getResource("client.id");
        int randomNumber = rand.nextInt(40);
        apiURL = apiURL.replace("{username}", username).replace("{offset}", String.valueOf(randomNumber));

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiURL))
                .header("X-MAL-CLIENT-ID", clientId)
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
