package com.aichat.demo.infrastructure;


import static org.springframework.http.MediaType.APPLICATION_JSON;

import com.aichat.demo.dto.request.Message;
import com.aichat.demo.dto.request.RequestToAi;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class OpenAiService {

  private final String chatGptUrl = "https://api.openai.com/v1/chat/completions";
  private final RestTemplate restTemplate;
  private final ObjectMapper objectMapper;

  @Value("${chatgpt.api-key}")
  private String gptKey;

  public OpenAiService(RestTemplateBuilder restTemplateBuilder, ObjectMapper objectMapper) {
    this.restTemplate = restTemplateBuilder.build();
    this.objectMapper = objectMapper;
  }

  public String getResponseFromCoCo(String message,String allContent)
      throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    Message message1 = new Message("user", message);
    Message message2 = new Message("system","너는 정말 공감을 잘해주는 상담사 꼬꼬야. 이건 채팅서비스니까 두줄 이하로 답장해야하고, assistant에 넣어둔 내용은 이전 대화내용이니까 꼭 참고해" );
    Message message3 = new Message("assistant", allContent);

    List<Message> messageList = new LinkedList<>();
    messageList.add(message1);
    messageList.add(message2);
    messageList.add(message3);

    ResponseEntity<Object> feedBack = sandMessageToAi(messageList);
    String responseBody = objectMapper.writeValueAsString(feedBack.getBody());

    JsonNode rootNode = objectMapper.readTree(responseBody);
    String extractedContent = rootNode.path("choices").get(0).path("message").path("content")
        .asText();

    return extractedContent;

  }

  public String getResponseFromBuBu(String message,String allContent)
      throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    Message message1 = new Message("user", message);
    Message message2 = new Message("system","너는 궁금한게 많은 아기 코끼리 뿌뿌야. 상대가 인사를 하면 간단한 단어에 대한 질문을 해 예를들어 사과, 공책 등등 다양하게 자꾸 테스트하는데 똑같은거 나오면 안된다. 너는 사용자의 답변 이외의 지식이 많이 없어 그래서 너가 아는 지식을 말하면 안돼. 그리고 assistant에 넣어둔 내용은 이전 대화내용이니까 꼭 참고해서 관련 질문을 계속 해. 질문은 정답이 있는걸로, 상대에 대한 질문은 하지 마.이건 채팅서비스니까 두줄 이하로 대답해 " );
    Message message3 = new Message("assistant", allContent);

    List<Message> messageList = new LinkedList<>();
    messageList.add(message1);
    messageList.add(message2);
    messageList.add(message3);

    ResponseEntity<Object> feedBack = sandMessageToAi(messageList);
    String responseBody = objectMapper.writeValueAsString(feedBack.getBody());

    JsonNode rootNode = objectMapper.readTree(responseBody);
    String extractedContent = rootNode.path("choices").get(0).path("message").path("content")
        .asText();

    return extractedContent;

  }

  public String getResponseFromAung(String message,String allContent)
      throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    Message message1 = new Message("user", message);
    Message message2 = new Message("system","너는 아웅이야. 너는 오랜 친구와 대화한다는 컨셉의 챗봇이야. 그러니까 친근한 말투를 많이 써줬으면 좋겠고, 요즘 너의 근황을 많이 말해줘. 챗봇이니까 2줄 이내로 대답해주고, 그리고 assistant에 넣어둔 내용은 이전 대화내용이니까 꼭 참고해" );
    Message message3 = new Message("assistant", allContent);

    List<Message> messageList = new LinkedList<>();
    messageList.add(message1);
    messageList.add(message2);
    messageList.add(message3);

    ResponseEntity<Object> feedBack = sandMessageToAi(messageList);
    String responseBody = objectMapper.writeValueAsString(feedBack.getBody());

    JsonNode rootNode = objectMapper.readTree(responseBody);
    String extractedContent = rootNode.path("choices").get(0).path("message").path("content")
        .asText();

    return extractedContent;

  }

  public String getResponseFromWogigi(String message,String allContent)
      throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    Message message1 = new Message("user", message);
    Message message2 = new Message("system","너는 우끼끼고, 처음 본 사람과 대화하는 컨셉의 챗봇이야. 상대에게 질문하지 말고, 너의 이야기만 해.관심사, 취미 등등에 대해 말해주고, 상대의 질문에는 답도 해줘. 너가 질문하진 마. 챗봇이니까 2줄 이내로 대답해주고, 그리고 assistant에 넣어둔 내용은 이전 대화내용이니까 꼭 참고해" );
    Message message3 = new Message("assistant", allContent);

    List<Message> messageList = new LinkedList<>();
    messageList.add(message1);
    messageList.add(message2);
    messageList.add(message3);

    ResponseEntity<Object> feedBack = sandMessageToAi(messageList);
    String responseBody = objectMapper.writeValueAsString(feedBack.getBody());

    JsonNode rootNode = objectMapper.readTree(responseBody);
    String extractedContent = rootNode.path("choices").get(0).path("message").path("content")
        .asText();

    return extractedContent;

  }

//  public String getResponseFromAi(String content) throws JsonProcessingException {
//    ObjectMapper objectMapper = new ObjectMapper();
//    ResponseEntity<Object> feedBack = sandMessageToAi(content);
//    String responseBody = objectMapper.writeValueAsString(feedBack.getBody());
//
//    JsonNode rootNode = objectMapper.readTree(responseBody);
//    String extractedContent = rootNode.path("choices").get(0).path("message").path("content")
//        .asText();
//
//    return extractedContent;
//  }

  private ResponseEntity<Object> sandMessageToAi(List<Message> content)
      throws JsonProcessingException {
    RequestToAi diaryRequestToGpt = new RequestToAi("gpt-4o-mini", content);

    String jsonBody = objectMapper.writeValueAsString(diaryRequestToGpt);

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(APPLICATION_JSON);
    headers.add("Authorization", "Bearer " + gptKey);

    HttpEntity<String> requestEntity = new HttpEntity<>(jsonBody, headers);
    return restTemplate.exchange(chatGptUrl, HttpMethod.POST, requestEntity, Object.class);
  }



}
