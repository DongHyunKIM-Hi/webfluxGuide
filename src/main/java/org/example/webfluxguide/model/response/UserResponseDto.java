package org.example.webfluxguide.model.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.example.webfluxguide.repository.User;

@Data
@Builder
@AllArgsConstructor
public class UserResponseDto {

    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static UserResponseDto of(User user) {
        return UserResponseDto.builder()
            .id(user.getId())
            .name(user.getName())
            .createdAt(user.getCreated())
            .updatedAt(user.getUpdated())
            .build();
    }
}
