package dev_stuff.intellijapps.ChatResponses.controllers;

import dev_stuff.intellijapps.ChatResponses.outputFormat.ActorFilms;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ActorController {
    private final ChatClient chatClient;

    public ActorController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping("/films-string")
    public String getActorFilms(){
        return chatClient.prompt()
                .user("Generate a filmography for shahrukh khan for year 2010")
                .call()
                .content();
    }

    // This would give a response as an entity (kinda like better O-AIR-M)
    @GetMapping("/films")
    public ActorFilms getActorFilmsAsAnEntity(){
        return chatClient.prompt()
                .user("Generate a filmography for shahrukh khan.")
                .call()
                .entity(ActorFilms.class);
    }

    @GetMapping("/films")
    public List<ActorFilms> getActorFilmsList(){
        return chatClient.prompt()
                .user("Generate a filmography for Robert Downey Jr, Chris Hemsworth.")
                .call()
                .entity(new ParameterizedTypeReference<>() {}); // new way to get a collection i see
    }
}
