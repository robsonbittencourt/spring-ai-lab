package com.rbittencourt.springailab.configuration;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {

    @Autowired
    private ChatClient.Builder builder;

    @Bean
    public ChatClient chatClient() {
        return builder.build();
    }
}
