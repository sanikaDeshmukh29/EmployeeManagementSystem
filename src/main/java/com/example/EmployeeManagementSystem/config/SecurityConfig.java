package com.example.EmployeeManagementSystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // In-memory users (for demo)
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.withUsername("admin")
                .password("{noop}admin123") // {noop} means plain text password
                .roles("ADMIN")
                .build();

        UserDetails user = User.withUsername("user")
                .password("{noop}user123")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

    // Security filter chain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // disable CSRF for REST APIs
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/departments/**").hasRole("ADMIN") // only ADMIN can manage departments
                        .requestMatchers("/api/employees/**").hasAnyRole("ADMIN", "USER") // both can view employees
                        .anyRequest().authenticated()
                )
                .httpBasic(); // enable Basic Auth

        return http.build();
    }
}
