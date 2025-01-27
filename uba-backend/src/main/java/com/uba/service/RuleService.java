package com.uba.service;

import com.uba.model.Rule;
import com.uba.repository.RuleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RuleService {
    private final RuleRepository ruleRepository;

    public RuleService(RuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    public List<Rule> getAllRules() {
        return ruleRepository.findAll();
    }

    public Rule createRule(Rule rule) {
        rule.setCreatedAt(LocalDateTime.now());
        return ruleRepository.save(rule);
    }

    public Rule updateRule(Long id, Rule rule) {
        Rule existingRule = ruleRepository.findById(id).orElseThrow();
        existingRule.setName(rule.getName());
        existingRule.setDescription(rule.getDescription());
        existingRule.setCondition(rule.getCondition());
        existingRule.setAction(rule.getAction());
        existingRule.setActive(rule.isActive());
        return ruleRepository.save(existingRule);
    }

    public void deleteRule(Long id) {
        ruleRepository.deleteById(id);
    }
} 