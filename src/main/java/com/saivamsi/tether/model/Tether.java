package com.saivamsi.tether.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tether {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "tethered_id")
    private ApplicationUser tetheredUser;
    @ManyToOne
    @JoinColumn(name = "root_id")
    private ApplicationUser rootUser;
    @Column(columnDefinition = "boolean default false")
    private boolean approved;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;
    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;
}
