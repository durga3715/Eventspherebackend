package com.example.eventsphere.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eventsphere.entity.speaker;
import com.example.eventsphere.exception.ResourceNotFound;
import com.example.eventsphere.repository.speakerrepo;

@Service
public class speakerservice {

    @Autowired
    private speakerrepo sp;

    public speaker saveData(speaker data) {
        return sp.save(data);
    }

    public List<speaker> getAllData() {
        return sp.findAll();
    }

    public speaker getUserDetails(int id) {
        return sp.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Speaker not Found"));
    }

    public speaker updateDatabase(int id, speaker data) {

        speaker viewData = sp.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Speaker not Found"));

        viewData.setName(data.getName());
        viewData.setEmail(data.getEmail());
        viewData.setExpertise(data.getExpertise());
        viewData.setBio(data.getBio());

        return sp.save(viewData);
    }

    public speaker getDelete(int id) {

        speaker viewData = sp.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Speaker not Found"));

        sp.delete(viewData);

        return viewData;
    }

    public speaker patchDatabase(int id, speaker data) {

    speaker viewData = sp.findById(id)
            .orElseThrow(() -> new ResourceNotFound("Speaker not Found"));

    if (data.getName() != null && !data.getName().isBlank()) {
        viewData.setName(data.getName());
    }

    if (data.getEmail() != null && !data.getEmail().isBlank()) {
        viewData.setEmail(data.getEmail());
    }

    if (data.getExpertise() != null && !data.getExpertise().isBlank()) {
        viewData.setExpertise(data.getExpertise());
    }

    if (data.getBio() != null && !data.getBio().isBlank()) {
        viewData.setBio(data.getBio());
    }

    return sp.save(viewData);
}
}