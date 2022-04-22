package io.labs.springreact.greet;

import io.labs.springreact.greet.controller.GreetingController;
import io.labs.springreact.greet.model.Greet;
import io.labs.springreact.greet.repository.GreetRepository;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

@Slf4j
@WebMvcTest(GreetingController.class)
public class GreetMvcTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private GreetRepository repository;

    @Test
    void 기본_테스트() throws Exception {
        log.debug("basic mvc test..");

        String value = "hello";
        mvc.perform(
                MockMvcRequestBuilders.get("/api/v1/greet")
                    .param("value", value)
        )
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().string("hello"));
    }

    @Test
    void json_테스트() throws Exception {
        String name = "hyungeun";
        String greeting = "hello";
        Greet result = Greet.builder()
                .name(name)
                .greeting(greeting)
                .build();

        BDDMockito.given(repository.findById(1L)).willReturn(Optional.of(result));

        mvc.perform(
                MockMvcRequestBuilders.get("/api/v1/greet/1")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is(name)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.greeting", Matchers.is("hello")));
    }

}
