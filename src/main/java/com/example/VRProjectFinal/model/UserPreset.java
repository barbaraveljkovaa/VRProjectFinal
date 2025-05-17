package com.example.VRProjectFinal.model;


import jakarta.persistence.*;

@Entity
public class UserPreset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private String presetName;
    private String presetType; // camera, controls, accessibility

    @Column(columnDefinition = "TEXT")
    private String presetData; // JSON data

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPresetName() {
        return presetName;
    }

    public void setPresetName(String presetName) {
        this.presetName = presetName;
    }

    public String getPresetType() {
        return presetType;
    }

    public void setPresetType(String presetType) {
        this.presetType = presetType;
    }

    public String getPresetData() {
        return presetData;
    }

    public void setPresetData(String presetData) {
        this.presetData = presetData;
    }
}

