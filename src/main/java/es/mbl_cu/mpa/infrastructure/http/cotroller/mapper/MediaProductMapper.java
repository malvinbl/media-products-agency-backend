package es.mbl_cu.mpa.infrastructure.http.cotroller.mapper;

import es.mbl_cu.mpa.application.service.TimeFormatterService;
import es.mbl_cu.mpa.domain.model.MediaProduct;
import es.mbl_cu.mpa.infrastructure.http.cotroller.MediaProductResponse;
import es.mbl_cu.mpa.infrastructure.http.cotroller.dto.MediaProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MediaProductMapper {

    private final TimeFormatterService timeFormatterService;

    public MediaProduct toDomain(MediaProductRequest mediaProductRequest) {
        return MediaProduct.of(
                mediaProductRequest.name(),
                mediaProductRequest.releaseDate(),
                mediaProductRequest.views(),
                mediaProductRequest.lengthInSeconds(),
                mediaProductRequest.abbreviation(),
                mediaProductRequest.categoryId()
        );
    }

    public MediaProduct toDomain(Long mediaProductId, MediaProductRequest mediaProductRequest) {
        return new MediaProduct(
                mediaProductId,
                mediaProductRequest.name(),
                mediaProductRequest.releaseDate(),
                mediaProductRequest.views(),
                mediaProductRequest.lengthInSeconds(),
                mediaProductRequest.abbreviation(),
                mediaProductRequest.categoryId()
        );
    }

    public MediaProductResponse toMediaProductResponse(MediaProduct domain) {
        var lengthFormatted = timeFormatterService.formatDuration(domain.length());
        return new MediaProductResponse(domain.id(), domain.name(), domain.releaseDate(), domain.views(), lengthFormatted, domain.abbreviation(), domain.categoryId());
    }

    public Page<MediaProductResponse> toMediaProductResponsePage(Page<MediaProduct> domainPage) {
        return domainPage.map(this::toMediaProductResponse);
    }

}
