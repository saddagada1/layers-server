package com.saivamsi.layers.dto;

import com.saivamsi.layers.model.ApplicationUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private UUID userId;
    private String username;
    private String email;
    private String name;
    private String bio;
    private String image;
    private boolean verified;
    private Date updatedAt;
    private Date createdAt;
}
