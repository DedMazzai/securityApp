package ru.davydenko.springcourse.securityapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.davydenko.springcourse.securityapp.models.Member;

import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findByUsername(String username);
}
