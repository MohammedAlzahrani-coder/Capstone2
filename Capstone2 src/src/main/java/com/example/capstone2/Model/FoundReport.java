package com.example.capstone2.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FoundReport {




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;

    @NotEmpty(message = "Activity summary is required")
    @Size(max = 2000, message = "Activity summary cannot be longer than 2000 characters")
    @Column(columnDefinition = "TEXT")
    private String activitySummary;

    @NotNull(message = "Start date is required")
    @PastOrPresent(message = "Start date cannot be in the future")
    @Column(nullable = false)
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    @PastOrPresent(message = "End date cannot be in the future")
    @Column(nullable = false)
    private LocalDate endDate;


    @NotNull(message = "Item is required")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id", referencedColumnName = "itemId")
    private Item item;

    @NotNull(message = "Finder is required")
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User finder;

    @Size(max = 255, message = "Location found cannot be longer than 255 characters")
    @Column(length = 255)
    private String locationFound;

    @NotEmpty(message = "Status is required")
    @Size(max = 20, message = "Status cannot be longer than 20 characters")
    @Column(length = 20)
    private String status;

    @ManyToOne
    @JoinColumn(name = "caseHandler_id", referencedColumnName = "userId")
    private User caseHandler;




}
