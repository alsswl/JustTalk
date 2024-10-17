package com.aichat.demo.service;

import com.aichat.demo.dto.request.ChatRequest;
import com.aichat.demo.dto.response.ChatResponse;
import com.aichat.demo.entity.Member;
import com.aichat.demo.infrastructure.OpenAiService;
import com.aichat.demo.repository.MemberRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChatService {

  private final SimpMessagingTemplate messagingTemplate;
  private final OpenAiService openAiService;
  private final JwtService jwtService;
  private final MemberRepository memberRepository;

  public void processMessage1(ChatRequest.ChatMessageDTO requestDTO) throws JsonProcessingException {
    String userMessage = requestDTO.getContent();
    String token = requestDTO.getToken();
    String email = "";
    if (token != null && !token.isEmpty()) {
      email = jwtService.getMemberEmail(token);
      System.out.println(email);
    } else {
      throw new IllegalArgumentException("Token is missing");
    }

    Member member = memberRepository.findByEmail(email).get();
    String memberContent = member.getNowContent();
    String aiResponse = openAiService.getResponseFromWogigi(userMessage,memberContent);
    memberContent = memberContent + userMessage + aiResponse;

    if (memberContent.length() > 5000) {
      //리포트
      memberContent = memberContent.substring(memberContent.length() - 5000);
    }

    member.setNowContent(memberContent);
    memberRepository.save(member);

    ChatResponse.ChatMessageDTO aiMessage = new ChatResponse.ChatMessageDTO(aiResponse);
    messagingTemplate.convertAndSend("/topic/messages", aiMessage);
  }

  public void processMessage2(ChatRequest.ChatMessageDTO requestDTO) throws JsonProcessingException {
    String userMessage = requestDTO.getContent();
    String token = requestDTO.getToken();
    String email = "";
    if (token != null && !token.isEmpty()) {
      email = jwtService.getMemberEmail(token);
      System.out.println(email);
    } else {
      throw new IllegalArgumentException("Token is missing");
    }

    Member member = memberRepository.findByEmail(email).get();
    String memberContent = member.getNowContent();
    String aiResponse = openAiService.getResponseFromAung(userMessage,memberContent);
    memberContent = memberContent + userMessage + aiResponse;

    if (memberContent.length() > 5000) {
      //리포트
      memberContent = memberContent.substring(memberContent.length() - 5000);
    }

    member.setNowContent(memberContent);
    memberRepository.save(member);

    ChatResponse.ChatMessageDTO aiMessage = new ChatResponse.ChatMessageDTO(aiResponse);
    messagingTemplate.convertAndSend("/topic/messages", aiMessage);
  }

  public void processMessage3(ChatRequest.ChatMessageDTO requestDTO) throws JsonProcessingException {
    String userMessage = requestDTO.getContent();
    String token = requestDTO.getToken();
    String email = "";
    if (token != null && !token.isEmpty()) {
      email = jwtService.getMemberEmail(token);
      System.out.println(email);
    } else {
      throw new IllegalArgumentException("Token is missing");
    }

    Member member = memberRepository.findByEmail(email).get();
    String memberContent = member.getNowContent();
    String aiResponse = openAiService.getResponseFromBuBu(userMessage,memberContent);
    memberContent = memberContent + userMessage + aiResponse;

    if (memberContent.length() > 5000) {
      //리포트
      memberContent = memberContent.substring(memberContent.length() - 5000);
    }

    member.setNowContent(memberContent);
    memberRepository.save(member);

    ChatResponse.ChatMessageDTO aiMessage = new ChatResponse.ChatMessageDTO(aiResponse);
    messagingTemplate.convertAndSend("/topic/messages", aiMessage);
  }

  public void processMessage4(ChatRequest.ChatMessageDTO requestDTO) throws JsonProcessingException {
    String userMessage = requestDTO.getContent();
    String token = requestDTO.getToken();
    String email = "";
    if (token != null && !token.isEmpty()) {
      email = jwtService.getMemberEmail(token);
      System.out.println(email);
    } else {
      // 토큰이 없을 경우 처리
      throw new IllegalArgumentException("Token is missing");
    }

    Member member = memberRepository.findByEmail(email).get();
    String memberContent = member.getNowContent();
    String aiResponse = openAiService.getResponseFromCoCo(userMessage,memberContent);
    memberContent = memberContent + userMessage + aiResponse;

    if (memberContent.length() > 5000) {
      memberContent = memberContent.substring(memberContent.length() - 5000);
    }

    member.setNowContent(memberContent);
    memberRepository.save(member);

    ChatResponse.ChatMessageDTO aiMessage = new ChatResponse.ChatMessageDTO(aiResponse);
    messagingTemplate.convertAndSend("/topic/messages", aiMessage);
  }

}
