package com.uba.service;

import com.uba.model.Anomaly;
import com.uba.repository.AnomalyRepository;
import com.uba.anomaly.AnomalyDetector;
import com.uba.anomaly.LLMAnomalyDetection;
import com.uba.model.UserActivity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnomalyService {
    private final AnomalyRepository anomalyRepository;
    private final AnomalyDetector anomalyDetector;
    private final LLMAnomalyDetection llmAnomalyDetection;

    public AnomalyService(AnomalyRepository anomalyRepository, 
                         AnomalyDetector anomalyDetector,
                         LLMAnomalyDetection llmAnomalyDetection) {
        this.anomalyRepository = anomalyRepository;
        this.anomalyDetector = anomalyDetector;
        this.llmAnomalyDetection = llmAnomalyDetection;
    }

    public List<Anomaly> getAllAnomalies() {
        return anomalyRepository.findAll();
    }

    public Anomaly createAnomaly(Anomaly anomaly) {
        return anomalyRepository.save(anomaly);
    }

    public void analyzeActivity(UserActivity activity) {
        boolean isAnomaly = anomalyDetector.detectAnomaly(activity);
        if (!isAnomaly) {
            isAnomaly = llmAnomalyDetection.detectComplexAnomaly(activity);
        }
        
        activity.setAnomaly(isAnomaly);
        if (isAnomaly) {
            activity.setAnomalyReason(anomalyDetector.getAnomalyReason(activity));
        }
    }
} 