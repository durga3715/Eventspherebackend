package com.example.eventsphere.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.eventsphere.entity.speaker; 

@Repository
 public interface  speakerrepo  extends JpaRepository< speaker , Integer> {

}



