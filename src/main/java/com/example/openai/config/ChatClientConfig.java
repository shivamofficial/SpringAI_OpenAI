package com.example.openai.config;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {

//    @Bean
//    public ChatClient openAiChatClient(OpenAiChatModel openAiChatModel) {
//        return ChatClient.create(openAiChatModel);
//    }
//
//    @Bean
//    public ChatClient ollamaChatClient(OllamaChatModel ollamaChatModel) {
//        ChatClient.Builder chatClientBuilder = ChatClient.builder(ollamaChatModel);
//        return chatClientBuilder.build();
//    }

    @Bean
    ChatClient chatClient(ChatModel chatModel) {
        return ChatClient.builder(chatModel).build();
    }
}
