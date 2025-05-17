package com.example.VRProjectFinal.service;


import com.example.VRProjectFinal.exception.ResourceNotFoundException;
import com.example.VRProjectFinal.model.UserPreset;
import com.example.VRProjectFinal.repository.UserPresetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserPresetService {

    @Autowired
    private UserPresetRepository presetRepository;

    public UserPreset createPreset(UserPreset preset) {
        return presetRepository.save(preset);
    }

    public List<UserPreset> getUserPresets(String userId) {
        return presetRepository.findByUserId(userId);
    }

    public UserPreset getPresetById(String userId, Long presetId) {
        UserPreset preset = presetRepository.findById(presetId)
                .orElseThrow(() -> new ResourceNotFoundException("Preset not found with id: " + presetId));

        if (!preset.getUserId().equals(userId)) {
            throw new ResourceNotFoundException("Preset not found for user: " + userId);
        }

        return preset;
    }

    public UserPreset updatePreset(String userId, Long presetId, UserPreset presetDetails) {
        UserPreset preset = getPresetById(userId, presetId);

        if (presetDetails.getPresetName() != null) {
            preset.setPresetName(presetDetails.getPresetName());
        }

        if (presetDetails.getPresetType() != null) {
            preset.setPresetType(presetDetails.getPresetType());
        }

        if (presetDetails.getPresetData() != null) {
            preset.setPresetData(presetDetails.getPresetData());
        }

        return presetRepository.save(preset);
    }

    public void deletePreset(String userId, Long presetId) {
        UserPreset preset = getPresetById(userId, presetId);
        presetRepository.delete(preset);
    }
}

