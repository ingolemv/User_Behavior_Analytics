package com.uba.service;

import com.uba.model.Report;
import com.uba.repository.ReportRepository;
import com.uba.reporting.ReportGenerator;
import com.uba.reporting.ReportExporter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReportService {
    private final ReportRepository reportRepository;
    private final ReportGenerator reportGenerator;
    private final ReportExporter reportExporter;

    public ReportService(ReportRepository reportRepository,
                        ReportGenerator reportGenerator,
                        ReportExporter reportExporter) {
        this.reportRepository = reportRepository;
        this.reportGenerator = reportGenerator;
        this.reportExporter = reportExporter;
    }

    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    public Report createReport(Report report) {
        report.setCreatedAt(LocalDateTime.now());
        report.setStatus("Pending");
        return reportRepository.save(report);
    }

    public Report updateReport(Long id, Report report) {
        Report existingReport = reportRepository.findById(id).orElseThrow();
        existingReport.setName(report.getName());
        existingReport.setDescription(report.getDescription());
        existingReport.setReportType(report.getReportType());
        existingReport.setStartDate(report.getStartDate());
        existingReport.setEndDate(report.getEndDate());
        existingReport.setStatus(report.getStatus());
        return reportRepository.save(existingReport);
    }

    public void deleteReport(Long id) {
        reportRepository.deleteById(id);
    }

    public Report generateReport(Long id) {
        Report report = reportRepository.findById(id).orElseThrow();
        return reportRepository.save(reportGenerator.generateReport(report));
    }

    public void exportReportToText(Long id, String filePath) throws IOException {
        Report report = reportRepository.findById(id).orElseThrow();
        reportExporter.exportToTextFile(report, filePath);
    }

    public void exportReportToCSV(Long id, String filePath) throws IOException {
        Report report = reportRepository.findById(id).orElseThrow();
        reportExporter.exportToCSV(report, filePath);
    }
} 