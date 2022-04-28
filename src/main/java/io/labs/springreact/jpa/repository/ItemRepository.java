package io.labs.springreact.jpa.repository;

import io.labs.springreact.jpa.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
