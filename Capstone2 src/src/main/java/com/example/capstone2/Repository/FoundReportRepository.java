package com.example.capstone2.Repository;

import com.example.capstone2.Model.FoundReport;
import com.example.capstone2.Model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface FoundReportRepository extends JpaRepository<FoundReport, Long> {


    FoundReport findFoundReportByReportId(Long id);

    FoundReport findFoundReportByItem(Item item);

    List<FoundReport> findByStartDate(LocalDate date);




}
