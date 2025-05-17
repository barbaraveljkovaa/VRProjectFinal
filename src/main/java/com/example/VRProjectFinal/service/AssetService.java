package com.example.VRProjectFinal.service;


import com.example.VRProjectFinal.exception.ResourceNotFoundException;
import com.example.VRProjectFinal.model.Asset;
import com.example.VRProjectFinal.model.Scene;
import com.example.VRProjectFinal.repository.AssetRepository;
import com.example.VRProjectFinal.repository.SceneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AssetService {

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private SceneRepository sceneRepository;

    public Asset addAsset(Long sceneId, Asset asset) {
        Scene scene = sceneRepository.findById(sceneId)
                .orElseThrow(() -> new ResourceNotFoundException("Scene not found with id: " + sceneId));

        asset.setScene(scene);
        return assetRepository.save(asset);
    }

    public List<Asset> getSceneAssets(Long sceneId) {
        if (!sceneRepository.existsById(sceneId)) {
            throw new ResourceNotFoundException("Scene not found with id: " + sceneId);
        }
        return assetRepository.findBySceneId(sceneId);
    }

    public Asset getAssetById(Long sceneId, Long assetId) {
        if (!sceneRepository.existsById(sceneId)) {
            throw new ResourceNotFoundException("Scene not found with id: " + sceneId);
        }

        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found with id: " + assetId));

        if (asset.getScene().getId() != sceneId) {
            throw new ResourceNotFoundException("Asset not found in scene with id: " + sceneId);
        }

        return asset;
    }

    public Asset updateAsset(Long sceneId, Long assetId, Asset assetDetails) {
        Asset asset = getAssetById(sceneId, assetId);

        if (assetDetails.getName() != null) {
            asset.setName(assetDetails.getName());
        }

        if (assetDetails.getType() != null) {
            asset.setType(assetDetails.getType());
        }

        if (assetDetails.getUrl() != null) {
            asset.setUrl(assetDetails.getUrl());
        }

        if (assetDetails.getFormat() != null) {
            asset.setFormat(assetDetails.getFormat());
        }

        return assetRepository.save(asset);
    }

    public void deleteAsset(Long sceneId, Long assetId) {
        Asset asset = getAssetById(sceneId, assetId);
        assetRepository.delete(asset);
    }
}

