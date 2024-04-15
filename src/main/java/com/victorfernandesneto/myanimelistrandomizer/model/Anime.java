package com.victorfernandesneto.myanimelistrandomizer.model;

import java.util.Map;

public record Anime(int id, String title, Map<String, String> mainPicture) {
}
