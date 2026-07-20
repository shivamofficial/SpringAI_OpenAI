package com.example.openai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PromptTemplateController {

    private final ChatClient chatClient;

    public PromptTemplateController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @Value("classpath:promptTemplates/userPromptTemplate.st")
    private Resource templateResource;

    @GetMapping("/email")
    public String emailResponse(
            @RequestParam String customerName,
            @RequestParam String customerMessage) throws IOException {

        // Read template file
        String templateText = StreamUtils.copyToString(
                templateResource.getInputStream(),
                StandardCharsets.UTF_8);

        // Create PromptTemplate
        PromptTemplate promptTemplate = new PromptTemplate(templateText);

        // Replace placeholders
        Prompt prompt = promptTemplate.create(Map.of(
                "customerName", customerName,
                "customerMessage", customerMessage
        ));
System.out.println("prompt is :"+ prompt);
        // Send to LLM
        return chatClient.prompt(prompt)
                .call()
                .content();
    }

}