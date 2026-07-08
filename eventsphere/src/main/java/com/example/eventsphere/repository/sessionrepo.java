package com.example.eventsphere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.eventsphere.entity.session;

@Repository
public interface  sessionrepo  extends JpaRepository<session , Integer> {
    
}
