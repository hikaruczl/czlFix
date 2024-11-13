package com.example.demo.control;

import org.springframework.ai.chat.client.ChatClient;

public class MyChatClientWithSystem {
    private final ChatClient chatClient;

    private MyChatClientWithSystem(ChatClient chatClient) {
        this.chatClient = chatClient;
    }
    public static class Builder {
        private ChatClient chatClient;

        public Builder client(ChatClient chatClient) {
            this.chatClient = chatClient;
            return this;
        }

        public MyChatClientWithSystem build() {
            return new MyChatClientWithSystem(chatClient);
        }
    }

    // 其他方法和属性可以根据需要添加
    public ChatClient client() {
        return chatClient;
    }
}