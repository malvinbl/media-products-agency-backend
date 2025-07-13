package es.mbl_cu.mpa.infrastructure.persistence.mapper;

import es.mbl_cu.mpa.domain.model.MediaProduct;
import es.mbl_cu.mpa.infrastructure.persistence.entity.MediaProductEntity;
import org.springframework.stereotype.Component;

@Component
public class MediaProductEntityMapper {

    public MediaProductEntity toEntity(MediaProduct domain) {
        return new MediaProductEntity(domain.id(), domain.name(), domain.releaseDate(), domain.views(), domain.length(), domain.abbreviation());
    }

    public MediaProduct toDomain(MediaProductEntity entity) {
        return new MediaProduct(
                entity.getId(),
                entity.getName(),
                entity.getReleaseDate(),
                entity.getViews(),
                entity.getLength(),
                entity.getAbbreviation(),
                entity.getCategoryEntity().getId()
        );
    }

}
