package com.victorfernandesneto.myanimelistrandomizer.model;

public class ApiQuery {
    private String username;
    private final String url;

    public ApiQuery(String username, int randomNumber) {
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
