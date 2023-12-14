package com.saivamsi.layers.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Product {

    @Id
    @Column(name = "product_id")
    private UUID productId;
    private List<String> images;
    private String name;
    private String description;
    private BigDecimal price;
    private boolean sold;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_version_id")}
    )
    private List<ProductVersion> versions;
    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updatedAt;
    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;

    public Product() {
        this.versions = new ArrayList<ProductVersion>();
    }
}
