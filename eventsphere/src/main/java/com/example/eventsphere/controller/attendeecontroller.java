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

import com.example.eventsphere.entity.attendee;
import com.example.eventsphere.services.attendeeservice;

@RestController
@RequestMapping("/api/attendees")
public class attendeecontroller {

    @Autowired
    private attendeeservice cruduser;

    @PostMapping
    public attendee saveData(@RequestBody attendee data) {
        return cruduser.saveData(data);
    }

    @GetMapping
    public List<attendee> getData() {
        return cruduser.getAllData();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserData(@PathVariable int id) {
        try {
            attendee getData = cruduser.getAttendeeDetails(id);
            return ResponseEntity.ok(getData);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Attendee not found");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateData(
            @PathVariable int id,
            @RequestBody attendee data) {

        try {
            attendee updated = cruduser.updateAttendee(id, data);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Attendee not found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteData(@PathVariable int id) {
        try {
            attendee deleted = cruduser.getDelete(id);
            return ResponseEntity.ok(deleted);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Attendee not found");
        }
    }

    @PatchMapping("/patch/{id}")
public attendee patchAttendee(@PathVariable int id, @RequestBody attendee attendee) {
    return cruduser.patchDatabase(id , attendee);
}
}