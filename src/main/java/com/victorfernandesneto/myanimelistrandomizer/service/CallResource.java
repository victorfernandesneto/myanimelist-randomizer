package com.victorfernandesneto.myanimelistrandomizer.service;

import java.util.Locale;
import java.util.ResourceBundle;

public class CallResource {
    public static String getResource(String resource){
        Locale currentLocale = Locale.getDefault();
        ResourceBundle bundle;
        try {
            bundle = ResourceBundle.getBundle("config", currentLocale);
        } catch (Exception e) {
            return "Error loading configuration file: " + e.getMessage();

        }
        return bundle.getString(resource);
    }
}
