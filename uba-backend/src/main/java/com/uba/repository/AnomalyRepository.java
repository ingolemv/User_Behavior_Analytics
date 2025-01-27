package com.uba.repository;

import com.uba.model.Anomaly;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnomalyRepository extends JpaRepository<Anomaly, Long> {
} 