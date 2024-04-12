package com.victorfernandesneto.myanimelistrandomizer.util;

import com.google.gson.Gson;
import com.victorfernandesneto.myanimelistrandomizer.models.Anime;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParseJson {
    public static String parseJson(String json) {
        String nodeJson;
        Gson gson = new Gson();
        Map responseMap = gson.fromJson(json, Map.class);

        List<Map<String, Object>> dataList = (List<Map<String, Object>>) responseMap.get("data");
        Map<String, Object> animeMap = dataList.get(0);

        Map<String, Object> node = (Map<String, Object>) animeMap.get("node");

        String title = (String) node.get("title");
        Map<String, Object> mainPictureMap = (Map<String, Object>) node.get("main_picture");
        Map<String, String> mainPicture = new HashMap<>();

        if (mainPictureMap != null) {
            String mediumUrl = (String) mainPictureMap.get("medium");
            mainPicture.put("medium", mediumUrl);
            String largeUrl = (String) mainPictureMap.get("large");
            mainPicture.put("large", largeUrl);
        }

        Anime anime = new Anime(title, mainPicture);
        nodeJson = gson.toJson(anime);
        return nodeJson;
    }
}
