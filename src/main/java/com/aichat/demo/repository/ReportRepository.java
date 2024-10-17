package com.aichat.demo.repository;
import com.aichat.demo.entity.Member;
import com.aichat.demo.entity.Report;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report,Long> {

  List<Report> findByMember(Member member);
}
