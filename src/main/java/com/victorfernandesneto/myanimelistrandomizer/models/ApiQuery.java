package com.victorfernandesneto.myanimelistrandomizer.models;

import java.util.Random;

public class ApiQuery {
    private String username;
    private final String url;

    public ApiQuery(String username) {
        Random rand = new Random();
        int randomNumber = rand.nextInt(1000);
        this.username = username;
        this.url = "https://api.myanimelist.net/v2/users/" + this.username + "/animelist?limit=1&offset={offset}"
                .replace("{offset}", String.valueOf(randomNumber));
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return this.url;
    }
}
