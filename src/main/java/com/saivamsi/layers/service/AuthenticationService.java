package com.saivamsi.layers.service;

import com.saivamsi.layers.dto.AuthenticatedUserDTO;
import com.saivamsi.layers.dto.UserDTO;
import com.saivamsi.layers.model.ApplicationUser;
import com.saivamsi.layers.model.Role;
import com.saivamsi.layers.repository.RoleRepository;
import com.saivamsi.layers.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@Transactional
public class AuthenticationService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private TokenService tokenService;

    public AuthenticationService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public UserDTO registerUser(String username, String email, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority("USER").get();

        Set<Role> authorities = new HashSet<Role>();
        authorities.add(userRole);

        UUID userId = UUID.randomUUID();

        return userRepository.save(new ApplicationUser(userId, username, email, encodedPassword, null, null, null, false, authorities, new Date(), new Date())).getUserDTO();
    }

    public AuthenticatedUserDTO loginUser(String email, String password) {
        try {
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            String token = tokenService.generateJwt(auth);
            return new AuthenticatedUserDTO(userRepository.findByEmail(email).orElseThrow().getUserDTO(), token);
        } catch (AuthenticationException e) {
            throw new AuthenticationServiceException("unable to login");
        }
    }
}
