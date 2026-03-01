package com.zportia.services;

import com.zportia.model.Comment;
import com.zportia.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment create(Comment comment) {
        return commentRepository.save(comment);

    }


}
