package com.example.VRProjectFinal.service;



import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import com.example.VRProjectFinal.model.SessionData;

@Service
public class AnalyticsService {

    // In a real application, this would be stored in a database
    private Map<Long, Map<String, Object>> sceneUsageData = new HashMap<>();
    private Map<String, Map<String, Object>> userBehaviorData = new HashMap<>();

    public void logSession(SessionData sessionData) {
        // Store session data for analytics
        // In a real application, this would be stored in a database

        // Update scene usage data
        Long sceneId = sessionData.getSceneId();
        if (!sceneUsageData.containsKey(sceneId)) {
            sceneUsageData.put(sceneId, new HashMap<>());
            sceneUsageData.get(sceneId).put("totalSessions", 0);
            sceneUsageData.get(sceneId).put("totalTimeSpent", 0L);
        }

        Map<String, Object> sceneData = sceneUsageData.get(sceneId);
        sceneData.put("totalSessions", (int) sceneData.get("totalSessions") + 1);

        if (sessionData.getStartTime() != null && sessionData.getEndTime() != null) {
            long sessionDuration = java.time.Duration.between(
                    sessionData.getStartTime(), sessionData.getEndTime()).getSeconds();
            sceneData.put("totalTimeSpent", (long) sceneData.get("totalTimeSpent") + sessionDuration);
        }

        // Update user behavior data
        String userId = sessionData.getUserId();
        if (!userBehaviorData.containsKey(userId)) {
            userBehaviorData.put(userId, new HashMap<>());
            userBehaviorData.get(userId).put("totalSessions", 0);
            userBehaviorData.get(userId).put("lastActive", null);
        }

        Map<String, Object> userData = userBehaviorData.get(userId);
        userData.put("totalSessions", (int) userData.get("totalSessions") + 1);
        userData.put("lastActive", LocalDateTime.now());
        userData.put("deviceInfo", sessionData.getDeviceInfo());
    }

    public Map<String, Object> getSceneUsageStats(Long sceneId, String timeFrame) {
        // In a real application, this would query the database for analytics
        Map<String, Object> stats = new HashMap<>();

        if (sceneUsageData.containsKey(sceneId)) {
            Map<String, Object> sceneData = sceneUsageData.get(sceneId);
            stats.put("totalSessions", sceneData.get("totalSessions"));
            stats.put("totalTimeSpent", sceneData.get("totalTimeSpent"));
            stats.put("averageSessionDuration",
                    (int) sceneData.get("totalSessions") > 0 ?
                            (long) sceneData.get("totalTimeSpent") / (int) sceneData.get("totalSessions") : 0);
        } else {
            stats.put("totalSessions", 0);
            stats.put("totalTimeSpent", 0);
            stats.put("averageSessionDuration", 0);
        }

        return stats;
    }

    public Map<String, Object> getUserBehaviorStats(String userId) {
        // In a real application, this would query the database for analytics
        Map<String, Object> stats = new HashMap<>();

        if (userBehaviorData.containsKey(userId)) {
            Map<String, Object> userData = userBehaviorData.get(userId);
            stats.put("totalSessions", userData.get("totalSessions"));
            stats.put("lastActive", userData.get("lastActive"));
            stats.put("deviceInfo", userData.get("deviceInfo"));
        } else {
            stats.put("totalSessions", 0);
            stats.put("lastActive", null);
            stats.put("deviceInfo", null);
        }

        return stats;
    }
}

