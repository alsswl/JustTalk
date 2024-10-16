package com.aichat.demo.dto.request;

import com.aichat.demo.dto.request.Message;
import java.util.List;

public record RequestToAi(
    String model,
    List<Message> messages
) {

}
