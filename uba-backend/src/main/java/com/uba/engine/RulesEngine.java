package com.uba.engine;

import com.uba.model.Rule;
import com.uba.model.UserActivity;
import com.uba.repository.RuleRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RulesEngine {
    private final RuleRepository ruleRepository;

    public RulesEngine(RuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    public boolean evaluateActivity(UserActivity activity) {
        List<Rule> activeRules = ruleRepository.findByActiveTrue();
        for (Rule rule : activeRules) {
            if (evaluateRule(activity, rule)) {
                return true; // Activity matches a rule
            }
        }
        return false; // No rules matched
    }

    private boolean evaluateRule(UserActivity activity, Rule rule) {
        // Implement rule evaluation logic
        // This is a simple example - you'd want to use a proper expression evaluator
        String condition = rule.getCondition();
        // Example condition: "activityType == 'LOGIN' && ipAddress == '192.168.1.1'"
        // You would parse and evaluate this condition against the activity
        return false; // Placeholder
    }

    public void applyAction(Rule rule, UserActivity activity) {
        // Implement action execution logic
        switch (rule.getAction()) {
            case "ALERT":
                // Send alert
                break;
            case "BLOCK":
                // Block user
                break;
            case "LOG":
                // Log activity
                break;
            default:
                // Unknown action
                break;
        }
    }
} 