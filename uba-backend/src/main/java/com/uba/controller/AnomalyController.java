package com.uba.controller;

import com.uba.model.Anomaly;
import com.uba.service.AnomalyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/anomalies")
public class AnomalyController {
    
    private final AnomalyService anomalyService;

    public AnomalyController(AnomalyService anomalyService) {
        this.anomalyService = anomalyService;
    }

    @GetMapping
    public List<Anomaly> getAllAnomalies() {
        return anomalyService.getAllAnomalies();
    }

    @PostMapping
    public Anomaly createAnomaly(@RequestBody Anomaly anomaly) {
        return anomalyService.createAnomaly(anomaly);
    }
} 