package com.saivamsi.tether.repository;

import com.saivamsi.tether.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {

    Optional<Post> findByUrl(String url);
}
