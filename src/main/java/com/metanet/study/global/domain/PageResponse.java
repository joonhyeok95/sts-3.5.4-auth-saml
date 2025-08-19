package com.metanet.study.global.domain;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageResponse<T> {
  private List<T> content;
  private int page;
  private int size;
  private long totalElements;
  private int totalPages;
  private boolean last;

  /*
   * 기본 생성자 feignclient 에서 Spring Data Page 를 생성자로 가져오면 에러남 Page<T> 는 인터페이스이며, 내부 구현체가 여러 가지 존재하므로
   * Jackson 이 정확한 타입을 모르기에 역직렬화 실패
   * 
   * Resolved [feign.codec.DecodeException: Type definition error: [simple type, class
   * com.metanet.study.global.domain.PageResponse]]
   */
  public PageResponse() {}

  // public PageResponse(Page<T> page) {
  // this.content = page.getContent();
  // this.page = page.getNumber();
  // this.size = page.getSize();
  // this.totalElements = page.getTotalElements();
  // this.totalPages = page.getTotalPages();
  // this.last = page.isLast();
  // }
}
