package com.victorfernandesneto.myanimelistrandomizer.service;

import com.google.gson.Gson;
import com.victorfernandesneto.myanimelistrandomizer.model.Anime;

public class ParseJson {
    public static String parseJson(Anime anime) {
        Gson gson = new Gson();
        String nodeJson = gson.toJson(anime);
        return nodeJson;
    }
}
