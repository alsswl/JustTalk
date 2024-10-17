package com.aichat.demo.controller;

import com.aichat.demo.dto.request.ChatRequest;
import com.aichat.demo.service.ChatService;
import com.aichat.demo.service.JwtService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
public class ChatController {

  private final ChatService chatService;

  @PostMapping("/chat/{Id}")
  public void startChat(@PathVariable Integer Id){

  }

  @MessageMapping("/chat.sendMessage1")
  public void sendMessage1(@Payload ChatRequest.ChatMessageDTO chatMessage)
      throws JsonProcessingException {
    chatService.processMessage1(chatMessage);
  }
  @MessageMapping("/chat.sendMessage2")
  public void sendMessage2(@Payload ChatRequest.ChatMessageDTO chatMessage)
      throws JsonProcessingException {
    chatService.processMessage2(chatMessage);
  }
  @MessageMapping("/chat.sendMessage3")
  public void sendMessage3(@Payload ChatRequest.ChatMessageDTO chatMessage)
      throws JsonProcessingException {
    chatService.processMessage3(chatMessage);
  }
  @MessageMapping("/chat.sendMessage4")
  public void sendMessage4(@Payload ChatRequest.ChatMessageDTO chatMessage)
      throws JsonProcessingException {
    chatService.processMessage4(chatMessage);
  }
  @MessageMapping("/chat.addUser")
  public void addUser(@Payload ChatRequest.ChatMessageDTO chatMessage, SimpMessageHeaderAccessor headerAccessor) {
    headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
  }



}
