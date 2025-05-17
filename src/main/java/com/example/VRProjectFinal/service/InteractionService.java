package com.example.VRProjectFinal.service;


import com.example.VRProjectFinal.exception.ResourceNotFoundException;
import com.example.VRProjectFinal.model.Interaction;
import com.example.VRProjectFinal.model.Scene;
import com.example.VRProjectFinal.repository.InteractionRepository;
import com.example.VRProjectFinal.repository.SceneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InteractionService {

    @Autowired
    private InteractionRepository interactionRepository;

    @Autowired
    private SceneRepository sceneRepository;

    public Interaction createInteraction(Long sceneId, Interaction interaction) {
        Scene scene = sceneRepository.findById(sceneId)
                .orElseThrow(() -> new ResourceNotFoundException("Scene not found with id: " + sceneId));

        interaction.setScene(scene);
        return interactionRepository.save(interaction);
    }

    public List<Interaction> getSceneInteractions(Long sceneId) {
        if (!sceneRepository.existsById(sceneId)) {
            throw new ResourceNotFoundException("Scene not found with id: " + sceneId);
        }
        return interactionRepository.findBySceneId(sceneId);
    }

    public Interaction getInteractionById(Long sceneId, Long interactionId) {
        if (!sceneRepository.existsById(sceneId)) {
            throw new ResourceNotFoundException("Scene not found with id: " + sceneId);
        }

        Interaction interaction = interactionRepository.findById(interactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Interaction not found with id: " + interactionId));

        if (interaction.getScene().getId() != sceneId) {
            throw new ResourceNotFoundException("Interaction not found in scene with id: " + sceneId);
        }

        return interaction;
    }

    public Interaction updateInteraction(Long sceneId, Long interactionId, Interaction interactionDetails) {
        Interaction interaction = getInteractionById(sceneId, interactionId);

        if (interactionDetails.getName() != null) {
            interaction.setName(interactionDetails.getName());
        }

        if (interactionDetails.getTriggerType() != null) {
            interaction.setTriggerType(interactionDetails.getTriggerType());
        }

        if (interactionDetails.getActionType() != null) {
            interaction.setActionType(interactionDetails.getActionType());
        }

        if (interactionDetails.getScriptContent() != null) {
            interaction.setScriptContent(interactionDetails.getScriptContent());
        }

        return interactionRepository.save(interaction);
    }

    public void deleteInteraction(Long sceneId, Long interactionId) {
        Interaction interaction = getInteractionById(sceneId, interactionId);
        interactionRepository.delete(interaction);
    }
}

