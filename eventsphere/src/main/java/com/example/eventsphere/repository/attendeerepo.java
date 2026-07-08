package com.example.eventsphere.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.eventsphere.entity.attendee;
@Repository
public interface attendeerepo extends JpaRepository<attendee, Integer>{
    
}
