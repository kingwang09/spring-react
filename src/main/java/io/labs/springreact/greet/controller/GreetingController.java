package io.labs.springreact.greet.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/greet")
@RestController
public class GreetingController {

    @GetMapping(value="")
    public ResponseEntity<String> greeting(@RequestParam(required = false, defaultValue = "world.")String value){
        return ResponseEntity.ok("Hello " + value);
    }
}
