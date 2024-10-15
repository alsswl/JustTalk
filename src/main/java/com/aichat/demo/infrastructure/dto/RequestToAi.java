package com.aichat.demo.infrastructure.dto;

import com.aichat.demo.infrastructure.dto.Message;
import java.util.List;

public record RequestToAi(
    String model,
    List<Message> messages
) {

}
