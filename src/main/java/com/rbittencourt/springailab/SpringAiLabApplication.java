package com.rbittencourt.springailab;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringAiLabApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAiLabApplication.class, args);
	}

	// Enable to see a some fact about Google when the application starts
//	@Bean
	CommandLineRunner commandLineRunner(ChatClient.Builder builder) {
		return args -> {
			var client = builder.build();
			String response = client.prompt("Tell me an interesting fact about Google")
					.call()
					.content();

			System.out.println(response);
		};
	}

}
