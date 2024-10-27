package com.aichat.demo.dto.response;

import com.aichat.demo.entity.Member;
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
