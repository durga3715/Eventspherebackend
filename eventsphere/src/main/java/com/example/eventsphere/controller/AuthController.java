package com.example.eventsphere.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.eventsphere.entity.user;
import com.example.eventsphere.services.AuthService;
import com.example.eventsphere.services.JwtService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final AuthService authService;

    public AuthController(JwtService jwtService,
                          AuthenticationManager authenticationManager,
                          AuthService authService) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.authService = authService;
    }

    @PostMapping("/register")
    public user register(@RequestBody user userData) {
        return authService.register(userData);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody user userData) {

        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userData.getUsername(),
                            userData.getPassword()));

            String token = jwtService.generateToken(userData.getUsername());

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login Successful");
            response.put("token", token);

            return ResponseEntity.ok(response);

        } catch (BadCredentialsException e) {

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Invalid Email or Password");

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(response);
        }
    }

    public JwtService getJwtService() {
        return jwtService;
    }

    @GetMapping("/get")
    public List<user> getAllUsers() {
        return authService.getAllUsers();
    }

    @GetMapping("/get/{id}")
    public user getUserById(@PathVariable int id) {
        return authService.getUserById(id);
    }

    @PutMapping("/put/{id}")
    public user updateUser(@PathVariable int id, @RequestBody user userData) {
        return authService.updateUser(id, userData);
    }

    @DeleteMapping("/delete/{id}")
    public user deleteUser(@PathVariable int id) {
        return authService.deleteUser(id);
    }

    @PatchMapping("/patch/{id}")
    public user patchUser(@PathVariable int id, @RequestBody user userData) {
        return authService.patchUser(id, userData);
    }
}