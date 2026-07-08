package com.example.eventsphere.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.eventsphere.entity.speaker;
import com.example.eventsphere.services.speakerservice;

@RestController
@RequestMapping("/api/speakers")

public class speakercontroller {

    @Autowired
    private speakerservice cruduser;

    @PostMapping
    public speaker saveData(@RequestBody speaker data) {
        return cruduser.saveData(data);
    }

    @GetMapping
    public List<speaker> getData() {
        return cruduser.getAllData();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserData(@PathVariable int id) {
        try {
            speaker getData = cruduser.getUserDetails(id);
            return ResponseEntity.ok(getData);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Speaker not found");
        }
    }

    @PutMapping("/{id}")
    public speaker updatedata(@PathVariable int id, @RequestBody speaker data) {
        return cruduser.updateDatabase(id, data);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> getDeleteData(@PathVariable int id) {
        try {
            speaker getData = cruduser.getDelete(id);
            return ResponseEntity.status(HttpStatus.OK).body(getData);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Speaker not found");
        }
    }

    @PatchMapping("/{id}")
    public speaker patchData(@PathVariable int id, @RequestBody speaker data) {
        return cruduser.patchDatabase(id, data);
    }
}