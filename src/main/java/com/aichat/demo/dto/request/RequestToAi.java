package com.aichat.demo.dto.request;

import java.util.List;

public record RequestToAi(
    String model,
    List<Message> messages
) {

}
