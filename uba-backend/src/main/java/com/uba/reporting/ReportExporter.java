package com.uba.reporting;

import com.uba.model.Report;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

@Component
public class ReportExporter {

    public void exportToTextFile(Report report, String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("=== User Behavior Analytics Report ===\n");
            writer.write("Report Name: " + report.getName() + "\n");
            writer.write("Generated At: " + 
                report.getCreatedAt().format(DateTimeFormatter.ISO_DATE_TIME) + "\n");
            writer.write("Period: " + 
                report.getStartDate().format(DateTimeFormatter.ISO_DATE) + " - " +
                report.getEndDate().format(DateTimeFormatter.ISO_DATE) + "\n");
            writer.write("\nReport Content:\n");
            writer.write(report.getContent());
        }
    }

    public void exportToCSV(Report report, String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            // Write CSV header
            writer.write("Report Name,Generated At,Start Date,End Date,Content\n");
            
            // Write CSV data
            writer.write(String.format("\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"",
                report.getName(),
                report.getCreatedAt().format(DateTimeFormatter.ISO_DATE_TIME),
                report.getStartDate().format(DateTimeFormatter.ISO_DATE),
                report.getEndDate().format(DateTimeFormatter.ISO_DATE),
                report.getContent().replace("\"", "\"\"")
            ));
        }
    }
} 