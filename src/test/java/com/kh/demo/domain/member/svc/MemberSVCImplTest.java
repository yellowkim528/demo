package com.kh.demo.domain.member.svc;

import com.kh.demo.domain.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;



@Slf4j
@SpringBootTest
@Transactional    // 테스트환경 : 실행후 rollback
class MemberSVCImplTest {

  @Autowired
  MemberSVC memberSVC;

  @Test
  void joinMember() {
    Member member = new Member();
    member.setEmail("user4@kh.com");
    member.setPasswd("user4");
    member.setNickname("사용자4");
    Long memberId = memberSVC.joinMember(member);
    log.info("memberId={}", memberId);
  }
}