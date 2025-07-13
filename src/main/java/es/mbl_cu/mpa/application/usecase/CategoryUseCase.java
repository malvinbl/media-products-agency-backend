package es.mbl_cu.mpa.application.usecase;

import es.mbl_cu.mpa.application.validator.CategoryValidator;
import es.mbl_cu.mpa.domain.exception.CategoryNotFoundException;
import es.mbl_cu.mpa.domain.model.Category;
import es.mbl_cu.mpa.domain.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CategoryUseCase {

    private final CategoryValidator categoryValidator;
    private final CategoryRepository categoryRepository;

    @Transactional
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Transactional
    public Category update(Category category) {
        categoryValidator.validate(category);
        return categoryRepository.save(category);
    }

    public Category findById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(CategoryNotFoundException::new);
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

}
