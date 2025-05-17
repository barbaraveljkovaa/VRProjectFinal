package com.example.VRProjectFinal.repository;

import com.example.VRProjectFinal.model.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ApiKeyRepository extends JpaRepository<ApiKey, Long> {
    Optional<ApiKey> findByKeyValue(String keyValue);
    Optional<ApiKey> findByKeyValueAndActiveTrue(String keyValue);
}

