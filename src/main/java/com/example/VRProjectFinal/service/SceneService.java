package com.example.VRProjectFinal.service;


import com.example.VRProjectFinal.exception.ResourceNotFoundException;
import com.example.VRProjectFinal.model.Scene;
import com.example.VRProjectFinal.model.SceneSettings;
import com.example.VRProjectFinal.repository.SceneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SceneService {

    @Autowired
    private SceneRepository sceneRepository;

    public Scene createScene(Scene scene) {
        if (scene.getSettings() == null) {
            scene.setSettings(new SceneSettings());
        }
        return sceneRepository.save(scene);
    }

    public List<Scene> getAllScenes() {
        return sceneRepository.findAll();
    }

    public Scene getSceneById(Long sceneId) {
        return sceneRepository.findById(sceneId)
                .orElseThrow(() -> new ResourceNotFoundException("Scene not found with id: " + sceneId));
    }

    public Scene updateScene(Long sceneId, Scene sceneDetails) {
        Scene scene = getSceneById(sceneId);

        if (sceneDetails.getName() != null) {
            scene.setName(sceneDetails.getName());
        }

        if (sceneDetails.getDescription() != null) {
            scene.setDescription(sceneDetails.getDescription());
        }

        return sceneRepository.save(scene);
    }

    public void deleteScene(Long sceneId) {
        Scene scene = getSceneById(sceneId);
        sceneRepository.delete(scene);
    }
}

