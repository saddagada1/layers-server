package com.saivamsi.tether.service;

import com.saivamsi.tether.model.Post;
import com.saivamsi.tether.model.PostType;
import com.saivamsi.tether.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post createPost(String name, String url, PostType type) {
        Post post = Post.builder().name(name).url(url).type(type).build();
        postRepository.save(post);
        return post;
    }

}
