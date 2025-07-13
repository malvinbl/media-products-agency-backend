package es.mbl_cu.mpa.application.usecase;

import es.mbl_cu.mpa.application.service.AbbreviationGeneratorService;
import es.mbl_cu.mpa.application.validator.MediaProductValidator;
import es.mbl_cu.mpa.domain.dto.MediaProductSearchDTO;
import es.mbl_cu.mpa.domain.dto.PageRequestDTO;
import es.mbl_cu.mpa.domain.dto.MediaProductSearchRequestDTO;
import es.mbl_cu.mpa.domain.model.MediaProduct;
import es.mbl_cu.mpa.domain.repository.MediaProductRepository;
import es.mbl_cu.mpa.domain.vo.MediaProductSortBy;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MediaProductUseCase {

    private final MediaProductValidator mediaProductValidator;
    private final MediaProductRepository mediaProductRepository;
    private final CategoryUseCase categoryUseCase;
    private final AbbreviationGeneratorService abbreviationGeneratorService;

    @Transactional
    public MediaProduct create(MediaProduct mediaProduct) {
        var categoryDB = categoryUseCase.findById(mediaProduct.categoryId());

        mediaProductValidator.validateCreate(mediaProduct, categoryDB);
        var mediaProductChecked = checkAbbreviation(mediaProduct);

        return mediaProductRepository.save(mediaProductChecked);
    }

    @Transactional
    public MediaProduct update(MediaProduct mediaProduct) {
        var categoryDB = categoryUseCase.findById(mediaProduct.categoryId());

        mediaProductValidator.validateUpdate(mediaProduct, categoryDB);
        var mediaProductChecked = checkAbbreviation(mediaProduct);

        return mediaProductRepository.save(mediaProductChecked);
    }

    public Page<MediaProduct> getAll(PageRequestDTO pageRequestDTO) {
        var pageRequest = PageRequest.of(
                pageRequestDTO.pageNumber(),
                pageRequestDTO.pageSize(),
                Sort.by(pageRequestDTO.orderDir(), MediaProductSortBy.releaseDate.name(), pageRequestDTO.orderBy().name())
        );

        return mediaProductRepository.findAll(pageRequest);
    }

    public Page<MediaProduct> search(MediaProductSearchRequestDTO searchRequest) {
        mediaProductValidator.validateDateRange(searchRequest.from(), searchRequest.to());

        var pageRequest = PageRequest.of(
                searchRequest.pageRequest().pageNumber(),
                searchRequest.pageRequest().pageSize(),
                Sort.by( searchRequest.pageRequest().orderDir(), MediaProductSortBy.releaseDate.name(),  searchRequest.pageRequest().orderBy().name())
        );
        var mediaProductSearch = new MediaProductSearchDTO(searchRequest.views(), searchRequest.from(), searchRequest.to(), pageRequest);

        return mediaProductRepository.search(mediaProductSearch);
    }

    private MediaProduct checkAbbreviation(MediaProduct mediaProduct) {
        if (!StringUtils.isEmpty(mediaProduct.abbreviation())) {
            return mediaProduct;
        }

        var abbreviation = abbreviationGeneratorService.generate(mediaProduct.name());

        return new MediaProduct(
                mediaProduct.id(),
                mediaProduct.name(),
                mediaProduct.releaseDate(),
                mediaProduct.views(),
                mediaProduct.length(),
                abbreviation,
                mediaProduct.categoryId()
        );
    }

}
