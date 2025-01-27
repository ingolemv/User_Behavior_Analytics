package com.uba.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class UserActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String activityType;
    private LocalDateTime timestamp;
    private String ipAddress;
    private String location;
    private String deviceInfo;
    private double riskScore;
    private boolean isAnomaly;
    private String anomalyReason;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getActivityType() { return activityType; }
    public void setActivityType(String activityType) { this.activityType = activityType; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    public String getIpAddress() { return ipAddress; }
    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getDeviceInfo() { return deviceInfo; }
    public void setDeviceInfo(String deviceInfo) { this.deviceInfo = deviceInfo; }
    public double getRiskScore() { return riskScore; }
    public void setRiskScore(double riskScore) { this.riskScore = riskScore; }
    public boolean isAnomaly() { return isAnomaly; }
    public void setAnomaly(boolean anomaly) { isAnomaly = anomaly; }
    public String getAnomalyReason() { return anomalyReason; }
    public void setAnomalyReason(String anomalyReason) { this.anomalyReason = anomalyReason; }
} 