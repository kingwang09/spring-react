package io.labs.springreact.greet.dto;

import lombok.*;

import java.io.Serializable;
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GreetDto implements Serializable {
    private String name;
    private String greeting;
}
