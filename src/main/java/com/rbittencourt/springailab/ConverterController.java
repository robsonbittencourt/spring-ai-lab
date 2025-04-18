package com.rbittencourt.springailab;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.ai.converter.MapOutputConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ConverterController {

    @Autowired
    private ChatClient chatClient;

    @GetMapping("songs")
    public List<String> getSongsByArtist(@RequestParam(value = "artist", defaultValue = "Taylor Swift") String artist) {
        var message = """
            Please give me a list of top 10 songs for the artist {artist}. If you don't know the answer, just say "I don't know de answer"
            {format}
            """;

        ListOutputConverter converter = new ListOutputConverter(new DefaultConversionService());

        PromptTemplate promptTemplate = new PromptTemplate(message, Map.of("artist", artist, "format", converter.getFormat()));
        Prompt prompt = promptTemplate.create();
        return converter.convert(chatClient.prompt(prompt).call().content());
    }

    @GetMapping("/author/{author}")
    public Map<String, Object> getAuthorsSocialLinks(@PathVariable String author) {
        var message = """
            Generate a list of links for the author {author}. Include the authors name as the key and any social network links as the object.
            {format}
            """;

        MapOutputConverter converter = new MapOutputConverter();
        String format = converter.getFormat();

        PromptTemplate promptTemplate = new PromptTemplate(message, Map.of("author", author, "format", format));
        Prompt prompt = promptTemplate.create();

        return converter.convert(chatClient.prompt(prompt).call().content());
    }

    @GetMapping("by-author/{author}")
    public Author getBooksByAuthor(@PathVariable String author) {
        var message = """
            Generate a list of books written by the author {author}.
            If you aren't positive that a book belongs to this author please don't include it.
            {format}
            """;

        var converter = new BeanOutputConverter<>(Author.class);
        String format = converter.getFormat();

        PromptTemplate promptTemplate = new PromptTemplate(message, Map.of("author", author, "format", format));
        Prompt prompt = promptTemplate.create();

        return converter.convert(chatClient.prompt(prompt).call().content());
    }
}
