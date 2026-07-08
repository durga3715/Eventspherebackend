package com.example.eventsphere.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.eventsphere.services.CustomUserDetailsService;
import com.example.eventsphere.util.JwtFilter;

@Configuration
@EnableMethodSecurity
public class eventsecur {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration)
            throws Exception {

        return configuration.getAuthenticationManager();

    }

    @Bean
    public AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();

        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());

        return provider;

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http

                // Disable CSRF
                .csrf(csrf -> csrf.disable())

                // Enable CORS
                .cors(Customizer.withDefaults())

                // Stateless Session
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Authorization
                .authorizeHttpRequests(auth -> auth

                        // ================= AUTH =================

                        .requestMatchers(
                                "/auth/register",
                                "/auth/login"
                        ).permitAll()

                        .requestMatchers(
                                "/auth/get",
                                "/auth/get/**"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                "/auth/update/**",
                                "/auth/patch/**"
                        ).hasAnyRole("ADMIN", "USER")

                        .requestMatchers(
                                "/auth/delete/**"
                        ).hasRole("ADMIN")

                        // ================= SPEAKER =================

                        .requestMatchers(
                                "/api/speakers/postData"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                "/api/speakers/get",
                                "/api/speakers/get/**"
                        ).hasAnyRole("ADMIN", "USER")

                        .requestMatchers(
                                "/api/speakers/get/**"
                        ).hasRole("ADMIN")

                        // ================= SESSION =================

                        .requestMatchers(
                                "/api/session"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                "/api/session/**"
                        ).hasAnyRole("ADMIN", "USER")

                        // ================= ATTENDEE =================

                        .requestMatchers(
                                "/api/attendees"
                        ).hasAnyRole("ADMIN", "USER")

                        .requestMatchers(
                                "/api/attendees/**"
                        ).hasAnyRole("ADMIN", "USER")

                        .anyRequest()
                        .authenticated()

                )

                .authenticationProvider(authenticationProvider())

                .formLogin(form -> form.disable())

                .httpBasic(httpBasic -> httpBasic.disable())

                // JWT Filter
                .addFilterBefore(
                        jwtFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();

    }

}