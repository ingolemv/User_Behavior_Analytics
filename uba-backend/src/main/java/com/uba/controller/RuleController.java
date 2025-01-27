package com.uba.controller;

import com.uba.model.Rule;
import com.uba.service.RuleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rules")
public class RuleController {
    private final RuleService ruleService;

    public RuleController(RuleService ruleService) {
        this.ruleService = ruleService;
    }

    @GetMapping
    public List<Rule> getAllRules() {
        return ruleService.getAllRules();
    }

    @PostMapping
    public Rule createRule(@RequestBody Rule rule) {
        return ruleService.createRule(rule);
    }

    @PutMapping("/{id}")
    public Rule updateRule(@PathVariable Long id, @RequestBody Rule rule) {
        return ruleService.updateRule(id, rule);
    }

    @DeleteMapping("/{id}")
    public void deleteRule(@PathVariable Long id) {
        ruleService.deleteRule(id);
    }
} 