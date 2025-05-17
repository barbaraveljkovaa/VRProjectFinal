package com.example.VRProjectFinal.controllers;

import com.example.VRProjectFinal.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/api-keys")
    public ResponseEntity<Map<String, String>> generateApiKey(@RequestBody Map<String, String> request) {
        String userId = request.get("userId");
        String apiKey = authService.generateApiKey(userId);

        Map<String, String> response = new HashMap<>();
        response.put("apiKey", apiKey);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/api-keys/{keyId}")
    public ResponseEntity<Void> revokeApiKey(@PathVariable String keyId) {
        authService.revokeApiKey(keyId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/status")
    public ResponseEntity<String> getStatus() {
        return ResponseEntity.ok("Auth service is running");
    }

    @GetMapping("/api-keys")
    public ResponseEntity<List<Map<String, String>>> getAllApiKeys() {
        List<Map<String, String>> keys = authService.getAllApiKeys();
        return ResponseEntity.ok(keys);
    }
}


