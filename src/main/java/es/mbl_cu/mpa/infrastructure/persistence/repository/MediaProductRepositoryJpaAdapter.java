package es.mbl_cu.mpa.infrastructure.persistence.repository;

import es.mbl_cu.mpa.domain.dto.MediaProductSearchDTO;
import es.mbl_cu.mpa.domain.model.MediaProduct;
import es.mbl_cu.mpa.domain.repository.MediaProductRepository;
import es.mbl_cu.mpa.infrastructure.persistence.mapper.MediaProductEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class MediaProductRepositoryJpaAdapter implements MediaProductRepository {

    private final MediaProductEntityMapper mediaProductEntityMapper;
    private final JpaMediaProductRepository jpaMediaProductRepository;
    private final JpaCategoryRepository jpaCategoryRepository;
    private final MediaProductFilterSpec mediaProductFilterSpec;

    @Override
    public MediaProduct save(MediaProduct domain) {
        var entity = mediaProductEntityMapper.toEntity(domain);

        var categoryEntity = jpaCategoryRepository.findById(domain.categoryId()).orElse(null);
        entity.setCategoryEntity(categoryEntity);

        var entitySaved = jpaMediaProductRepository.save(entity);

        return mediaProductEntityMapper.toDomain(entitySaved);
    }

    @Override
    public Page<MediaProduct> findAll(PageRequest pageRequest) {
        var page = jpaMediaProductRepository.findAll(pageRequest);
        return page.map(mediaProductEntityMapper::toDomain);
    }

    @Override
    public boolean exists(Long mediaProductId) {
        return jpaMediaProductRepository.existsById(mediaProductId);
    }

    @Override
    public Page<MediaProduct> search(MediaProductSearchDTO mediaProductSearch) {
        var spec = mediaProductFilterSpec.findByFilter(mediaProductSearch.views(), mediaProductSearch.from(), mediaProductSearch.to());
        var page = jpaMediaProductRepository.findAll(spec, mediaProductSearch.pageRequest());

        return page.map(mediaProductEntityMapper::toDomain);
    }

}
