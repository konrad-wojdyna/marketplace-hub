package com.marketplace.backend.user.dto;

import java.time.OffsetDateTime;

public record UserResponse(
        Long id,
        String email,
        String firstName,
        String lastName,
        String phone,
        String avatarUrl,
        Long cityId,
        String bio,
        Boolean isVerified,
        Boolean isEmailVerified,
        Boolean isPhoneVerified,
        OffsetDateTime lastActiveAt,
        OffsetDateTime  createdAt,
        OffsetDateTime  updatedAt
) {
}
