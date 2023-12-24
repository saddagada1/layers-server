package com.saivamsi.tether;

import com.saivamsi.tether.model.ApplicationUser;
import com.saivamsi.tether.model.Role;
import com.saivamsi.tether.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class TetherApplication {

    public static void main(String[] args) {
        SpringApplication.run(TetherApplication.class, args);
    }

    @Bean
    CommandLineRunner run(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {

            ApplicationUser applicationUser = ApplicationUser.builder()
                    .username("admin")
                    .email("admin@acme.ca")
                    .password(passwordEncoder.encode("password"))
                    .role(Role.ADMIN)
                    .build();

            userRepository.save(applicationUser);
        };
    }
}
