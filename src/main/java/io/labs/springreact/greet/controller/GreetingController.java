package io.labs.springreact.greet.controller;

import io.labs.springreact.greet.model.Greet;
import io.labs.springreact.greet.repository.GreetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/greet")
@RestController
public class GreetingController {

    private final GreetRepository repository;

    @GetMapping(value="")
    public String greeting(@RequestParam(required = false, defaultValue = "world.")String value){
        log.debug("greeting: {}", value);
        return value;
    }

    @PostMapping
    public Greet saveGreet(@RequestParam(required = true) String name, @RequestParam(required = false, defaultValue = "Hello world.") String greeting){
        log.debug("save greet: name={}, greeting={}", name, greeting);

        Greet greet = repository.save(Greet.builder()
                .name(name)
                .greeting(greeting)
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
}
