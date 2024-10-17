package com.aichat.demo.dto.response;

import com.aichat.demo.entity.Member;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import java.util.Date;

public record ReportResponse(
    Long reportId,
    String animal,
    Member member,
    String report,
    Date date,
    String chatContent
) {

}
