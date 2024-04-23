package com.victorfernandesneto.malrandomizer.title;

import com.victorfernandesneto.malrandomizer.exception.UserNotFoundException;
import com.victorfernandesneto.malrandomizer.exception.UserPrivateOrEmptyListException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestHeader;

import java.io.IOException;

@RestController
public class TitleController {

    @Value("${client.id}")
    private String clientId;

    @GetMapping("/anime")
    public TitleDTO getRandomAnime(@RequestHeader(value = "username", required = true) String username) throws IOException, InterruptedException, UserPrivateOrEmptyListException, UserNotFoundException {
        return TitleService.getRandomAnime(username, clientId, 1000);
    }


    @GetMapping("/manga")
    public TitleDTO returnRandomManga(@RequestHeader(value = "username", required = true) String username) throws IOException, InterruptedException, UserPrivateOrEmptyListException, UserNotFoundException {
        return TitleService.getRandomManga(username, clientId, 1000);
    }
}
