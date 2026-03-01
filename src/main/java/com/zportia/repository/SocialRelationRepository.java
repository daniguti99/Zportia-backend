package com.zportia.repository;

import com.zportia.model.SocialRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialRelationRepository extends JpaRepository<SocialRelation, Long> {
}
