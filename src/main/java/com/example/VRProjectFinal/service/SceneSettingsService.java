package com.example.VRProjectFinal.service;


import com.example.VRProjectFinal.exception.ResourceNotFoundException;
import com.example.VRProjectFinal.model.Scene;
import com.example.VRProjectFinal.model.SceneSettings;
import com.example.VRProjectFinal.repository.SceneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class SceneSettingsService {

    @Autowired
    private SceneRepository sceneRepository;

    public SceneSettings getSceneSettings(Long sceneId) {
        Scene scene = sceneRepository.findById(sceneId)
                .orElseThrow(() -> new ResourceNotFoundException("Scene not found with id: " + sceneId));

        if (scene.getSettings() == null) {
            scene.setSettings(new SceneSettings());
            sceneRepository.save(scene);
        }

        return scene.getSettings();
    }

    public SceneSettings updateSceneSettings(Long sceneId, SceneSettings settingsDetails) {
        Scene scene = sceneRepository.findById(sceneId)
                .orElseThrow(() -> new ResourceNotFoundException("Scene not found with id: " + sceneId));

        SceneSettings settings = scene.getSettings();
        if (settings == null) {
            settings = new SceneSettings();
            scene.setSettings(settings);
        }

        // Update settings properties
        if (settingsDetails.getAmbientLightColor() != null) {
            settings.setAmbientLightColor(settingsDetails.getAmbientLightColor());
        }

        if (settingsDetails.getAmbientLightIntensity() != null) {
            settings.setAmbientLightIntensity(settingsDetails.getAmbientLightIntensity());
        }

        if (settingsDetails.getGravity() != null) {
            settings.setGravity(settingsDetails.getGravity());
        }

        if (settingsDetails.getCollisionsEnabled() != null) {
            settings.setCollisionsEnabled(settingsDetails.getCollisionsEnabled());
        }

        if (settingsDetails.getSkyboxType() != null) {
            settings.setSkyboxType(settingsDetails.getSkyboxType());
        }

        if (settingsDetails.getFogEnabled() != null) {
            settings.setFogEnabled(settingsDetails.getFogEnabled());
        }

        if (settingsDetails.getFogColor() != null) {
            settings.setFogColor(settingsDetails.getFogColor());
        }

        sceneRepository.save(scene);
        return settings;
    }

    public SceneSettings updateLightingSettings(Long sceneId, Map<String, Object> lightingSettings) {
        Scene scene = sceneRepository.findById(sceneId)
                .orElseThrow(() -> new ResourceNotFoundException("Scene not found with id: " + sceneId));

        SceneSettings settings = scene.getSettings();
        if (settings == null) {
            settings = new SceneSettings();
            scene.setSettings(settings);
        }

        if (lightingSettings.containsKey("ambientLightColor")) {
            settings.setAmbientLightColor((String) lightingSettings.get("ambientLightColor"));
        }

        if (lightingSettings.containsKey("ambientLightIntensity")) {
            settings.setAmbientLightIntensity(Float.valueOf(lightingSettings.get("ambientLightIntensity").toString()));
        }

        sceneRepository.save(scene);
        return settings;
    }

    public SceneSettings updatePhysicsSettings(Long sceneId, Map<String, Object> physicsSettings) {
        Scene scene = sceneRepository.findById(sceneId)
                .orElseThrow(() -> new ResourceNotFoundException("Scene not found with id: " + sceneId));

        SceneSettings settings = scene.getSettings();
        if (settings == null) {
            settings = new SceneSettings();
            scene.setSettings(settings);
        }

        if (physicsSettings.containsKey("gravity")) {
            settings.setGravity(Float.valueOf(physicsSettings.get("gravity").toString()));
        }

        if (physicsSettings.containsKey("collisionsEnabled")) {
            settings.setCollisionsEnabled((Boolean) physicsSettings.get("collisionsEnabled"));
        }

        sceneRepository.save(scene);
        return settings;
    }
}

