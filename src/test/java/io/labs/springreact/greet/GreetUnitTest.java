package io.labs.springreact.greet;

import io.labs.springreact.greet.model.Greet;
import io.labs.springreact.greet.repository.GreetRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@Slf4j
@SpringBootTest
public class GreetUnitTest {

    @Autowired
    private GreetRepository repository;


    void init(){
        log.debug("before each..");
        repository.save(Greet.builder()
                .name("hyungeun")
                .greeting("hello")
        .build());
    }

    @Test
    public void 기본_테스트(){
        init();

        log.debug("basic test..");
        Optional<Greet> greetOptional = repository.findById(1L);
        Greet greet = greetOptional.get();

        Assertions.assertThat(greet.getId()).isEqualTo(1L);
        Assertions.assertThat(greet.getName()).isEqualTo("hyungeun");
        Assertions.assertThat(greet.getGreeting()).isEqualTo("hello");
    }
}
