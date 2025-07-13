package es.mbl_cu.mpa.infrastructure.persistence.repository;

import es.mbl_cu.mpa.domain.model.Category;
import es.mbl_cu.mpa.domain.repository.CategoryRepository;
import es.mbl_cu.mpa.infrastructure.persistence.mapper.CategoryEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class CategoryRepositoryJpaAdapter implements CategoryRepository {

    private final CategoryEntityMapper categoryEntityMapper;
    private final JpaCategoryRepository jpaCategoryRepository;

    @Override
    public Category save(Category domain) {
        var entity = categoryEntityMapper.toEntity(domain);
        var entitySaved = jpaCategoryRepository.save(entity);

        return categoryEntityMapper.toDomain(entitySaved);
    }

    @Override
    public List<Category> findAll() {
        var entities = jpaCategoryRepository.findAll();
        return categoryEntityMapper.toDomainList(entities);
    }

    @Override
    public Optional<Category> findById(Long categoryId) {
        return jpaCategoryRepository.findById(categoryId)
                .map(categoryEntityMapper::toDomain);
    }

    @Override
    public boolean exists(Long categoryId) {
        return jpaCategoryRepository.existsById(categoryId);
    }

}
