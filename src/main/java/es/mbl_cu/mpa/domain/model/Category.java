package es.mbl_cu.mpa.domain.model;

public record Category(
        Long id,
        String name,
        boolean hasLength) {

    public static Category of(String name, Boolean hasLength) {
        return new Category(null, name, hasLength);
    }

}
