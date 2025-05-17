package com.example.VRProjectFinal.service;


import com.example.VRProjectFinal.exception.ResourceNotFoundException;
import com.example.VRProjectFinal.model.Asset;
import com.example.VRProjectFinal.repository.SceneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class AssetUploadService {

    private final Path fileStorageLocation = Paths.get("uploads");

    @Autowired
    private AssetService assetService;

    @Autowired
    private SceneRepository sceneRepository;

    public AssetUploadService() {
        try {
            Files.createDirectories(fileStorageLocation);
        } catch (IOException ex) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored", ex);
        }
    }

    public String uploadAsset(MultipartFile file, String assetType) {
        try {
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path targetLocation = fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation);

            return "/uploads/" + fileName;
        } catch (IOException ex) {
            throw new RuntimeException("Could not store file " + file.getOriginalFilename(), ex);
        }
    }

    public Asset uploadAndAttachAsset(Long sceneId, MultipartFile file, String assetName, String assetType) {
        if (!sceneRepository.existsById(sceneId)) {
            throw new ResourceNotFoundException("Scene not found with id: " + sceneId);
        }

        String assetUrl = uploadAsset(file, assetType);

        Asset asset = new Asset();
        asset.setName(assetName);
        asset.setType(assetType);
        asset.setUrl(assetUrl);
        asset.setFormat(getFileExtension(file.getOriginalFilename()));

        return assetService.addAsset(sceneId, asset);
    }

    private String getFileExtension(String fileName) {
        if (fileName == null) {
            return "";
        }
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
    }
}

