package com.kh.demo.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

@Slf4j
public class RegExTest {

  @Test
  void t1() {
    String pattern = "[0-9]{3,10}";
    String str = "12345";

    if (Pattern.matches(pattern, str)) {
      log.info("패턴일치");
    } else {
      log.info("패턴불일치");
    }

  }

  void t2() {
    if (Pattern.matches("\\d{3}-\\{4}-\\d{4}", "010-1234-5678")) {
      log.info("패턴일치");
    } else {
      log.info("패턴불일치");
    }
  }

  void t3() {
    if (Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", "sky6156@naver.com")) {
      log.info("패턴일치");
    } else {
      log.info("패턴불일치");
    }
  }
}
