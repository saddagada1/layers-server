package com.saivamsi.layers.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductVersion {

    @Id
    @Column(name = "product_version_id")
    private UUID productVersionId;
    @Column(name = "version_number")
    private int versionNumber;
    private List<String> files;
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updatedAt;
    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;
}
