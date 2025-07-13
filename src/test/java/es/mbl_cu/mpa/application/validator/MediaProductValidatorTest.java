package es.mbl_cu.mpa.application.validator;

import es.mbl_cu.mpa.domain.exception.BadRequestException;
import es.mbl_cu.mpa.domain.exception.BusinessException;
import es.mbl_cu.mpa.domain.model.Category;
import es.mbl_cu.mpa.domain.model.MediaProduct;
import es.mbl_cu.mpa.domain.repository.MediaProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MediaProductValidatorTest {

    @Mock
    private MediaProductRepository mediaProductRepository;

    @InjectMocks
    private MediaProductValidator mediaProductValidator;

    @Test
    void should_not_throw_when_length_is_present_and_required() {
        var category = mock(Category.class);
        var mediaProduct = new MediaProduct(1L, "Test", LocalDateTime.now(), 100L, 120, "TST", 2L);

        when(category.hasLength()).thenReturn(true);


        assertDoesNotThrow(() ->
                mediaProductValidator.validateCreate(mediaProduct, category)
        );
    }

    @Test
    void should_throw_when_length_is_missing_and_required() {
        var category = mock(Category.class);
        var media = new MediaProduct(1L, "Test", LocalDateTime.now(), 100L, null, "TST", 2L);

        when(category.hasLength()).thenReturn(true);


        var exception = assertThrows(BusinessException.class,
                () -> mediaProductValidator.validateCreate(media, category)
        );

        assertEquals("Media Product length is required", exception.getMessage());
    }

    @Test
    void should_not_throw_when_category_does_not_require_length() {
        var category = mock(Category.class);
        var media = new MediaProduct(1L, "Test", LocalDateTime.now(), 100L, null, "TST", 2L);

        when(category.hasLength()).thenReturn(false);


        assertDoesNotThrow(() ->
                mediaProductValidator.validateCreate(media, category)
        );
    }

    @Test
    void should_validate_update_successfully_when_media_exists() {
        var category = mock(Category.class);
        var media = new MediaProduct(10L, "Updated", LocalDateTime.now(), 50L, 60, "UPD", 1L);

        when(category.hasLength()).thenReturn(true);
        when(mediaProductRepository.exists(10L)).thenReturn(true);


        assertDoesNotThrow(() ->
                mediaProductValidator.validateUpdate(media, category)
        );
    }

    @Test
    void should_throw_when_updating_non_existent_media() {
        var category = mock(Category.class);
        var media = new MediaProduct(99L, "Nonexistent", LocalDateTime.now(), 10L, 90, "NON", 3L);

        when(category.hasLength()).thenReturn(false);
        when(mediaProductRepository.exists(99L)).thenReturn(false);


        var exception = assertThrows(BadRequestException.class,
                () -> mediaProductValidator.validateUpdate(media, category)
        );

        assertEquals("Media Product 99 no exists", exception.getMessage());
    }

    @Test
    void should_not_throw_when_valid_dateRange_provided() {
        var from = LocalDateTime.now().minusDays(5);
        var to = LocalDateTime.now();


        assertDoesNotThrow(() -> mediaProductValidator.validateDateRange(from, to));
    }

    @Test
    void should_throw_when_dateRange_is_invalid() {
        var from = LocalDateTime.now();
        var to = LocalDateTime.now().minusDays(5);


        var exception = assertThrows(BadRequestException.class,
                () -> mediaProductValidator.validateDateRange(from, to)
        );

        assertEquals("Invalid date range", exception.getMessage());
    }

    @Test
    void should_not_throw_when_any_date_is_null() {
        assertDoesNotThrow(() -> mediaProductValidator.validateDateRange(null, null));
        assertDoesNotThrow(() -> mediaProductValidator.validateDateRange(LocalDateTime.now(), null));
        assertDoesNotThrow(() -> mediaProductValidator.validateDateRange(null, LocalDateTime.now()));
    }

}
