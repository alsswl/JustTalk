package com.aichat.demo.service;

import com.aichat.demo.dto.request.ChatRequest;
import com.aichat.demo.dto.response.ChatResponse;
import com.aichat.demo.infrastructure.OpenAiService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChatService {

  private final SimpMessagingTemplate messagingTemplate;
  private final OpenAiService openAiService;

  public void processMessage(ChatRequest.ChatMessageDTO requestDTO) throws JsonProcessingException {
    String userMessage = requestDTO.getContent();
    String aiResponse = openAiService.getResponseFromAi(userMessage);

    ChatResponse.ChatMessageDTO aiMessage = new ChatResponse.ChatMessageDTO(aiResponse);
    messagingTemplate.convertAndSend("/topic/messages", aiMessage);
  }
}
