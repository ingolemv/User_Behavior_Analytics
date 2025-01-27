package com.uba.controller;

import com.uba.model.Report;
import com.uba.service.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping
    public List<Report> getAllReports() {
        return reportService.getAllReports();
    }

    @PostMapping
    public Report createReport(@RequestBody Report report) {
        return reportService.createReport(report);
    }

    @PutMapping("/{id}")
    public Report updateReport(@PathVariable Long id, @RequestBody Report report) {
        return reportService.updateReport(id, report);
    }

    @DeleteMapping("/{id}")
    public void deleteReport(@PathVariable Long id) {
        reportService.deleteReport(id);
    }

    @PostMapping("/{id}/generate")
    public Report generateReport(@PathVariable Long id) {
        return reportService.generateReport(id);
    }

    @PostMapping("/{id}/export/text")
    public ResponseEntity<String> exportReportToText(
            @PathVariable Long id,
            @RequestParam String filePath) {
        try {
            reportService.exportReportToText(id, filePath);
            return ResponseEntity.ok("Report exported successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to export report: " + e.getMessage());
        }
    }

    @PostMapping("/{id}/export/csv")
    public ResponseEntity<String> exportReportToCSV(
            @PathVariable Long id,
            @RequestParam String filePath) {
        try {
            reportService.exportReportToCSV(id, filePath);
            return ResponseEntity.ok("Report exported successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to export report: " + e.getMessage());
        }
    }
} 