package com.example.eventsphere.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Entity
public class session {

    @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

     @NotBlank(message = "Session name is required")
    @Size(min = 5, max = 100)
    private String sessionname;

      @NotBlank(message = "Topic is required")
    private String topic;

      @NotBlank(message = "Day is required")
    private String dayofweek;
     @NotBlank(message = "Start time is required")
    private String starttime;
     @NotBlank(message = "End  time is required")
    private String endtime;
     @NotBlank(message = "room note  is required")
    private String roomnote;

    public session() {
    }

    public session(String dayofweek, String endtime, int id, String roomnote, String sessionname, String starttime, String topic) {
        this.dayofweek = dayofweek;
        this.endtime = endtime;
        this.id = id;
        this.roomnote = roomnote;
        this.sessionname = sessionname;
        this.starttime = starttime;
        this.topic = topic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSessionname() {
        return sessionname;
    }

    public void setSessionname(String sessionname) {
        this.sessionname = sessionname;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDayofweek() {
        return dayofweek;
    }

    public void setDayofweek(String dayofweek) {
        this.dayofweek = dayofweek;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getRoomnote() {
        return roomnote;
    }

    public void setRoomnote(String roomnote) {
        this.roomnote = roomnote;
    }
}
