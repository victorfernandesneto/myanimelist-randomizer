package com.victorfernandesneto.myanimelistrandomizer.main;

import com.google.gson.JsonSyntaxException;
import com.victorfernandesneto.myanimelistrandomizer.models.ApiQuery;
import com.victorfernandesneto.myanimelistrandomizer.request.RequestAnimeList;
import com.victorfernandesneto.myanimelistrandomizer.util.ParseJson;
import com.victorfernandesneto.myanimelistrandomizer.util.RandomizeNumber;
import com.victorfernandesneto.myanimelistrandomizer.util.ValidateJson;

import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Your username -> ");
        var username = sc.next();

        boolean validJson = false;
        String nodeJson;
        String json = null;
        int ceil = 1000;
        int randomNumber = 0;
        while (!validJson) {
            randomNumber = RandomizeNumber.randomizeNumber(ceil);
            ceil = randomNumber;
            ApiQuery query = new ApiQuery(username, randomNumber);
            json = RequestAnimeList.getRandomAnime(query).body();
            try {
                validJson = ValidateJson.validateJson(json);
            } catch (JsonSyntaxException e) {
                System.err.println("Error: Invalid JSON received. Trying again...");
            } catch (Exception e) { // Handle other potential exceptions
                e.printStackTrace();
                System.err.println("An unexpected error occurred. Trying again...");
            }
        }
        nodeJson = ParseJson.parseJson(json);
        System.out.println(nodeJson);
    }
}
