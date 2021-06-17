package com.example.firstapp.service;

import com.example.firstapp.domain.Member;
import com.example.firstapp.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemoryMemberRepository memberRepository ;

    @Test
    void join() {
        // given
        Member member = new Member();
        member.setName("ramsey");

        // when
        Long serviceId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(serviceId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    void duplicateException(){
        Member member1 = new Member();
        member1.setName("spring1");

        Member member2 = new Member();
        member2.setName("spring1");

        memberService.join(member1);

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하고 있는 회원입니다.");


    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}