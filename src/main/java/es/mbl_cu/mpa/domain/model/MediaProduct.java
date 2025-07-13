package es.mbl_cu.mpa.domain.model;

import java.time.LocalDateTime;

public record MediaProduct(
        Long id,
        String name,
        LocalDateTime releaseDate,
        Long views,
        Integer length,
        String abbreviation,
        Long categoryId) {

    public static MediaProduct of(String name, LocalDateTime releaseDate, Long views, Integer length, String abbreviation, Long categoryId) {
        return new MediaProduct(null, name, releaseDate, views, length, abbreviation, categoryId);
    }

}
