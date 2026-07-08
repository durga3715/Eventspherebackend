package com.example.eventsphere.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eventsphere.entity.attendee;
import com.example.eventsphere.exception.ResourceNotFound;
import com.example.eventsphere.repository.attendeerepo;

@Service
public class attendeeservice {

    @Autowired
    attendeerepo crudrepo;

    public attendee saveData(attendee data) {
        return crudrepo.save(data);
    }

    public List<attendee> getAllData() {
        return crudrepo.findAll();
    }

    public attendee getAttendeeDetails(int id) {
        return crudrepo.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Attendee not Found"));
    }

    public attendee updateAttendee(int id, attendee data) {

        attendee viewData = crudrepo.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Attendee not Found"));

        viewData.setFullname(data.getFullname());
        viewData.setEmail(data.getEmail());
        viewData.setRegtype(data.getRegtype());

        return crudrepo.save(viewData);
    }

    public attendee getDelete(int id) {

        attendee stu = crudrepo.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Attendee not Found"));

        crudrepo.delete(stu);

        return stu;
    }

    public attendee patchDatabase(int id, attendee data) {

        attendee viewData = crudrepo.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Attendee not Found"));

        viewData.setFullname(data.getFullname());
        viewData.setEmail(data.getEmail());
        viewData.setRegtype(data.getRegtype());

        return crudrepo.save(viewData);
    }
}