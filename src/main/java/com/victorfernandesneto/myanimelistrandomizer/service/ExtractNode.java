package com.victorfernandesneto.myanimelistrandomizer.service;

import com.victorfernandesneto.myanimelistrandomizer.model.Anime;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExtractNode {
    public static Anime extractNode(List<Map<String, Object>> dataList){
        Map<String, Object> animeMap = dataList.get(0);

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

        Anime anime = new Anime(id, title, mainPicture);
        return anime;
    }
}