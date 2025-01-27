package com.uba.anomaly;

import com.uba.model.UserActivity;
import com.uba.llm.LLMService;
import org.springframework.stereotype.Component;

@Component
public class LLMAnomalyDetection {
    private final LLMService llmService;

    public LLMAnomalyDetection(LLMService llmService) {
        this.llmService = llmService;
    }

    public boolean detectComplexAnomaly(UserActivity activity) {
        String activityContext = createActivityContext(activity);
        String llmResponse = llmService.analyzeActivity(activityContext);
        return interpretLLMResponse(llmResponse);
    }

    private String createActivityContext(UserActivity activity) {
        return String.format(
            "User: %s, Activity: %s, Location: %s, Device: %s, Risk Score: %.2f",
            activity.getUserId(),
            activity.getActivityType(),
            activity.getLocation(),
            activity.getDeviceInfo(),
            activity.getRiskScore()
        );
    }

    private boolean interpretLLMResponse(String response) {
        // Simple interpretation - could be enhanced with more complex logic
        return response.toLowerCase().contains("anomaly") || 
               response.toLowerCase().contains("suspicious");
    }
} 