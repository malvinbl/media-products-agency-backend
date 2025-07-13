package es.mbl_cu.mpa.infrastructure.persistence.mapper;

import es.mbl_cu.mpa.domain.model.Category;
import es.mbl_cu.mpa.infrastructure.persistence.entity.CategoryEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryEntityMapper {

    public CategoryEntity toEntity(Category domain) {
        return new CategoryEntity(domain.id(), domain.name(), domain.hasLength());
    }

    public Category toDomain(CategoryEntity entity) {
        return new Category(entity.getId(), entity.getName(), entity.getHasLength());
    }

    public List<Category> toDomainList(List<CategoryEntity> entities) {
        return entities.stream()
                .map(this::toDomain)
                .toList();
    }

}
