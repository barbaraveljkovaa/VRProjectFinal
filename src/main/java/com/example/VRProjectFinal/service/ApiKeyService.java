package com.example.VRProjectFinal.service;

import com.example.VRProjectFinal.repository.ApiKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class ApiKeyService {

    @Autowired
    private ApiKeyRepository apiKeyRepository;

    public boolean isValidApiKey(String apiKey) {
        System.out.println("Validating API key: " + apiKey);
        boolean isValid = apiKeyRepository.findByKeyValueAndActiveTrue(apiKey)
                .map(key -> {
                    LocalDateTime now = LocalDateTime.now();
                    boolean notExpired = key.getExpiresAt() == null || now.isBefore(key.getExpiresAt());
                    System.out.println("Key found, expired: " + !notExpired);
                    return notExpired;
                })
                .orElse(false);
        System.out.println("Key valid: " + isValid);
        return isValid;
    }
}


