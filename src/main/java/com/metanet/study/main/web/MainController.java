package com.metanet.study.main.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MainController {

  // Main Page
  @GetMapping
  public String main() {
    return "This is Auth Main Page";
  }

  // 공개 경로
  @GetMapping("/public/hello")
  public String hello() {
    return "Hello World (Public)";
  }
}
