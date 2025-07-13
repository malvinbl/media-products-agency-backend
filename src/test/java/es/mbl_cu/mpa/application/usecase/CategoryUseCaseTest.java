package es.mbl_cu.mpa.application.usecase;

import es.mbl_cu.mpa.application.validator.CategoryValidator;
import es.mbl_cu.mpa.domain.exception.CategoryNotFoundException;
import es.mbl_cu.mpa.domain.model.Category;
import es.mbl_cu.mpa.domain.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryUseCaseTest {

    @Mock
    private CategoryValidator categoryValidator;
    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryUseCase categoryUseCase;

    @Test
    void should_create_category() {
        var category = new Category(1L, "Test Category", false);

        when(categoryRepository.save(category)).thenReturn(category);


        var actual = categoryUseCase.create(category);


        assertEquals(category, actual);
    }

    @Test
    void should_update_category() {
        var category = new Category(1L, "Updated Category", false);

        when(categoryRepository.save(category)).thenReturn(category);


        var actual = categoryUseCase.update(category);


        assertEquals(category, actual);
    }

    @Test
    void should_return_category_when_foundById() {
        var category = new Category(1L, "Found Category", false);

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));


        var actual = categoryUseCase.findById(1L);


        assertEquals(category, actual);
    }

    @Test
    void should_throw_exception_when_category_not_found() {
        when(categoryRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () ->
                categoryUseCase.findById(999L)
        );
    }

    @Test
    void should_return_all_categories() {
        var categories = List.of(
                new Category(1L, "Cat1", false),
                new Category(2L, "Cat2", false)
        );

        when(categoryRepository.findAll()).thenReturn(categories);


        var actual = categoryUseCase.getAll();


        assertEquals(categories, actual);
    }

}
