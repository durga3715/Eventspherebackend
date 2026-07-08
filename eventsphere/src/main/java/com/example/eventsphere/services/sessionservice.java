package com.example.eventsphere.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eventsphere.entity.session;
import com.example.eventsphere.exception.ResourceNotFound;
import com.example.eventsphere.repository.sessionrepo;

@Service
public class sessionservice {

    @Autowired
    sessionrepo crudrepo;

    public session saveData(session data) {
        return crudrepo.save(data);
    }

    public List<session> getAllData() {
        return crudrepo.findAll();
    }

    public session getUserDetails(int id) {

        return crudrepo.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Session not Found"));
    }

    public session updateDatabase(int id, session data) {

        session viewData = crudrepo.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Session not Found"));

        viewData.setSessionname(data.getSessionname());
        viewData.setTopic(data.getTopic());
        viewData.setDayofweek(data.getDayofweek());
        viewData.setStarttime(data.getStarttime());
        viewData.setEndtime(data.getEndtime());
        viewData.setRoomnote(data.getRoomnote());

        return crudrepo.save(viewData);
    }

    public session getDelete(int id) {

        session stu = crudrepo.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Session not Found"));

        crudrepo.delete(stu);

        return stu;
    }

    public session patchDatabase(int id, session data) {

        session viewData = crudrepo.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Session not Found"));

        viewData.setSessionname(data.getSessionname());
        viewData.setTopic(data.getTopic());
        viewData.setDayofweek(data.getDayofweek());
        viewData.setStarttime(data.getStarttime());
        viewData.setEndtime(data.getEndtime());
        viewData.setRoomnote(data.getRoomnote());

        return crudrepo.save(viewData);
    }
}