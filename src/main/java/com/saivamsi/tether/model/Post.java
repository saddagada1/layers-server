package com.saivamsi.tether.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String url;
    @Enumerated(EnumType.STRING)
    private PostType type;
    @ManyToMany
    @JoinColumn(name = "users_ids")
    private List<ApplicationUser> users;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;
    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

}
