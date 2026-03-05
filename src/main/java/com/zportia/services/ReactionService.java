package com.zportia.services;

import com.zportia.repository.ReactionRepository;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class ReactionService {
    private final ReactionRepository reactionRepository;

    public ReactionService(ReactionRepository reactionRepository) {
        this.reactionRepository = reactionRepository;
    }
}
