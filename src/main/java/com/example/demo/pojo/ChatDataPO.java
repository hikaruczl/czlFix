package com.example.demo.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChatDataPO {
    private String code;
    private ChildData data;
}