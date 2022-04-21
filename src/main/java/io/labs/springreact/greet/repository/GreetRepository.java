package io.labs.springreact.greet.repository;

import io.labs.springreact.greet.model.Greet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GreetRepository extends JpaRepository<Greet, Long> {
}
