package com.aichat.demo.dto;

import java.util.List;

public record RequestToAi(
    String model,
    List<Message> messages
) {

}
