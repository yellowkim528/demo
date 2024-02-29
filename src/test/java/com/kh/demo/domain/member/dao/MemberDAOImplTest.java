package com.kh.demo.domain.member.dao;

import com.kh.demo.domain.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@Slf4j
@SpringBootTest
@Rollback
class MemberDAOImplTest {

  @Autowired
  private MemberDAO memberDAO;

  @Test
  void test(){
    log.info("memberDAO={}", memberDAO.getClass().getName());
  }

  @Test
  @DisplayName("회원가입")
  void insertMember() {
    Member member = new Member();
    member.setEmail("user3@kh.com");
    member.setPasswd("user3");
    member.setNickname("사용자3");
    Long memberId = memberDAO.insertMember(member);
    log.info("memberId={}", memberId);

  }

  @Test
  @DisplayName("이메일체크")
  void existEmail() {

    boolean exist = memberDAO.existMemberId("user1@kh.com");

    Assertions.assertThat(exist).isEqualTo(true);

  }

  @Test
  @DisplayName("회원조회")
  void findByEmailAndPasswd() {
    Optional<Member> optionalMember = memberDAO.findByEmailAndPasswd("user9@kh.com", "user9");
    // Then
    Assertions.assertThat(optionalMember).isEmpty();
//    Member findedMember = optionalMember.get();
//    assertEquals("user1@kh.com", findedMember.getEmail(), "Email should match");
//    assertEquals("user1", findedMember.getPasswd(), "Password should match");
  }
}