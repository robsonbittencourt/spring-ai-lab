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

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/olympics")
public class ContextController {

    @Autowired
    private ChatClient chatClient;

    @Value("classpath:/prompts/olympic-sports.st")
    private Resource olympicSportsPrompt;

    @Value("classpath:/docs/olympic-sports.txt")
    private Resource olympicSportsContext;

    @GetMapping("/2024")
    public String get2024OlympicsSports(
            @RequestParam(value = "message", defaultValue = "What sports are being included in the 2024 Summer Olympics?") String message,
            @RequestParam(value = "stuffit", defaultValue = "false") boolean stuffit
    ) {
        PromptTemplate template = new PromptTemplate(olympicSportsPrompt);
        Map<String, Object> map = new HashMap<>();
        map.put("question", message);

        if (stuffit) {
            map.put("context", olympicSportsContext);
        } else {
            map.put("context", "");
        }

        Prompt prompt = template.create(map);
        return chatClient.prompt(prompt).call().content();
    }
}
