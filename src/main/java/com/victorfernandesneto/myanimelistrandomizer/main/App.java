package com.victorfernandesneto.myanimelistrandomizer.main;

import com.google.gson.JsonSyntaxException;
import com.victorfernandesneto.myanimelistrandomizer.model.ApiQuery;
import com.victorfernandesneto.myanimelistrandomizer.model.Anime;
import com.victorfernandesneto.myanimelistrandomizer.service.RequestAnimeList;
import com.victorfernandesneto.myanimelistrandomizer.service.*;


import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Your username -> ");
        var username = sc.next();

        boolean validJson = false;
        String json;
        String requestBody;
        int ceil = 1000;
        int randomNumber = 0;
        List<Map<String, Object>> dataList = List.of();

        while (!validJson) {
            try {
                randomNumber = RandomizeNumber.randomizeNumber(ceil);
                ceil = randomNumber;
            } catch (IllegalArgumentException e) {
                System.out.println("Anime list is empty or profile is set to private. Please add an anime or make your profile public again.");
                break;
            }
            ApiQuery query = new ApiQuery(username, randomNumber);
            requestBody = RequestAnimeList.getRandomAnime(query).body();
            dataList = ExtractData.extractData(requestBody);
            try {
                validJson = ValidateData.validateData(dataList);
            } catch (JsonSyntaxException e) {
                System.err.println("Error: Invalid JSON received. Trying again...");
            } catch (Exception e) { // Handle other potential exceptions
                e.printStackTrace();
                System.err.println("An unexpected error occurred. Trying again...");
            }
        }
        Anime animeClass = ExtractNode.extractNode(dataList);
        json = ParseJson.parseJson(animeClass);
        System.out.println(json);
    }
}
