package com.example.firstapp.service;

import com.example.firstapp.domain.Member;
import com.example.firstapp.repository.MemberRepository;
import com.example.firstapp.repository.MemoryMemberRepository;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

// service 는 비즈니스 처리

@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired // Dependency Injection, spirng container에 있는 repository를 주입해준다
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원가입
    public long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하고 있는 회원입니다.");
                });
    }

    // 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findByAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
