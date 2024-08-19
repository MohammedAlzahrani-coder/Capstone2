package com.example.capstone2.Dtos;

import com.example.capstone2.Model.Item;
import com.example.capstone2.Model.MissingReport;
import com.example.capstone2.Model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class MissingReportDTO {


    private Long reportId;

    @NotEmpty(message = "Title is required")
    @Size(max = 100, message = "Title cannot be longer than 100 characters")
    @Column(nullable = false, length = 100)
    private String title;

    @NotEmpty(message = "Description is required")
    @Size(max = 2000, message = "Description cannot be longer than 2000 characters")
    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull(message = "Date reported is required")
    @PastOrPresent(message = "Date reported cannot be in the future")
    private LocalDateTime dateReported;

    @NotNull(message = "Item is required")
    private Item item;

    @NotNull(message = "Reporter is required")
    private String reporter;

    @Size(max = 255, message = "Location reported cannot be longer than 255 characters")
    private String locationReported;

    @NotEmpty(message = "Status is required")
    @Size(max = 20, message = "Status cannot be longer than 20 characters")
    private String status;

    public MissingReport generateMissingReport(){
        MissingReport mr = new MissingReport();
        mr.setTitle(this.title);
        mr.setDescription(this.description);
        mr.setDateReported(this.dateReported);
        mr.setItem(this.item);
        mr.setLocationReported(this.locationReported);
        mr.setStatus(this.status);
        return mr;
    }


}
