package es.mbl_cu.mpa.infrastructure.persistence.repository;

import es.mbl_cu.mpa.infrastructure.persistence.entity.MediaProductEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class MediaProductFilterSpec {

    public Specification<MediaProductEntity> findByFilter(Integer minViews, LocalDateTime from, LocalDateTime to) {
        List<Specification<MediaProductEntity>> specs = new ArrayList<>();

        if (minViews != null) {
            specs.add((root, query, cb) -> cb.greaterThanOrEqualTo(root.get("views"), minViews));
        }

        if (from != null && to != null) {
            specs.add((root, query, cb) -> cb.between(root.get("releaseDate"), from, to));
        } else if (from != null) {
            specs.add((root, query, cb) -> cb.greaterThanOrEqualTo(root.get("releaseDate"), from));
        } else if (to != null) {
            specs.add((root, query, cb) -> cb.lessThanOrEqualTo(root.get("releaseDate"), to));
        }

        return Specification.allOf(specs);
    }

}
