package com.metanet.study.user.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

  /*
   * 인증된 사용자 정보 : Oauth2 Bearer 토큰, vue/react 같은 클라이언트사이드렌더링 방식에서 활용
   */
  // @GetMapping("/me")
  // public Mono<Object> user(Authentication authentication) {
  // if (authentication == null)
  // return Mono.just("NO_AUTH");
  // Object principal = authentication.getPrincipal();
  // return Mono.just(principal);
  // }
  //
  // // security session 값을 추출해보기
  // @GetMapping("/sc")
  // public Mono<Object> getSecurityContext() {
  // // SecurityContext 전체를 그대로 Json으로 리턴
  // return ReactiveSecurityContextHolder.getContext().map(context -> {
  // Authentication auth = context.getAuthentication();
  // if (auth == null)
  // return "No authentication";
  // // 필요한 정보만 추출해 리턴하는 것이 안전
  // return new SecurityInfoDto(auth.getName(), auth.getAuthorities().toString(),
  // auth.isAuthenticated(), auth.getPrincipal());
  // });
  // }

  // @GetMapping("/token")
  // public Mono<Map<String, String>> getAccessToken(
  // @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient) {
  // String accessToken = authorizedClient.getAccessToken() != null
  // ? authorizedClient.getAccessToken().getTokenValue()
  // : "없음";
  //
  // String refreshToken = authorizedClient.getRefreshToken() != null
  // ? authorizedClient.getRefreshToken().getTokenValue()
  // : "없음";
  //
  // Map<String, String> tokenMap =
  // Map.of("access_token", accessToken, "refresh_token", refreshToken);
  //
  // return Mono.just(tokenMap);
  // }
}
