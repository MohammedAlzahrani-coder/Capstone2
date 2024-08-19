package com.example.capstone2.Controller;

import com.example.capstone2.Dtos.FoundReportDTO;
import com.example.capstone2.Model.FoundReport;
import com.example.capstone2.Model.Item;
import com.example.capstone2.Model.MissingReport;
import com.example.capstone2.Model.User;
import com.example.capstone2.Service.ItemService;
import com.example.capstone2.Service.ReportService;
import com.example.capstone2.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/foundreports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;
    private final UserService userService;
    private final ItemService itemService;

    @GetMapping("/get")
    public ResponseEntity<List<FoundReport>> getAllFoundReports() {
        List<FoundReport> reports = reportService.getAllFoundReports();
        return ResponseEntity.ok(reports);
    }

    @PostMapping("/add")
    public ResponseEntity<FoundReport> createFoundReport(@Valid @RequestBody FoundReportDTO foundReportDTO) {
        User finderUser = userService.findUserById(foundReportDTO.getFinderId());
        User caseHandler = userService.findUserById(foundReportDTO.getCaseHandlerId());
        Item item = itemService.getItemById(foundReportDTO.getItemId());
        FoundReport foundReport = foundReportDTO.generateFoundReport();
        foundReport.setFinder(finderUser);
        foundReport.setCaseHandler(caseHandler);
        foundReport.setItem(item);
        FoundReport created =  reportService.createReport(foundReport);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<FoundReport> updateMissingReport(@PathVariable Long id, @Valid @RequestBody FoundReportDTO foundReportDTO) {
        User finderUser = userService.findUserById(foundReportDTO.getFinderId());
        User caseHandler = userService.findUserById(foundReportDTO.getCaseHandlerId());
        Item item = itemService.getItemById(foundReportDTO.getItemId());
        FoundReport foundReport = foundReportDTO.generateFoundReport();
        foundReport.setFinder(finderUser);
        foundReport.setCaseHandler(caseHandler);
        foundReport.setItem(item);
        FoundReport updated = reportService.updateFoundReport(id, foundReport);
        return ResponseEntity.ok(updated);
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable Long id, @RequestParam boolean isFoundReport) {
        reportService.deleteReport(id, isFoundReport);
        return ResponseEntity.noContent().build();
    }

//    @PostMapping("/missing")
//    public ResponseEntity<MissingReport> createMissingReport(@RequestBody MissingReport missingReport) {
//        reportService.createReport(FoundReport);
//        return ResponseEntity.ok(missingReport);
//    }

//    @PutMapping("/found/{id}")
//    public ResponseEntity<FoundReport> updateFoundReport(@PathVariable Long id, @RequestBody FoundReport foundReport) {
//        reportService.updateReport(id, foundReport);
//        return ResponseEntity.ok(foundReport);
//    }



    @PutMapping("/update/status/{id}")
    public ResponseEntity<Void> updateReportStatus(@PathVariable Long id, @RequestParam String status, @RequestParam boolean isFoundReport) {
        reportService.updateReportStatus(id, status, isFoundReport);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/assign/{id}")
    public ResponseEntity<Void> assignCase(@PathVariable Long id, @RequestParam long userId, @RequestParam boolean isFoundReport) {
        reportService.assignCase(id, userId, isFoundReport);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/escalate/{id}")
    public ResponseEntity<Void> escalateCase(@PathVariable Long id, @RequestParam boolean isFoundReport) {
        reportService.escalateCase(id, isFoundReport);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get_found_report_by_id/{id}")
    public ResponseEntity<FoundReport> getFoundReportById(@PathVariable Long id) {
        FoundReport report = reportService.getFoundReportById(id);
        return ResponseEntity.ok(report);
    }

    @GetMapping("/analytics")
    public ResponseEntity<String> generateAnalyticsReport() {
        String report = reportService.generateAnalyticsReport();
        return ResponseEntity.ok(report);
    }

    @PutMapping("/feedback/{id}")
    public ResponseEntity<Void> provideFeedback(@PathVariable Long id, @RequestParam String feedback, @RequestParam boolean isFoundReport) {
        reportService.provideFeedback(id, feedback, isFoundReport);
        return ResponseEntity.ok().build();
    }

    // Fetch Found Reports by Date
    @GetMapping("/found/date")
    public ResponseEntity<List<FoundReport>> getFoundReportsByDate(@RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date);
        List<FoundReport> reports = reportService.getFoundReportsByDate(localDate);
        return ResponseEntity.ok(reports);
    }




}
