package com.example.capstone2.Controller;

import com.example.capstone2.Dtos.MissingReportDTO;
import com.example.capstone2.Model.FoundReport;
import com.example.capstone2.Model.Item;
import com.example.capstone2.Model.MissingReport;
import com.example.capstone2.Model.User;
import com.example.capstone2.Service.ItemService;
import com.example.capstone2.Service.MissingReportService;
import com.example.capstone2.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/missing-reports")
@RequiredArgsConstructor
public class MissingReportController {

    private final MissingReportService missingReportService;
    private final UserService userService;
    private final ItemService itemService;

    @GetMapping("/get")
    public ResponseEntity<List<MissingReport>> getAllMissingReports() {
        List<MissingReport> reports = missingReportService.getAllMissingReports();
        return ResponseEntity.ok(reports);
    }

    @PostMapping("/add")
    public ResponseEntity<MissingReport> createMissingReport(@Valid @RequestBody MissingReportDTO missingReport) {
        User user = userService.findUserByEmail(missingReport.getReporter());
        Item item = itemService.createItem(missingReport.getItem());
        item.setUserEmail(user.getEmail());
        MissingReport mr = missingReport.generateMissingReport();
        mr.setReporter(user);
        mr.setItem(item);
        MissingReport saved = missingReportService.createMissingReport(mr);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MissingReport> updateMissingReport(@PathVariable Long id, @Valid @RequestBody MissingReportDTO missingReport) {
        User user = userService.findUserByEmail(missingReport.getReporter());
        Item item = itemService.createItem(missingReport.getItem());
        item.setUserEmail(user.getEmail());
        MissingReport mr = missingReport.generateMissingReport();
        mr.setReporter(user);
        mr.setItem(item);
        MissingReport updated = missingReportService.updateMissingReport(id, mr);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMissingReport(@PathVariable Long id) {
        missingReportService.deleteMissingReport(id);
        return ResponseEntity.noContent().build();
    }



    @GetMapping("get_by_id/{id}")
    public ResponseEntity<MissingReport> getMissingReportById(@PathVariable Long id) {
        MissingReport report = missingReportService.getMissingReportById(id);
        return ResponseEntity.ok(report);
    }


    @GetMapping("/date")
    public ResponseEntity<List<MissingReport>> getMissingReportsByDate(@RequestParam String date) {
        LocalDateTime localDate = LocalDateTime.parse(date);
        List<MissingReport> reports = missingReportService.getMissingReportsByDate(localDate);
        return ResponseEntity.ok(reports);
    }


}
