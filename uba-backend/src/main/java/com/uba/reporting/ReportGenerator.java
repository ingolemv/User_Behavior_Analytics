package com.uba.reporting;

import com.uba.model.Report;
import com.uba.model.UserActivity;
import com.uba.repository.UserActivityRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ReportGenerator {

    private final UserActivityRepository userActivityRepository;

    public ReportGenerator(UserActivityRepository userActivityRepository) {
        this.userActivityRepository = userActivityRepository;
    }

    public Report generateReport(Report report) {
        LocalDateTime startDate = report.getStartDate();
        LocalDateTime endDate = report.getEndDate();
        
        List<UserActivity> activities = userActivityRepository
            .findByTimestampBetween(startDate, endDate);
        
        // Generate report statistics
        long totalActivities = activities.size();
        long anomalyCount = activities.stream()
            .filter(UserActivity::isAnomaly)
            .count();
        
        String reportContent = String.format(
            "Report Period: %s to %s\n" +
            "Total Activities: %d\n" +
            "Anomalies Detected: %d\n" +
            "Risk Level Distribution: %s",
            startDate, endDate,
            totalActivities,
            anomalyCount,
            getRiskLevelDistribution(activities)
        );
        
        report.setContent(reportContent);
        report.setStatus("Generated");
        return report;
    }

    private String getRiskLevelDistribution(List<UserActivity> activities) {
        long low = activities.stream()
            .filter(a -> a.getRiskScore() < 3)
            .count();
        long medium = activities.stream()
            .filter(a -> a.getRiskScore() >= 3 && a.getRiskScore() < 7)
            .count();
        long high = activities.stream()
            .filter(a -> a.getRiskScore() >= 7)
            .count();
        
        return String.format("Low: %d, Medium: %d, High: %d", low, medium, high);
    }
} 