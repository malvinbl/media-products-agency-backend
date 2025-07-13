package es.mbl_cu.mpa.domain.dto;

import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;

public record MediaProductSearchDTO(
        Integer views,
        LocalDateTime from,
        LocalDateTime to,
        PageRequest pageRequest) {
}
