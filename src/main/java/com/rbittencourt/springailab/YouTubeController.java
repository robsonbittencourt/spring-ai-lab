package com.rbittencourt.springailab;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/youtube")
public class YouTubeController {

    @Autowired
    private ChatClient chatClient;

    @Value("classpath:/prompts/youtube.st")
    private Resource youtubePrompt;

    @GetMapping("/popular")
    public String findPopularYouTuberByGenre(@RequestParam(value = "genre", defaultValue = "tech") String genre) {
        PromptTemplate promptTemplate = new PromptTemplate(youtubePrompt);
        Prompt prompt = promptTemplate.create(Map.of("genre", genre));

        return chatClient.prompt(prompt).call().content();
    }
}
