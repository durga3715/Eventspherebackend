package com.example.eventsphere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.eventsphere.entity.user;
import java.util.Optional;

@Repository
public interface userrepo extends JpaRepository<user, Integer> {
 Optional<user> findByUsername(String username);
}
