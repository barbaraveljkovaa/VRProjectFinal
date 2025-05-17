package com.example.VRProjectFinal.controllers;

import com.example.VRProjectFinal.model.Asset;
import com.example.VRProjectFinal.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/scenes/{sceneId}/assets")
public class AssetController {

    @Autowired
    private AssetService assetService;

    @PostMapping
    public ResponseEntity<Asset> addAsset(@PathVariable Long sceneId, @RequestBody Asset asset) {
        Asset addedAsset = assetService.addAsset(sceneId, asset);
        return new ResponseEntity<>(addedAsset, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Asset>> getSceneAssets(@PathVariable Long sceneId) {
        List<Asset> assets = assetService.getSceneAssets(sceneId);
        return ResponseEntity.ok(assets);
    }

    @GetMapping("/{assetId}")
    public ResponseEntity<Asset> getAssetById(@PathVariable Long sceneId, @PathVariable Long assetId) {
        Asset asset = assetService.getAssetById(sceneId, assetId);
        return ResponseEntity.ok(asset);
    }

    @PutMapping("/{assetId}")
    public ResponseEntity<Asset> updateAsset(@PathVariable Long sceneId, @PathVariable Long assetId,
                                             @RequestBody Asset assetDetails) {
        Asset updatedAsset = assetService.updateAsset(sceneId, assetId, assetDetails);
        return ResponseEntity.ok(updatedAsset);
    }

    @DeleteMapping("/{assetId}")
    public ResponseEntity<Void> deleteAsset(@PathVariable Long sceneId, @PathVariable Long assetId) {
        assetService.deleteAsset(sceneId, assetId);
        return ResponseEntity.noContent().build();
    }
}

