package com.example.VRProjectFinal.repository;


import com.example.VRProjectFinal.model.SceneSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SceneSettingsRepository extends JpaRepository<SceneSettings, Long> {
}

