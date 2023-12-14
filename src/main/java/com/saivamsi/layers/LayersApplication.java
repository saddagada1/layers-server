package com.saivamsi.layers;

import com.saivamsi.layers.model.Role;
import com.saivamsi.layers.model.ApplicationUser;
import com.saivamsi.layers.repository.RoleRepository;
import com.saivamsi.layers.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@SpringBootApplication
public class LayersApplication {

    public static void main(String[] args) {
        SpringApplication.run(LayersApplication.class, args);
    }

    @Bean
    CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (roleRepository.findByAuthority("ADMIN").isPresent()) return;

            Role adminRole = roleRepository.save(new Role("ADMIN"));
            roleRepository.save(new Role("USER"));

            Set<Role> roles = new HashSet<Role>();
            roles.add(adminRole);

            ApplicationUser admin = new ApplicationUser(UUID.randomUUID(), "admin", "layers@acme.ca", passwordEncoder.encode("password"), null, null, null, false, roles, new Date(), new Date());

            userRepository.save(admin);
        };
    }
}
