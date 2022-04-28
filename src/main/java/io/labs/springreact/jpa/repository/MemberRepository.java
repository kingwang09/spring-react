package io.labs.springreact.jpa.repository;

import io.labs.springreact.jpa.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
