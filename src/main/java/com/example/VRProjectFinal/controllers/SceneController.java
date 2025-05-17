package com.example.VRProjectFinal.controllers;



import com.example.VRProjectFinal.model.Scene;
import com.example.VRProjectFinal.service.SceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/scenes")
public class SceneController {

    @Autowired
    private SceneService sceneService;

    @PostMapping
    public ResponseEntity<Scene> createScene(@RequestBody Scene scene) {
        Scene createdScene = sceneService.createScene(scene);
        return new ResponseEntity<>(createdScene, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Scene>> getAllScenes() {
        List<Scene> scenes = sceneService.getAllScenes();
        return ResponseEntity.ok(scenes);
    }

    @GetMapping("/{sceneId}")
    public ResponseEntity<Scene> getSceneById(@PathVariable Long sceneId) {
        Scene scene = sceneService.getSceneById(sceneId);
        return ResponseEntity.ok(scene);
    }

    @PatchMapping("/{sceneId}")
    public ResponseEntity<Scene> updateScene(@PathVariable Long sceneId, @RequestBody Scene sceneDetails) {
        Scene updatedScene = sceneService.updateScene(sceneId, sceneDetails);
        return ResponseEntity.ok(updatedScene);
    }

    @DeleteMapping("/{sceneId}")
    public ResponseEntity<Void> deleteScene(@PathVariable Long sceneId) {
        sceneService.deleteScene(sceneId);
        return ResponseEntity.noContent().build();
    }
}

