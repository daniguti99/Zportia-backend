package com.zportia.services;

import com.zportia.repository.PostRepository;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


}
