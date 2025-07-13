package es.mbl_cu.mpa.infrastructure.http.cotroller.mapper;

import es.mbl_cu.mpa.domain.model.Category;
import es.mbl_cu.mpa.infrastructure.http.cotroller.dto.CategoryDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryMapper {

    public Category toDomain(CategoryDTO categoryDTO) {
        return Category.of(categoryDTO.name(), categoryDTO.hasLength());
    }

    public Category toDomain(Long categoryId, CategoryDTO categoryDTO) {
        return new Category(categoryId, categoryDTO.name(), categoryDTO.hasLength());
    }

    public CategoryDTO toCategoryDTO(Category domain) {
        return new CategoryDTO(domain.id(), domain.name(), domain.hasLength());
    }

    public List<CategoryDTO> toCategoryDTOList(List<Category> domainList) {
        return domainList.stream()
                .map(this::toCategoryDTO)
                .toList();
    }

}
