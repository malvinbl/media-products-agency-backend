package es.mbl_cu.mpa.infrastructure.http.cotroller.dto;

import jakarta.validation.constraints.NotNull;

public record CategoryDTO(
        Long id,

        @NotNull
        String name,

        boolean hasLength) {
}
