package com.metanet.study.main.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
public class ApiController {
  //
  //
  // /*
  // * 인증된 사용자 정보 : Oauth2 Bearer 토큰, vue/react 같은 클라이언트사이드렌더링 방식에서 활용
  // */
  // @GetMapping("/me")
  // public Mono<Object> user(Authentication authentication) {
  // if (authentication == null)
  // return Mono.just("NO_AUTH");
  // Object principal = authentication.getPrincipal();
  // return Mono.just(principal);
  // }
  //
  // // 인증정보 활용
  // @PreAuthorize("hasAuthority('SCOPE_user.list')") // Scope 기반 권한 체크
  // @GetMapping("/profile")
  // public Mono<String> profile(Authentication authentication) {
  // if (authentication.getPrincipal() instanceof Jwt jwt) {
  // // JWT Bearer 인증 시 JWT의 Sub claim 값이 name 이기 때문에 실제 Claim속 값을 추출해야 name을 볼 수 있다.
  // String username = jwt.getClaimAsString("preferred_username");
  // return Mono.just("Hello, " + username);
  // }
  // // OAuth2 Login(세션) 때는 OIDC 프로토콜 기반으로 토큰이 별개 관리 되어 name을 추출할 수 있다.
  // return Mono.just("Hello, " + authentication.getName());
  // }

  // @PreAuthorize("hasAuthority('SCOPE_profile') and hasRole('ADMIN')")
  // @PreAuthorize("hasAuthority('SCOPE_profile') or hasRole('ADMIN')")
  // @PreAuthorize("hasAuthority('ROLE_admin')
  // @PreAuthorize("hasRole('admin')") // ROLE_admin 권한이 있을 때만 허용
  @GetMapping("/admin")
  public String admin() {
    return "admin only";
  }
}
