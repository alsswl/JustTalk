package com.aichat.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import java.util.Date;
import lombok.Getter;

@Entity
@Getter
public class Report {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long reportId;
  private String animal;

  @ManyToOne
  @JoinColumn(name = "memberId")
  private Member member;
  @Lob
  private String report;
  private Date date;
  @Lob
  private String chatContent;

  public Report(Member member, String animal, String report, Date date, String chatContent) {
    this.member = member;
    this.report = report;
    this.animal = animal;
    this.date = date;
    this.chatContent = chatContent;
  }

  public Report() {

  }
}

