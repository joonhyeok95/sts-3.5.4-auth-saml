package com.metanet.study.user.domain.service;

import java.util.Set;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {

  public Mono<Set<String>> getAuthority(ServerWebExchange exchange) {
    // 실제 로직 대신 항상 "user.list" 스코프만 반환하는 더미 구현
    return Mono.just(Set.of("user.list", "openid", "profile"));
  }
}
