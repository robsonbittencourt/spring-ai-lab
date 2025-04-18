package com.rbittencourt.springailab;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimplePromptController {

    @Autowired
    private ChatClient chatClient;

    @GetMapping("")
    public String simple() {
        return chatClient.prompt(new Prompt("Tell me a dad joke")).call().content();
    }
}
