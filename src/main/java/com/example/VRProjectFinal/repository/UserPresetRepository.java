package com.example.VRProjectFinal.repository;

import com.example.VRProjectFinal.model.UserPreset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserPresetRepository extends JpaRepository<UserPreset, Long> {
    List<UserPreset> findByUserId(String userId);
}

