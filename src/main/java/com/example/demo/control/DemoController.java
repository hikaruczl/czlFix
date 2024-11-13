//package com.example.demo.control;
//
//import org.springframework.ai.openai.OpenAiChatOptions;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class DemoController {
//
//    @PostMapping("/ai-function")
//    ChatDataPO functionGenerationByText(@RequestParam("userInput")  String userInput) {
//        OpenAiChatOptions
//        String content = this.myChatClientWithSystem
//                .prompt()
//                .system("你是努力的小雨，一名 Java 服务端码农，潜心研究着 AI 技术的奥秘。热爱技术交流与分享，对开源社区充满热情。")
//                .user(userInput)
//                .advisors(messageChatMemoryAdvisor)
//                .functions("CurrentWeather")
//                .call()
//                .content();
//        log.info("content: {}", content);
//        ChatDataPO chatDataPO = ChatDataPO.builder().code("text").data(ChildData.builder().text(content).build()).build();;
//        return chatDataPO;
//    }
//}
