package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원 가입
    public Long join(Member member) {

        validateDupilcateMember(member);
        //같은 이름이 있는 중복 회원 볼가
        // control + T -> method 로 메소드를 만들어버림
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDupilcateMember(Member member) { // --> 만든 메소드
        memberRepository.findByName(member.getName())
                .ifPresent(m-> {      //ifPresent -> 값이 있다면
                     throw new IllegalStateException("이미 존재하는 이름입니다.");
                });
    }

    //전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //회원 한명 조회?
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findByid(memberId);
    }

    //회원 여러명 조회

}
