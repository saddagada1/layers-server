package com.saivamsi.layers.controller;

import com.saivamsi.layers.dto.AuthenticatedUserDTO;
import com.saivamsi.layers.dto.LoginDTO;
import com.saivamsi.layers.dto.RegistrationDTO;
import com.saivamsi.layers.dto.UserDTO;
import com.saivamsi.layers.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody RegistrationDTO body) {
        return new ResponseEntity<>(authenticationService.registerUser(body.getUsername(), body.getEmail(), body.getPassword()), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticatedUserDTO> loginUser(@RequestBody LoginDTO body) {
        try {
            return new ResponseEntity<>(authenticationService.loginUser(body.getEmail(), body.getPassword()), HttpStatus.OK);
        } catch (AuthenticationServiceException e) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }
}
