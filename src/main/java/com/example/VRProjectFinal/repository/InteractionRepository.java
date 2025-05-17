package com.example.VRProjectFinal.repository;

import com.example.VRProjectFinal.model.Interaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface InteractionRepository extends JpaRepository<Interaction, Long> {
    List<Interaction> findBySceneId(Long sceneId);
}

