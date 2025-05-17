package com.example.VRProjectFinal.model;

import jakarta.persistence.*;

@Entity
public class SceneSettings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Lighting settings
    private String ambientLightColor;
    private Float ambientLightIntensity;

    // Physics settings
    private Float gravity;
    private Boolean collisionsEnabled;

    // Rendering settings
    private String skyboxType;
    private Boolean fogEnabled;
    private String fogColor;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAmbientLightColor() {
        return ambientLightColor;
    }

    public void setAmbientLightColor(String ambientLightColor) {
        this.ambientLightColor = ambientLightColor;
    }

    public Float getAmbientLightIntensity() {
        return ambientLightIntensity;
    }

    public void setAmbientLightIntensity(Float ambientLightIntensity) {
        this.ambientLightIntensity = ambientLightIntensity;
    }

    public Float getGravity() {
        return gravity;
    }

    public void setGravity(Float gravity) {
        this.gravity = gravity;
    }

    public Boolean getCollisionsEnabled() {
        return collisionsEnabled;
    }

    public void setCollisionsEnabled(Boolean collisionsEnabled) {
        this.collisionsEnabled = collisionsEnabled;
    }

    public String getSkyboxType() {
        return skyboxType;
    }

    public void setSkyboxType(String skyboxType) {
        this.skyboxType = skyboxType;
    }

    public Boolean getFogEnabled() {
        return fogEnabled;
    }

    public void setFogEnabled(Boolean fogEnabled) {
        this.fogEnabled = fogEnabled;
    }

    public String getFogColor() {
        return fogColor;
    }

    public void setFogColor(String fogColor) {
        this.fogColor = fogColor;
    }
}

