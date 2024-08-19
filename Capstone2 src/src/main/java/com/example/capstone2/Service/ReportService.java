package com.example.capstone2.Service;

import com.example.capstone2.Model.FoundReport;
import com.example.capstone2.Model.MissingReport;
import com.example.capstone2.Model.User;
import com.example.capstone2.Repository.FoundReportRepository;
import com.example.capstone2.Repository.MissingReportRepository;
import com.example.capstone2.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final FoundReportRepository foundReportRepository;
    private final MissingReportRepository missingReportRepository;
    private final UserRepository userRepository;

    public List<FoundReport> viewFoundReports() {
        return foundReportRepository.findAll();
    }

    public List<MissingReport> viewMissingReports() {
        return missingReportRepository.findAll();
    }

    public List<FoundReport> getAllFoundReports() {
        return foundReportRepository.findAll();
    }

    public void updateReportStatus(Long reportId, String status, boolean isFoundReport) {
        if (isFoundReport) {
            FoundReport report = foundReportRepository.findById(reportId)
                    .orElseThrow(() -> new RuntimeException("Found report not found"));
            report.setStatus(status);
            foundReportRepository.save(report);
        } else {
            MissingReport report = missingReportRepository.findById(reportId)
                    .orElseThrow(() -> new RuntimeException("Missing report not found"));
            report.setStatus(status);
            missingReportRepository.save(report);
        }
    }

    public void assignCase(Long reportId, long userId, boolean isFoundReport) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (isFoundReport) {
            FoundReport report = foundReportRepository.findById(reportId)
                    .orElseThrow(() -> new RuntimeException("Found report not found"));
            report.setCaseHandler(user);
            foundReportRepository.save(report);
        } else {
            MissingReport report = missingReportRepository.findById(reportId)
                    .orElseThrow(() -> new RuntimeException("Missing report not found"));
            report.setReporter(user);
            missingReportRepository.save(report);
        }
    }

    public void escalateCase(Long reportId, boolean isFoundReport) {
        if (isFoundReport) {
            FoundReport report = foundReportRepository.findById(reportId)
                    .orElseThrow(() -> new RuntimeException("Found report not found"));
            report.setStatus("Escalated");
            foundReportRepository.save(report);
        } else {
            MissingReport report = missingReportRepository.findById(reportId)
                    .orElseThrow(() -> new RuntimeException("Missing report not found"));
            report.setStatus("Escalated");
            missingReportRepository.save(report);
        }
    }

    public String generateAnalyticsReport() {
        long totalFoundReports = foundReportRepository.count();
        long totalMissingReports = missingReportRepository.count();
        return "Total Found Reports: " + totalFoundReports + ", Total Missing Reports: " + totalMissingReports;
    }

    public void provideFeedback(Long reportId, String feedback, boolean isFoundReport) {
        if (isFoundReport) {
            FoundReport report = foundReportRepository.findById(reportId)
                    .orElseThrow(() -> new RuntimeException("Found report not found"));
            report.setActivitySummary(report.getActivitySummary() + "\nFeedback: " + feedback);
            foundReportRepository.save(report);
        } else {
            MissingReport report = missingReportRepository.findById(reportId)
                    .orElseThrow(() -> new RuntimeException("Missing report not found"));
            report.setDescription(report.getDescription() + "\nFeedback: " + feedback);
            missingReportRepository.save(report);
        }
    }

    public FoundReport createReport(FoundReport foundReport) {
        return foundReportRepository.save(foundReport);

    }


    public FoundReport updateFoundReport(Long id, FoundReport foundReport) {
        FoundReport existingReport = foundReportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Missing report not found"));
        existingReport.setActivitySummary(foundReport.getActivitySummary());
        existingReport.setStartDate(foundReport.getStartDate());
        existingReport.setEndDate(foundReport.getEndDate());
        existingReport.setLocationFound(foundReport.getLocationFound());
        existingReport.setStatus(foundReport.getStatus());
        existingReport.setItem(foundReport.getItem());
        existingReport.setCaseHandler(foundReport.getCaseHandler());
        existingReport.setFinder(foundReport.getFinder());
        return foundReportRepository.save(existingReport);

    }

    public void deleteReport(Long id, boolean isFoundReport) {
        if (isFoundReport) {
            FoundReport report = foundReportRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Found report not found"));
            foundReportRepository.delete(report);
        } else {
            MissingReport report = missingReportRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Missing report not found"));
            missingReportRepository.delete(report);
        }
    }

    public FoundReport getFoundReportById(Long id) {
        return foundReportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Found report not found"));
    }

    public MissingReport getMissingReportById(Long id) {
        return missingReportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Missing report not found"));
    }


    public List<FoundReport> getFoundReportsByDate(LocalDate date) {
        return foundReportRepository.findByStartDate(date);
    }






}