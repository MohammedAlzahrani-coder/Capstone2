package com.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @NotEmpty(message = "The name can not be empty")
    @Size(min = 5, message = "The name must be at least 5 characters")
    @Column(columnDefinition = "VARCHAR(50) NOT NULL")
    private String name;

    @NotEmpty(message = "The username can not be empty")
    @Size(min = 5, message = "The username must be at least 5 characters")
    @Column(columnDefinition = "VARCHAR(50) NOT NULL UNIQUE")
    private String username;

    @NotEmpty(message = "The password can not be empty")
    @Column(columnDefinition = "VARCHAR(50) NOT NULL")
    @Size(min = 8, message = "The password must be at least 8 characters")
    private String password;


    @Size(min = 8, message = "The email must be at least 8 characters")
    @NotEmpty(message = "The email can not be empty")
    @Email(message = "Invalid email")
    @Column(columnDefinition = "VARCHAR(50) NOT NULL UNIQUE")
    private String email;

    @Pattern(regexp = "^(RegularUser|Admin)$")
    @NotEmpty(message = "The role can not be empty")
    @Column(columnDefinition = "VARCHAR(10) NOT NULL")
    @Size(min = 4, message = "The role must be at least 4 characters")
    private String role;

    @Positive(message = "The age must be a positive number")
    @NotNull(message = "The age can not be empty")
    @Column(columnDefinition = "INTEGER NOT NULL")
    private Integer age;

    @OneToMany(mappedBy = "caseHandler")
    private List<FoundReport> foundReportList;

}
