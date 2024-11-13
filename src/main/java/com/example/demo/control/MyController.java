package com.example.demo.control;

import com.example.demo.pojo.ChatDataPO;
import com.example.demo.pojo.ChildData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.ArrayList;

@Slf4j
@RestController
public class MyController {
    private final ChatClient myChatClientWithSystem;

//    private final ChatClient myChatClientWithParam;

    /**
     * 可以选择自动注入、也可以在方法内自定义，此客户端无系统文本
     */
    private final ChatClient chatClient;

    public MyController(ChatClient.Builder chatClientBuilder, MyChatClientWithSystem myChatClient) {
        this.chatClient = chatClientBuilder.build();
        this.myChatClientWithSystem = myChatClient.client();
//        this.myChatClientWithParam = myChatClientWithParam.client();
    }

    @PostMapping("/ai-Entity")
    ActorFilms generationByEntity(String text) {
        System.out.println("Entity: " + text);
        String content = this.myChatClientWithSystem.prompt()
                .user(text)
                .call()
                .content();
        log.info("content: {}", content);
        return new ActorFilms(content,new ArrayList<>());
//        ActorFilms actorFilms = chatClient.prompt()
//                .user(text)
//                .call()
//                .entity(ActorFilms.class);
//        return actorFilms;
    }

    @PostMapping("/ai")
    ChatDataPO generationByText(@RequestParam("userInput")  String userInput) {
        String content = this.myChatClientWithSystem.prompt()
                .user(userInput)
                .call()
                .content();
        log.info("content: {}", content);
        ChatDataPO chatDataPO = ChatDataPO.builder().code("text").data(ChildData.builder().text(content).build()).build();
        return chatDataPO;
    }
    @PostMapping(value = "/ai-stream",produces = MediaType.APPLICATION_OCTET_STREAM_VALUE + ";charset=UTF-8")
    Flux<String> generationByStream(@RequestParam("userInput") String userInput) {
        Flux<String> output = chatClient.prompt()
                .user(userInput)
                .stream()
                .content();
        log.info("content: {}", output);
        return output;
    }

    @PostMapping("/ai-function")
    ChatDataPO functionGenerationByText(@RequestParam("userInput")  String userInput) {
        String content = this.myChatClientWithSystem.prompt()
                .user(userInput)
                .functions("CurrentWeather")
                .call()
                .content();
        log.info("content: {}", content);
        ChatDataPO chatDataPO = ChatDataPO.builder().code("text").data(ChildData.builder().text(content).build()).build();;
        return chatDataPO;
    }
}
