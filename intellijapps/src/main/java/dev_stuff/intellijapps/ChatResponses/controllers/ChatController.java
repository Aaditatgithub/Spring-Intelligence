package dev_stuff.intellijapps.ChatResponses.controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class ChatController {

    private final ChatClient chatClient;
    @Value("classpath:/resources/dadJokes.st")
    private Resource jokePromptResource;

    public ChatController(ChatClient.Builder builder) {
        this.chatClient = builder
                .defaultSystem("You are a loud assistant that answers in all capital letters")
                .build();
    }

    @GetMapping("")
    public String chat(@RequestParam String message){
        return chatClient.prompt()
                .user(message)
                .call()
                .content();
    }

    @GetMapping("/jokes")
    public String jokes(@RequestParam String topic){
        return chatClient.prompt()
                .user(u -> u.text(jokePromptResource).param("topic",topic))
                .call()
                .content();
    }
}
