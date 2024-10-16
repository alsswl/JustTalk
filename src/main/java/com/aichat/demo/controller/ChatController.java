package com.aichat.demo.controller;

import com.aichat.demo.dto.ChatRequest;
import com.aichat.demo.service.ChatService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;

@RequiredArgsConstructor
@Controller
public class ChatController {

  private final ChatService chatService;

  @MessageMapping("/chat.sendMessage")
  public void sendMessage(@Payload ChatRequest.ChatMessageDTO chatMessage)
      throws JsonProcessingException {
    chatService.processMessage(chatMessage);
  }
  @MessageMapping("/chat.addUser")
  public void addUser(@Payload ChatRequest.ChatMessageDTO chatMessage, SimpMessageHeaderAccessor headerAccessor) {
    headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
  }
}
