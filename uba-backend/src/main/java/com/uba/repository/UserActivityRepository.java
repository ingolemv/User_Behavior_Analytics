package com.uba.repository;

import com.uba.model.UserActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface UserActivityRepository extends JpaRepository<UserActivity, Long> {
    List<UserActivity> findByTimestampBetween(LocalDateTime start, LocalDateTime end);
} 