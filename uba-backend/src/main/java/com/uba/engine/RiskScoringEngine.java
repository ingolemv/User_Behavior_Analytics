package com.uba.engine;

import com.uba.model.UserActivity;
import com.uba.repository.RuleRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RiskScoringEngine {
    private final RulesEngine rulesEngine;
    private final Map<String, Double> riskFactors;

    public RiskScoringEngine(RulesEngine rulesEngine) {
        this.rulesEngine = rulesEngine;
        this.riskFactors = new HashMap<>();
        initializeRiskFactors();
    }

    private void initializeRiskFactors() {
        // Initialize risk factors for different activity types
        riskFactors.put("LOGIN", 1.0);
        riskFactors.put("FILE_ACCESS", 2.0);
        riskFactors.put("DATA_TRANSFER", 3.0);
        // Add more as needed
    }

    public double calculateRiskScore(UserActivity activity) {
        double baseScore = riskFactors.getOrDefault(activity.getActivityType(), 1.0);
        
        // Apply rule-based adjustments
        if (rulesEngine.evaluateActivity(activity)) {
            baseScore *= 1.5; // Increase risk if activity matches a rule
        }

        // Apply additional risk factors
        if (activity.getLocation() != null && isHighRiskLocation(activity.getLocation())) {
            baseScore *= 1.2;
        }

        // Ensure score is within bounds
        return Math.min(Math.max(baseScore, 0), 10);
    }

    private boolean isHighRiskLocation(String location) {
        // Implement location-based risk assessment
        return location.equals("HIGH_RISK_COUNTRY"); // Example
    }

    public String getRiskLevel(double score) {
        if (score < 3) return "LOW";
        if (score < 7) return "MEDIUM";
        return "HIGH";
    }
} 