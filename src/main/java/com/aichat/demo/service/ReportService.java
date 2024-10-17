package com.aichat.demo.service;

import com.aichat.demo.dto.response.ReportResponse;
import com.aichat.demo.entity.Member;
import com.aichat.demo.entity.Report;
import com.aichat.demo.infrastructure.OpenAiService;
import com.aichat.demo.repository.MemberRepository;
import com.aichat.demo.repository.ReportRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReportService {
  private final OpenAiService openAiService;
  private final ReportRepository reportRepository;
  private final JwtService jwtService;
  private final MemberRepository memberRepository;

  public String makeReport(Member member,String animal, String content) throws JsonProcessingException {
    String reportContent = openAiService.getResponseForReport(content);
    Date currentDate = new Date();
    Report report = new Report(member,animal,reportContent,currentDate,content);
    reportRepository.save(report);
    return reportContent;
  }

  public List<ReportResponse> getReports(String token) {
    String memberEmail = jwtService.getMemberEmail(token);
    Member member = memberRepository.findByEmail(memberEmail).orElseThrow(() -> new RuntimeException("Member not found"));

    // Member에 해당하는 리포트 리스트 가져옴
    List<Report> reports = reportRepository.findByMember(member);

    // Report 엔티티를 ReportResponse DTO로 매핑
    List<ReportResponse> reportResponses = reports.stream()
        .map(report -> new ReportResponse(
            report.getReportId(),
            report.getAnimal(),
            report.getMember(),
            report.getReport(),
            report.getDate(),
            report.getChatContent()
        ))
        .collect(Collectors.toList());

    // 3개의 데이터만 반환 (최대 3개)
    return reportResponses;
  }

  public ReportResponse getReportById(String token, Long reportId) {
    String memberEmail = jwtService.getMemberEmail(token);
    Member member = memberRepository.findByEmail(memberEmail).orElseThrow(() -> new RuntimeException("Member not found"));

    Report report = reportRepository.findById(reportId).get();

    return new ReportResponse(report.getReportId(),report.getAnimal(),report.getMember(),report.getReport(),report.getDate(),report.getChatContent());
  }
}
