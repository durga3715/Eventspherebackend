package com.example.eventsphere.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

   
     @Entity


public class speaker {

    @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Speaker name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Expertise is required")
    @Size(max = 100, message = "Expertise cannot exceed 100 characters")
    private String expertise;

    @NotBlank(message = "Bio is required")
    @Size(min = 20, max = 500, message = "Bio must be between 20 and 500 characters")
    private String bio;

 
      public speaker(int id, String name, String email, String expertise, String bio) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.expertise = expertise;
        this.bio = bio;
      }

    public speaker() {
    }
      public int getId() {
          return id;
      }
      public void setId(int id) {
          this.id = id;
      }
      public String getName() {
          return name;
      }
      public void setName(String name) {
          this.name = name;
      }
      public String getEmail() {
          return email;
      }
      public void setEmail(String email) {
          this.email = email;
      }
      public String getExpertise() {
          return expertise;
      }
      public void setExpertise(String expertise) {
          this.expertise = expertise;
      }
      public String getBio() {
          return bio;
      }
      public void setBio(String bio) {
          this.bio = bio;
      }
}
