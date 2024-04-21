package com.victorfernandesneto.malrandomizer.title;

import java.util.Map;

public record TitleDTO(int id, String title, Map<String, String> mainPicture) {
}