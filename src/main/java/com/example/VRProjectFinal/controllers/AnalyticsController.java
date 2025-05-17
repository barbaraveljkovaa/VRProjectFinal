package com.example.VRProjectFinal.controllers;

import com.example.VRProjectFinal.model.SessionData;
import com.example.VRProjectFinal.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;

    @PostMapping("/sessions")
    public ResponseEntity<Void> logSession(@RequestBody SessionData sessionData) {
        analyticsService.logSession(sessionData);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/scenes/{sceneId}/usage")
    public ResponseEntity<Map<String, Object>> getSceneUsageStats(@PathVariable Long sceneId,
                                                                  @RequestParam(required = false) String timeFrame) {
        Map<String, Object> stats = analyticsService.getSceneUsageStats(sceneId, timeFrame);
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/users/{userId}/behavior")
    public ResponseEntity<Map<String, Object>> getUserBehaviorStats(@PathVariable String userId) {
        Map<String, Object> stats = analyticsService.getUserBehaviorStats(userId);
        return ResponseEntity.ok(stats);
    }
}

