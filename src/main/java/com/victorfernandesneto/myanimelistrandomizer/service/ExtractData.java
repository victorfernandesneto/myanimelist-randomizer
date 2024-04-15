package com.victorfernandesneto.myanimelistrandomizer.service;

import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

public class ExtractData {
    public static List<Map<String, Object>> extractData(String json){
        Gson gson = new Gson();
        Map<String, Object> responseMap = gson.fromJson(json, Map.class);
        // next line is needed since the "correct" JSON ("node") is actually the "data" value
        List<Map<String, Object>> dataList = (List<Map<String, Object>>) responseMap.get("data");
        return dataList;
    }
}
