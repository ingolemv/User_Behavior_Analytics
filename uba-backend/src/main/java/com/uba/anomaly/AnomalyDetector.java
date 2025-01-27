package com.uba.anomaly;

import com.uba.model.UserActivity;
import com.uba.engine.RulesEngine;
import com.uba.engine.RiskScoringEngine;
import org.springframework.stereotype.Component;

@Component
public class AnomalyDetector {
    private final RulesEngine rulesEngine;
    private final RiskScoringEngine riskScoringEngine;

    public AnomalyDetector(RulesEngine rulesEngine, RiskScoringEngine riskScoringEngine) {
        this.rulesEngine = rulesEngine;
        this.riskScoringEngine = riskScoringEngine;
    }

    public boolean detectAnomaly(UserActivity activity) {
        // Check if activity matches any rules
        if (rulesEngine.evaluateActivity(activity)) {
            return true;
        }

        // Check risk score
        double riskScore = riskScoringEngine.calculateRiskScore(activity);
        activity.setRiskScore(riskScore);
        return riskScoringEngine.getRiskLevel(riskScore).equals("HIGH");
    }

    public String getAnomalyReason(UserActivity activity) {
        if (rulesEngine.evaluateActivity(activity)) {
            return "Activity matched security rules";
        }
        return "High risk score detected: " + activity.getRiskScore();
    }
} 