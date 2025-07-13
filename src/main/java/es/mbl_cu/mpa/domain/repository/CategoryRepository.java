package es.mbl_cu.mpa.domain.repository;

import es.mbl_cu.mpa.domain.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    Category save(Category category);
    List<Category> findAll();
    Optional<Category> findById(Long categoryId);
    boolean exists(Long categoryId);

}
