package com.aichat.demo.dto.request;

import lombok.NonNull;

public record MemberLoginRequest(
    @NonNull String email,
    @NonNull String password
) {
}
