package dev_stuff.intellijapps.ChatResponses.controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class StreamController {
    private final ChatClient chatClient;
    @Value("classpath:/prompts/restaurantList.st")
    private Resource restaurantListPromptResource;

    public StreamController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }


    // At times response from the LLM might take a lot more time, so a frontend guy wont know if
    // something is happening. So, HOW TO SEND TEXT AS IT COMES TO US?
    @GetMapping("/stream")
    public Flux<String> stream(){
        return chatClient.prompt()
                .user(restaurantListPromptResource)
                .stream()
                .content();

    }
}
