package com.kh.demo.domain.member.svc;

import com.kh.demo.domain.entity.Member;
import com.kh.demo.domain.member.dao.MemberDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
// 클래스에 final이 붙은 필드의 요소를 매개변수로 하는 생성자 자동 생성
@RequiredArgsConstructor
public class MemberSVCImpl implements MemberSVC {

  // 요청을 하는 대상
  private final MemberDAO memberDAO;

//  @Autowired
//  public MemberSVCImpl(@Qualifier("memberDAOImpl") MemberDAO memberDAO) {
//    this.memberDAO = memberDAO;
//    log.info("memberDAO={}", memberDAO.getClass().getName());
//
//  }

  // 회원가입
  @Override
  public Long joinMember(Member member) {
    return memberDAO.insertMember(member);      // member_id 리턴
  }

  // 이메일 존재 조회
  @Override
  public boolean existMemberId(String email) {
    return memberDAO.existMemberId(email);
  }

  // 회원 조회
  @Override
  public Optional<Member> findByEmailAndPasswd(String email, String passwd) {
    return memberDAO.findByEmailAndPasswd(email, passwd);
  }
}
