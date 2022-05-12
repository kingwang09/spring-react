package io.labs.springreact.greet.controller;

import io.labs.springreact.greet.dto.GreetDto;
import io.labs.springreact.greet.model.Greet;
import io.labs.springreact.greet.repository.GreetRepository;
import io.labs.springreact.greet.support.GreetQuerySupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/greet")
@RestController
public class GreetingController {

    private final GreetRepository repository;
    private final GreetQuerySupport querySupport;

    @GetMapping(value="")
    public List<Greet> list(){
        return querySupport.findAll();
    }

    @PostMapping
    public Greet saveGreet(@RequestBody GreetDto greetDto){
        log.debug("save greet:{}", greetDto);

        Greet greet = repository.save(Greet.builder()
                .name(greetDto.getName())
                .greeting(greetDto.getGreeting())
            .build());
        return greet;
    }

    @GetMapping("/{id}")
    public Greet find(@PathVariable Long id){
        log.debug("find greet: id={}", id);
        Optional<Greet> greetOptional = repository.findById(id);
        log.debug("find greet: {}", greetOptional);
        if(greetOptional.isPresent()){
            return greetOptional.get();
        }
        return null;
    }

    @GetMapping("/querydsl/{id}")
    public Greet findById(@PathVariable Long id){
        log.debug("find greet Using Query Dsl: id={}", id);
        return querySupport.findById(id);
    }
}
