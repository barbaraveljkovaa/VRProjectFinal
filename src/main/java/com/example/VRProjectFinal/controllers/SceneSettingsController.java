package com.example.VRProjectFinal.controllers;



import com.example.VRProjectFinal.model.SceneSettings;
import com.example.VRProjectFinal.service.SceneSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/scenes/{sceneId}/settings")
public class SceneSettingsController {

    @Autowired
    private SceneSettingsService settingsService;

    @GetMapping
    public ResponseEntity<SceneSettings> getSceneSettings(@PathVariable Long sceneId) {
        SceneSettings settings = settingsService.getSceneSettings(sceneId);
        return ResponseEntity.ok(settings);
    }

    @PatchMapping
    public ResponseEntity<SceneSettings> updateSceneSettings(@PathVariable Long sceneId,
                                                             @RequestBody SceneSettings settingsDetails) {
        SceneSettings updatedSettings = settingsService.updateSceneSettings(sceneId, settingsDetails);
        return ResponseEntity.ok(updatedSettings);
    }

    @PatchMapping("/lighting")
    public ResponseEntity<SceneSettings> updateLightingSettings(@PathVariable Long sceneId,
                                                                @RequestBody Map<String, Object> lightingSettings) {
        SceneSettings updatedSettings = settingsService.updateLightingSettings(sceneId, lightingSettings);
        return ResponseEntity.ok(updatedSettings);
    }

    @PatchMapping("/physics")
    public ResponseEntity<SceneSettings> updatePhysicsSettings(@PathVariable Long sceneId,
                                                               @RequestBody Map<String, Object> physicsSettings) {
        SceneSettings updatedSettings = settingsService.updatePhysicsSettings(sceneId, physicsSettings);
        return ResponseEntity.ok(updatedSettings);
    }
}

