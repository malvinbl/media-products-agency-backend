package es.mbl_cu.mpa.application.usecase;

import es.mbl_cu.mpa.application.service.AbbreviationGeneratorService;
import es.mbl_cu.mpa.application.validator.MediaProductValidator;
import es.mbl_cu.mpa.domain.dto.MediaProductSearchRequestDTO;
import es.mbl_cu.mpa.domain.dto.PageRequestDTO;
import es.mbl_cu.mpa.domain.model.Category;
import es.mbl_cu.mpa.domain.model.MediaProduct;
import es.mbl_cu.mpa.domain.repository.MediaProductRepository;
import es.mbl_cu.mpa.domain.vo.MediaProductSortBy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MediaProductUseCaseTest {

    @Mock
    private MediaProductValidator mediaProductValidator;
    @Mock
    private MediaProductRepository mediaProductRepository;
    @Mock
    private CategoryUseCase categoryUseCase;
    @Mock
    private AbbreviationGeneratorService abbreviationGeneratorService;

    @InjectMocks
    private MediaProductUseCase mediaProductUseCase;

    @Test
    void should_create_mediaProduct_with_generated_abbreviation() {
        var mediaProduct = new MediaProduct(null, "Test Name", LocalDateTime.now(), 100L, 120, null, 1L);
        var category = new Category(1L, "Category A", false);
        var generatedAbbr = "TES";
        var mediaProductWithAbbr = new MediaProduct(null, "Test Name", mediaProduct.releaseDate(), 100L, 120, generatedAbbr, 1L);

        when(categoryUseCase.findById(1L)).thenReturn(category);
        doNothing().when(mediaProductValidator).validateCreate(mediaProduct, category);
        when(abbreviationGeneratorService.generate("Test Name")).thenReturn(generatedAbbr);
        when(mediaProductRepository.save(mediaProductWithAbbr)).thenReturn(mediaProductWithAbbr);


        var actual = mediaProductUseCase.create(mediaProduct);


        assertEquals(generatedAbbr, actual.abbreviation());
        verify(mediaProductValidator).validateCreate(mediaProduct, category);
    }

    @Test
    void should_update_mediaProduct_with_generated_abbreviation() {
        var mediaProduct = new MediaProduct(5L, "Updated", LocalDateTime.now(), 50L, 90, null, 2L);
        var category = new Category(2L, "Drama", false);
        var abbr = "UPD";
        var updatedMedia = new MediaProduct(5L, "Updated", mediaProduct.releaseDate(), 50L, 90, abbr, 2L);

        when(categoryUseCase.findById(2L)).thenReturn(category);
        doNothing().when(mediaProductValidator).validateUpdate(mediaProduct, category);
        when(abbreviationGeneratorService.generate("Updated")).thenReturn(abbr);
        when(mediaProductRepository.save(updatedMedia)).thenReturn(updatedMedia);


        var actual = mediaProductUseCase.update(mediaProduct);


        assertEquals(abbr, actual.abbreviation());
        verify(mediaProductValidator).validateUpdate(mediaProduct, category);
    }

    @Test
    void should_return_pagedMediaProducts() {
        var pageRequestDTO = new PageRequestDTO(0, 10, Sort.Direction.DESC, MediaProductSortBy.releaseDate);
        var mediaPage = new PageImpl<>(List.of(new MediaProduct(1L, "Media", LocalDateTime.now(), 10L, 60, "MED", 1L)));

        when(mediaProductRepository.findAll(any())).thenReturn(mediaPage);


        var actual = mediaProductUseCase.getAll(pageRequestDTO);


        assertEquals(1, actual.getContent().size());
    }

    @Test
    void should_search_and_return_pagedResults() {
        var from = LocalDateTime.now().minusDays(30);
        var to = LocalDateTime.now();
        var pageRequestDTO = new PageRequestDTO(0, 10, Sort.Direction.ASC, MediaProductSortBy.releaseDate);
        var searchRequest = new MediaProductSearchRequestDTO(100, from, to, pageRequestDTO);
        var resultPage = new PageImpl<MediaProduct>(List.of());

        doNothing().when(mediaProductValidator).validateDateRange(from, to);
        when(mediaProductRepository.search(any())).thenReturn(resultPage);


        var actual = mediaProductUseCase.search(searchRequest);


        assertEquals(0, actual.getTotalElements());
        verify(mediaProductValidator).validateDateRange(from, to);
    }

}
