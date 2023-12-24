package com.saivamsi.tether.controller;

import com.saivamsi.tether.model.Post;
import com.saivamsi.tether.model.PostType;
import com.saivamsi.tether.request.CreatePostRequest;
import com.saivamsi.tether.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user/posts")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class PostController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<String> getPost() {
        return new ResponseEntity<String>("hello world", HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Post> createPost(@RequestBody CreatePostRequest request) {
        Post post;
        if (request.getUrl() != null) {
            post = postService.createPost(request.getName(), request.getUrl(), PostType.SHARE);
        } else {
            UUID url = UUID.randomUUID();
            post = postService.createPost(request.getName(), url.toString(), PostType.UPLOAD);
        }

        return ResponseEntity
                .ok(post);
    }
}
