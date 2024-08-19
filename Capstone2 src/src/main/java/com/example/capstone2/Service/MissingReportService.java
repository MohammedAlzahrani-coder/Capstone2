package com.example.capstone2.Service;

import com.example.capstone2.Model.MissingReport;
import com.example.capstone2.Repository.MissingReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MissingReportService {

    private final MissingReportRepository missingReportRepository;


    public List<MissingReport> getAllMissingReports() {

        return missingReportRepository.findAll();
    }

    public MissingReport createMissingReport(MissingReport missingReport) {
        return missingReportRepository.save(missingReport);

    }

    public MissingReport updateMissingReport(Long id, MissingReport missingReport) {
        MissingReport existingReport = missingReportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Missing report not found"));
        existingReport.setTitle(missingReport.getTitle());
        existingReport.setDescription(missingReport.getDescription());
        existingReport.setDateReported(missingReport.getDateReported());
        existingReport.setItem(missingReport.getItem());
        existingReport.setReporter(missingReport.getReporter());
        existingReport.setLocationReported(missingReport.getLocationReported());
        existingReport.setStatus(missingReport.getStatus());
        return missingReportRepository.save(existingReport);
    }

    public void deleteMissingReport(Long id) {
        MissingReport missingReport = missingReportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Missing report not found"));
        missingReportRepository.delete(missingReport);
    }

    public MissingReport getMissingReportById(Long id) {
        return missingReportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Missing report not found"));
    }


    public List<MissingReport> getMissingReportsByDate(LocalDateTime date) {
        return missingReportRepository.findByDateReported(date);
    }



}