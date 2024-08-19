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
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    @NotEmpty(message = "Name is required")
    @Size(max = 100, message = "Name cannot be longer than 100 characters")
    @Column(nullable = false, length = 100)
    private String name;

    @Size(max = 1000, message = "Description cannot be longer than 1000 characters")
    @Column(columnDefinition = "TEXT")
    private String description;

    @NotEmpty(message = "Category is required")
    @Size(max = 50, message = "Category cannot be longer than 50 characters")
    @Column(nullable = false, length = 50)
    private String category;

    @Size(max = 50, message = "Brand cannot be longer than 50 characters")
    @Column(length = 50)
    private String brand;

    @Size(max = 50, message = "Model cannot be longer than 50 characters")
    @Column(length = 50)
    private String model;

    @Size(max = 30, message = "Color cannot be longer than 30 characters")
    @Column(length = 30)
    private String color;

    @Size(max = 1000, message = "Unique Identifiers cannot be longer than 1000 characters")
    @Column(columnDefinition = "TEXT")
    private String uniqueIdentifiers;

    @ElementCollection
    @Size(max = 10, message = "You can upload a maximum of 10 images")
    @Column(columnDefinition = "TEXT")
    private List<String> images;

    @NotNull(message = "Date lost is required")
    @PastOrPresent(message = "Date lost cannot be in the future")
    @Column(nullable = false)
    private LocalDate dateLost;

    @Size(max = 255, message = "Location lost cannot be longer than 255 characters")
    @Column(length = 255)
    private String locationLost;

    @NotEmpty(message = "Status is required")
    @Size(max = 20, message = "Status cannot be longer than 20 characters")
    @Column(length = 20)
    private String status;

    //@NotNull(message = "User is required")

    private String userEmail;


    @NotNull(message = "Value is required")
    @Column(nullable = false)
    private Double value;



}
