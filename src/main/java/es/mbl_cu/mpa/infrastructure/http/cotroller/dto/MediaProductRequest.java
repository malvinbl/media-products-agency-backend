package es.mbl_cu.mpa.infrastructure.http.cotroller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record MediaProductRequest(
        Long id,

        @NotNull
        String name,

        @Schema(type = "string", pattern = "yyyy-MM-dd'T'HH:mm:ss", example = "2025-07-25T00:00:00")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime releaseDate,

        Long views,

        Integer lengthInSeconds,

        String abbreviation,

        @NotNull
        Long categoryId) {
}
