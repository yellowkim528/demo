package com.kh.demo.domain.member.dao;

import com.kh.demo.domain.entity.Member;

import java.util.Optional;

//input output 고민하면서 작성
public interface MemberDAO {
  //회원가입
  Long insertMember(Member member);

  //회원 아이디 조회
  boolean existEmail (String email);

  //회원 조회
  Optional<Member> findByEmailAndPasswd(String email, String passwd);

  //회원수정
  
  //회원탈퇴

}
