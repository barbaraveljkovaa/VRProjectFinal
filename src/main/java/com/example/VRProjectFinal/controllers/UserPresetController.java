package com.example.VRProjectFinal.controllers;



import com.example.VRProjectFinal.model.UserPreset;
import com.example.VRProjectFinal.service.UserPresetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/presets")
public class UserPresetController {

    @Autowired
    private UserPresetService presetService;

    @PostMapping
    public ResponseEntity<UserPreset> createPreset(@PathVariable String userId, @RequestBody UserPreset preset) {
        preset.setUserId(userId);
        UserPreset createdPreset = presetService.createPreset(preset);
        return new ResponseEntity<>(createdPreset, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserPreset>> getUserPresets(@PathVariable String userId) {
        List<UserPreset> presets = presetService.getUserPresets(userId);
        return ResponseEntity.ok(presets);
    }

    @GetMapping("/{presetId}")
    public ResponseEntity<UserPreset> getPresetById(@PathVariable String userId, @PathVariable Long presetId) {
        UserPreset preset = presetService.getPresetById(userId, presetId);
        return ResponseEntity.ok(preset);
    }

    @PutMapping("/{presetId}")
    public ResponseEntity<UserPreset> updatePreset(@PathVariable String userId, @PathVariable Long presetId,
                                                   @RequestBody UserPreset presetDetails) {
        UserPreset updatedPreset = presetService.updatePreset(userId, presetId, presetDetails);
        return ResponseEntity.ok(updatedPreset);
    }

    @DeleteMapping("/{presetId}")
    public ResponseEntity<Void> deletePreset(@PathVariable String userId, @PathVariable Long presetId) {
        presetService.deletePreset(userId, presetId);
        return ResponseEntity.noContent().build();
    }
}

