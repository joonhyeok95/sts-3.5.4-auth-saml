package com.metanet.study.user.domain.service.dto;

public class SecurityInfoDto {
  public final String username;
  public final String authorities;
  public final boolean authenticated;
  public final Object principal;

  public SecurityInfoDto(String username, String authorities, boolean authenticated,
      Object principal) {
    this.username = username;
    this.authorities = authorities;
    this.authenticated = authenticated;
    this.principal = principal;
  }
}
