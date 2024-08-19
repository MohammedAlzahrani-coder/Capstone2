package com.example.capstone2.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MissingReport {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(nullable = false)
    private LocalDateTime dateReported;

    @NotNull(message = "Item is required")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id", referencedColumnName = "itemId")
    private Item item;

    @NotNull(message = "Reporter is required")
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User reporter;

    @Size(max = 255, message = "Location reported cannot be longer than 255 characters")
    @Column(length = 255)
    private String locationReported;

    @NotEmpty(message = "Status is required")
    @Size(max = 20, message = "Status cannot be longer than 20 characters")
    @Column(length = 20)
    private String status;


}
