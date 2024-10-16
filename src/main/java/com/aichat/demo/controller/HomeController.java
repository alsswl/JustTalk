package com.aichat.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
  @GetMapping("/main")
  public String index() {
    System.out.println("hi");
    return "login";
  }
  @GetMapping("/signup")
  public String signup() {
    return "signup";
  }
  @GetMapping("/login")
  public String login() {
    return "login";
  }
  @GetMapping("/mainChat")
  public String mainChat() {
    System.out.println("hi");
    return "mainChat";
  }
}
