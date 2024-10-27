package com.aichat.demo.controller;

import com.aichat.demo.dto.request.MemberLoginRequest;
import com.aichat.demo.dto.request.MemberRequest;
import com.aichat.demo.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/members")
public class MemberController {
  private MemberService memberService;

  public MemberController(MemberService memberService) {
    this.memberService = memberService;
  }

  @PostMapping("/join")
  public ResponseEntity<Void> join(
      @RequestBody @Valid MemberRequest memberRequest
  ) {
    System.out.println(memberRequest);
    memberService.join(memberRequest);
    HttpHeaders headers = new HttpHeaders();
    headers.add("Message", "success");
    return ResponseEntity.ok().headers(headers).body(null);
  }

  @PostMapping("/login")
  public ResponseEntity<String> login(
      @RequestBody MemberLoginRequest memberRequest
  ) {
    String jwt = memberService.login(memberRequest);
    HttpHeaders headers = new HttpHeaders();
    headers.add("Authorization", jwt);
    headers.add("Message","login success");
    return ResponseEntity.ok().headers(headers).body(null);
  }

}

