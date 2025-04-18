package com.rbittencourt.springailab;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DadJokeController {

    @Autowired
    private ChatClient chatClient;

    @GetMapping("/dad-jokes")
    public String jokes() {
        var system = new SystemMessage("Your primary function is to tell Dad Jokes. If someone asks you for any other type of joke please tell them you only know Dad Jokes");
        var user = new UserMessage("Tell me a serious joke about the universe");
        Prompt prompt = new Prompt(List.of(system, user));

        return chatClient.prompt(prompt).call().content();
    }
}
