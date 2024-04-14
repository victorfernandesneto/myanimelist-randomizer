package com.victorfernandesneto.myanimelistrandomizer.util;

import java.util.List;
import java.util.Map;

public class ValidateData {

    public static boolean validateData(List<Map<String, Object>> dataList){
        return !dataList.isEmpty();
    }
}
