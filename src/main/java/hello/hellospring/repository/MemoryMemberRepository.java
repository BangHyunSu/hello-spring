package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findByid(Long id) {
        return Optional.ofNullable(store.get(id));
        //Optional 로 감싸면 해당 값이 null 이여도 클라이언트에서 뭘 할 수가 있다.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny(); //루프를 돌려 .getName 이 파라미터로 넘어온 name 과 같으면 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() { // repository 를 비워주는 역할 test 에서 사용
        store.clear();
    }
}
