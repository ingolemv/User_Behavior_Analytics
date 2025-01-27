package com.uba.llm;

import org.springframework.stereotype.Service;

@Service
public class LLMServiceImpl implements LLMService {
    @Override
    public String analyzeActivity(String activityContext) {
        // This would integrate with an actual LLM API
        // For now, it's a mock implementation
        if (activityContext.contains("HIGH_RISK_COUNTRY")) {
            return "Potential anomaly detected: Activity from high-risk location";
        }
        return "No significant anomalies detected";
    }
} 