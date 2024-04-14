package com.victorfernandesneto.myanimelistrandomizer.request;

import com.victorfernandesneto.myanimelistrandomizer.models.ApiQuery;
import com.victorfernandesneto.myanimelistrandomizer.util.CallResource;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class RequestAnimeList {
    public static HttpResponse<String> getRandomAnime(ApiQuery query) throws IOException, InterruptedException {
        String apiURL = query.toString();
        String clientId = CallResource.getResource("client.id");

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiURL))
                .header("X-MAL-CLIENT-ID", clientId)
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
