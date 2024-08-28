package org.example.webfluxguide.repository;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class User {

    private Long id;
    private String name;
    private LocalDateTime created;
    private LocalDateTime updated;
}
