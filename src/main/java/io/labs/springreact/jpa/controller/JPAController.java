package io.labs.springreact.jpa.controller;

import io.labs.springreact.jpa.model.Item;
import io.labs.springreact.jpa.model.Member;
import io.labs.springreact.jpa.service.JpaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/jpa")
public class JPAController {

    private final JpaService service;

    @PostMapping("/members")
    public Member save(@RequestBody Member member){
        return service.saveMember(member);
    }

    @PostMapping("/items")
    public Item saveItem(@RequestBody Item item){
        return service.saveItem(item);
    }

}
