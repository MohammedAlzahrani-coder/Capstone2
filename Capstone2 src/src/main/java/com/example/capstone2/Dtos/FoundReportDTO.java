package com.example.capstone2.Dtos;

import com.example.capstone2.Model.FoundReport;
import com.example.capstone2.Model.Item;
import com.example.capstone2.Model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FoundReportDTO {
    private Long reportId;

    @NotEmpty(message = "Activity summary is required")
    @Size(max = 2000, message = "Activity summary cannot be longer than 2000 characters")
    private String activitySummary;

    @NotNull(message = "Start date is required")
    @PastOrPresent(message = "Start date cannot be in the future")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    @PastOrPresent(message = "End date cannot be in the future")
    private LocalDate endDate;


    @NotNull(message = "Item is required")
    private Long itemId;

    @NotNull(message = "Finder is required")
    private Long finderId;

    @Size(max = 255, message = "Location found cannot be longer than 255 characters")
    private String locationFound;

    @NotEmpty(message = "Status is required")
    @Size(max = 20, message = "Status cannot be longer than 20 characters")
    private String status;

    private Long caseHandlerId;

    public FoundReport generateFoundReport(){
        FoundReport foundReport = new FoundReport();
        foundReport.setActivitySummary(this.activitySummary);
        foundReport.setStartDate(this.startDate);
        foundReport.setEndDate(this.endDate);
        foundReport.setLocationFound(this.locationFound);
        foundReport.setStatus(this.status);
        return foundReport;
    }

}
