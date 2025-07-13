package es.mbl_cu.mpa.infrastructure.http.cotroller;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record MediaProductResponse(
        Long id,

        @NotNull
        String name,

        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime releaseDate,

        Long views,

        String length,

        String abbreviation,

        @NotNull
        Long categoryId) {
}
