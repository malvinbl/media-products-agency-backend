package es.mbl_cu.mpa.application.validator;

import es.mbl_cu.mpa.domain.exception.BadRequestException;
import es.mbl_cu.mpa.domain.exception.BusinessException;
import es.mbl_cu.mpa.domain.model.Category;
import es.mbl_cu.mpa.domain.model.MediaProduct;
import es.mbl_cu.mpa.domain.repository.MediaProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class MediaProductValidator {

    private final MediaProductRepository mediaProductRepository;

    public void validateCreate(MediaProduct mediaProduct, Category category) {
        validateHasLength(mediaProduct, category);
    }

    public void validateUpdate(MediaProduct mediaProduct, Category category) {
        validateCreate(mediaProduct, category);
        validateExists(mediaProduct);
    }

    public void validateDateRange(LocalDateTime from, LocalDateTime to) {
        if (isDateRangeNotNull(from, to) && from.isAfter(to)) {
            throw new BadRequestException("Invalid date range");
        }
    }

    private void validateExists(MediaProduct mediaProduct) {
        if (!mediaProductRepository.exists(mediaProduct.id())) {
            throw new BadRequestException("Media Product " + mediaProduct.id() + " no exists");
        }
    }

    private void validateHasLength(MediaProduct mediaProduct, Category category) {
        if (hasLength(category) && mediaProduct.length() == null) {
            throw new BusinessException("Media Product length is required");
        }
    }

    private boolean isDateRangeNotNull(LocalDateTime from, LocalDateTime to) {
        return from != null && to != null;
    }

    private boolean hasLength(Category category) {
        return category.hasLength();
    }

}
