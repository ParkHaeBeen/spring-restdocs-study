package com.example.springrestdocstest.exception;

import lombok.Getter;

@Getter
public class CustomeException extends RuntimeException{
  private String message;

  public CustomeException(String message) {
    this.message = message;
  }
}
