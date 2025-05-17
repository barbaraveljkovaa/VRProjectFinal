package com.example.VRProjectFinal.controllers;


import com.example.VRProjectFinal.model.Interaction;
import com.example.VRProjectFinal.service.InteractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/scenes/{sceneId}/interactions")
public class InteractionController {

    @Autowired
    private InteractionService interactionService;

    @PostMapping
    public ResponseEntity<Interaction> createInteraction(@PathVariable Long sceneId, @RequestBody Interaction interaction) {
        Interaction createdInteraction = interactionService.createInteraction(sceneId, interaction);
        return new ResponseEntity<>(createdInteraction, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Interaction>> getSceneInteractions(@PathVariable Long sceneId) {
        List<Interaction> interactions = interactionService.getSceneInteractions(sceneId);
        return ResponseEntity.ok(interactions);
    }

    @GetMapping("/{interactionId}")
    public ResponseEntity<Interaction> getInteractionById(@PathVariable Long sceneId, @PathVariable Long interactionId) {
        Interaction interaction = interactionService.getInteractionById(sceneId, interactionId);
        return ResponseEntity.ok(interaction);
    }

    @PutMapping("/{interactionId}")
    public ResponseEntity<Interaction> updateInteraction(@PathVariable Long sceneId, @PathVariable Long interactionId,
                                                         @RequestBody Interaction interactionDetails) {
        Interaction updatedInteraction = interactionService.updateInteraction(sceneId, interactionId, interactionDetails);
        return ResponseEntity.ok(updatedInteraction);
    }

    @DeleteMapping("/{interactionId}")
    public ResponseEntity<Void> deleteInteraction(@PathVariable Long sceneId, @PathVariable Long interactionId) {
        interactionService.deleteInteraction(sceneId, interactionId);
        return ResponseEntity.noContent().build();
    }
}

