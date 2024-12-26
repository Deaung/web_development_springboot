package me.kimdeaung.springbootdeveloper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    @Autowired
    MemberRepository memberRepository;

    public List<Member> getAllMembers(){
        return memberRepository.findAll();//2.멤버 목록 얻기 //
    }
    /*
        1. MemberRepository 라는 빈을 주입 받은 후에
        2. findAll() 메서드를 호출하여 멤버 테이블에 지정된 멤버 목록을 가져온다
            -> 개발자가 직접적으로 정의한 메서드가 아니라 JpaRepository 라는 SpringBoot 관련 클래스에서
            메서드를 상속받아 사용하는 경우
     */
}
