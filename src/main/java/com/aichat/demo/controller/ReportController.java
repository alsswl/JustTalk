package com.aichat.demo.controller;

import com.aichat.demo.dto.response.ReportResponse;
import com.aichat.demo.service.ReportService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/reports")
public class ReportController {
  private final ReportService reportService;

  @GetMapping
  public ResponseEntity<List<ReportResponse>> getReports(@RequestHeader("Authorization") String token){
    System.out.println(token);
    List<ReportResponse> reports = reportService.getReports(token);
    return ResponseEntity.ok().body(reports);
  }

  @GetMapping("/{reportId}")
  public ResponseEntity<ReportResponse> getReportById(
      @PathVariable Long reportId,
      @RequestHeader("Authorization") String token
  ){
    ReportResponse report = reportService.getReportById(token,reportId);
    return ResponseEntity.ok().body(report);
  }
}
