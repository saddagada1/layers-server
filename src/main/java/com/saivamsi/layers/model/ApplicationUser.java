package com.saivamsi.layers.model;

import com.saivamsi.layers.dto.UserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class ApplicationUser implements UserDetails {

    @Id
    @Column(name = "user_id")
    private UUID userId;
    @Column(unique = true, columnDefinition = "citext")
    private String username;
    @Column(unique = true, columnDefinition = "citext")
    private String email;
    private String password;
    @Column(nullable = true)
    private String name;
    @Column(nullable = true, columnDefinition = "text")
    private String bio;
    @Column(nullable = true)
    private String image;
    @Column(columnDefinition = "boolean default false")
    private boolean verified;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private Set<Role> authorities;
    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updatedAt;
    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;

    public ApplicationUser() {
        this.authorities = new HashSet<Role>();
    }

    public UserDTO getUserDTO() {
        return new UserDTO(this.userId, this.username, this.email, this.name, this.bio, this.image, this.verified, this.updatedAt, this.createdAt);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
