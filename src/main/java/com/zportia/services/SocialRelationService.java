package com.zportia.services;

import com.zportia.repository.SocialRelationRepository;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class SocialRelationService {
    private final SocialRelationRepository socialRelationRepository;

    public SocialRelationService(SocialRelationRepository socialRelationRepository) {
        this.socialRelationRepository = socialRelationRepository;
    }
}
