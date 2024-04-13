package com.victorfernandesneto.myanimelistrandomizer.util;

import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

public class ValidateJson {

    public static boolean validateJson(String json){
        Gson gson = new Gson();
        Map<String, Object> responseMap = gson.fromJson(json, Map.class);

        List<Map<String, Object>> dataList = (List<Map<String, Object>>) responseMap.get("data");
        return !dataList.isEmpty();
    }
}
