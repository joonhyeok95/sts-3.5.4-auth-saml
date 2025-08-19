package com.metanet.study.global.exception;

public class ExternalServerException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public ExternalServerException(String msg) {
    super(msg);
  }

}
