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
  private final ReportService reportService;

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
    String memberContent = member.getContent1();
    String aiResponse = openAiService.getResponseFromWogigi(userMessage,memberContent);
    memberContent = memberContent + "\n사용자:" + userMessage + "\n우끼끼:" + aiResponse;

    if (memberContent.length() > 500) {
      String report = reportService.makeReport(member,"우끼끼",memberContent);
      memberContent = "";
      aiResponse = "아쉽지만 내 기억력은 여기까지야. 내가 너와의 대화가 어땠는지 이야기해줄게!\n" + report +"\n내 리포트는 나의 리포트에서 다시 한번 확인할 수 있어 즐거웠어 다음에 또 만나!";
      System.out.println(aiResponse);
    }

    member.setContent1(memberContent);
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
    String memberContent = member.getContent2();
    String aiResponse = openAiService.getResponseFromAung(userMessage,memberContent);
    memberContent = memberContent + "\n사용자:" + userMessage + "\n아웅:" + aiResponse;

    if (memberContent.length() > 500) {
      String report = reportService.makeReport(member,"아웅",memberContent);
      memberContent = "";
      aiResponse = "아쉽지만 내 기억력은 여기까지야. 내가 너와의 대화가 어땠는지 이야기해줄게!\n" + report +"\n내 리포트는 나의 리포트에서 다시 한번 확인할 수 있어 즐거웠어 다음에 또 만나!";
      System.out.println(aiResponse);
    }

    member.setContent2(memberContent);
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
    String memberContent = member.getContent3();
    String aiResponse = openAiService.getResponseFromBuBu(userMessage,memberContent);
    memberContent = memberContent + "\n사용자:" + userMessage + "\n뿌뿌:" + aiResponse;

    if (memberContent.length() > 500) {
      String report = reportService.makeReport(member,"뿌뿌",memberContent);
      memberContent = "";
      aiResponse = "아쉽지만 내 기억력은 여기까지야. 내가 너와의 대화가 어땠는지 이야기해줄게!\n" + report +"\n내 리포트는 나의 리포트에서 다시 한번 확인할 수 있어 즐거웠어 다음에 또 만나!";
      System.out.println(aiResponse);
    }

    member.setContent3(memberContent);
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
    String memberContent = member.getContent4();
    String aiResponse = openAiService.getResponseFromCoCo(userMessage,memberContent);
    memberContent = memberContent + userMessage + aiResponse;

    if (memberContent.length() > 5000) {
      memberContent = memberContent.substring(memberContent.length() - 5000);
    }

    member.setContent4(memberContent);
    memberRepository.save(member);

    ChatResponse.ChatMessageDTO aiMessage = new ChatResponse.ChatMessageDTO(aiResponse);
    messagingTemplate.convertAndSend("/topic/messages", aiMessage);
  }

}
