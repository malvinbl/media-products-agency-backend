package es.mbl_cu.mpa.application.validator;

import es.mbl_cu.mpa.domain.exception.BadRequestException;
import es.mbl_cu.mpa.domain.model.Category;
import es.mbl_cu.mpa.domain.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryValidator {

    private final CategoryRepository categoryRepository;

    public void validate(Category category) {
        validateExists(category.id());
    }

    private void validateExists(Long categoryId) {
        if (!categoryRepository.exists(categoryId)) {
            throw new BadRequestException("Category " + categoryId + " no exists");
        }
    }

}
