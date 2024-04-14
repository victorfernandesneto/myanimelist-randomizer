package com.victorfernandesneto.myanimelistrandomizer.models;

import java.util.Map;

public record Anime(int id, String title, Map<String, String> mainPicture) {
}
