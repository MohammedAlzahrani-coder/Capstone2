package com.example.capstone2.Repository;


import com.example.capstone2.Model.Item;
import com.example.capstone2.Model.MissingReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MissingReportRepository extends JpaRepository<MissingReport, Long> {


    MissingReport findByReportId(Long id);

    MissingReport findByItem(Item item);

    List<MissingReport> findByDateReported(LocalDateTime date);


}
