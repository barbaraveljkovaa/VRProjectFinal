package com.example.VRProjectFinal.controllers;


import com.example.VRProjectFinal.model.Asset;
import com.example.VRProjectFinal.service.AssetUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/upload")
public class AssetUploadController {

    @Autowired
    private AssetUploadService uploadService;

    @PostMapping("/assets")
    public ResponseEntity<Map<String, String>> uploadAsset(@RequestParam("file") MultipartFile file,
                                                           @RequestParam("assetType") String assetType) {
        String assetUrl = uploadService.uploadAsset(file, assetType);
        Map<String, String> response = new HashMap<>();
        response.put("url", assetUrl);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/scenes/{sceneId}/assets")
    public ResponseEntity<Asset> uploadAndAttachAsset(@PathVariable Long sceneId,
                                                      @RequestParam("file") MultipartFile file,
                                                      @RequestParam("assetName") String assetName,
                                                      @RequestParam("assetType") String assetType) {
        Asset asset = uploadService.uploadAndAttachAsset(sceneId, file, assetName, assetType);
        return new ResponseEntity<>(asset, HttpStatus.CREATED);
    }

}

