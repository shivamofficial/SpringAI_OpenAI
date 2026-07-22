package com.example.openai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChatOptionsController {

    private final ChatClient chatClient;


    public ChatOptionsController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

@GetMapping("/chat")
    private String chat(@RequestParam String question)
    {

        return chatClient.prompt().user(question).options(OpenAiChatOptions.builder().temperature(0.0).maxTokens(100).topP(0.5).topK(1))
                .call().content();
    }

    @GetMapping("/response")
    public ChatResponse response(@RequestParam String question) {

        return chatClient.prompt()
                .user(question)
                .call()
                .chatResponse();

      // System.out.println(response);
    }

}
