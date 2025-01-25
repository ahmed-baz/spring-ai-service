package com.demo.ai.controller;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/chat")
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping("/joke")
    public String getJoke() {
        return chatClient.prompt("Tell me a joke").call().content();
    }

    @GetMapping("/film")
    public String getFilmsByActor(@RequestParam String actor, @RequestParam int count) {
        return chatClient.prompt()
                .user(user ->
                        user.text("Tell me some {count} films by {actor}")
                                .params(Map.of("count", count,
                                        "actor", actor)))
                .call()
                .content();
    }
}
