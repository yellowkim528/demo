package com.kh.demo.web;

import lombok.Getter;

@Getter
public enum ResCode {
  OK("00"),fail("01"), ETC("99");

  private final String code;

  ResCode(String code) {
    this.code = code;

  }
}
