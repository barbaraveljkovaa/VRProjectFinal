package com.example.VRProjectFinal.service;


import com.example.VRProjectFinal.model.ApiKey;
import com.example.VRProjectFinal.repository.ApiKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AuthService {

    @Autowired
    private ApiKeyRepository apiKeyRepository;

    public String generateApiKey(String userId) {
        String keyValue = UUID.randomUUID().toString();

        ApiKey apiKey = new ApiKey();
        apiKey.setKeyValue(keyValue);
        apiKey.setUserId(userId);
        apiKey.setExpiresAt(LocalDateTime.now().plusYears(1)); // Key expires in 1 year

        apiKeyRepository.save(apiKey);

        return keyValue;
    }
    public List<Map<String, String>> getAllApiKeys() {
    List<ApiKey> keys = apiKeyRepository.findAll();
    return keys.stream()
            .map(key -> {
        Map<String, String> keyInfo = new HashMap<>();
        keyInfo.put("id", key.getId().toString());
        keyInfo.put("userId", key.getUserId());
        keyInfo.put("active", String.valueOf(key.isActive()));
        return keyInfo;
    })
            .collect(Collectors.toList());
}
    public void revokeApiKey(String keyId) {
        apiKeyRepository.findById(Long.valueOf(keyId)).ifPresent(apiKey -> {
            apiKey.setActive(false);
            apiKeyRepository.save(apiKey);
        });
    }
}

