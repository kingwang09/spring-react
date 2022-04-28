package io.labs.springreact.jpa.service;

import io.labs.springreact.jpa.model.Item;
import io.labs.springreact.jpa.model.Member;
import io.labs.springreact.jpa.repository.ItemRepository;
import io.labs.springreact.jpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class JpaService {

    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public Member saveMember(Member member){
        return memberRepository.save(member);
    }

    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }
}
