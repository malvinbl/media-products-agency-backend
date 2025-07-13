package es.mbl_cu.mpa.domain.dto;

import java.time.LocalDateTime;

public record MediaProductSearchRequestDTO(
        Integer views,
        LocalDateTime from,
        LocalDateTime to,
        PageRequestDTO pageRequest) {
}
