package es.mbl_cu.mpa.infrastructure.http.cotroller;

import com.fasterxml.jackson.annotation.JsonFormat;
import es.mbl_cu.mpa.application.usecase.MediaProductUseCase;
import es.mbl_cu.mpa.domain.dto.PageRequestDTO;
import es.mbl_cu.mpa.domain.dto.MediaProductSearchRequestDTO;
import es.mbl_cu.mpa.domain.vo.MediaProductSortBy;
import es.mbl_cu.mpa.infrastructure.http.cotroller.dto.MediaProductRequest;
import es.mbl_cu.mpa.infrastructure.http.cotroller.mapper.MediaProductMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Tag(name = "Media Product Controller")
@RequiredArgsConstructor
@RequestMapping("/media-products")
@RestController
public class MediaProductController {

    private final MediaProductMapper mediaProductMapper;
    private final MediaProductUseCase mediaProductUseCase;

    @Operation(summary = "Create a new Media Product")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<MediaProductResponse> create(
            @Valid @RequestBody MediaProductRequest request) {

        var mediaProduct = mediaProductMapper.toDomain(request);
        var mediaProductCreated = mediaProductUseCase.create(mediaProduct);

        return ResponseEntity.ok(mediaProductMapper.toMediaProductResponse(mediaProductCreated));
    }

    @Operation(summary = "Update a given Media Product")
    @PutMapping("/{mediaProductId}")
    public ResponseEntity<MediaProductResponse> update(
            @PathVariable(name = "mediaProductId") Long mediaProductId,

            @Valid @RequestBody MediaProductRequest request) {

        var mediaProduct = mediaProductMapper.toDomain(mediaProductId, request);
        var mediaProductUpdated = mediaProductUseCase.update(mediaProduct);

        return ResponseEntity.ok(mediaProductMapper.toMediaProductResponse(mediaProductUpdated));
    }

    @Operation(summary = "Get all Media Products order by release date and views")
    @GetMapping
    public ResponseEntity<Page<MediaProductResponse>> getAll(
            @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(name = "orderBy") MediaProductSortBy orderBy,
            @RequestParam(name = "orderDir") Sort.Direction orderDir) {

        var pageRequestDTO = new PageRequestDTO(pageNumber, pageSize, orderDir, orderBy);
        var domainPage = mediaProductUseCase.getAll(pageRequestDTO);

        return ResponseEntity.ok(mediaProductMapper.toMediaProductResponsePage(domainPage));
    }

    @Operation(summary = "Find Media Products by filters")
    @GetMapping("/search")
    public ResponseEntity<Page<MediaProductResponse>> search(
            @RequestParam(name = "views", required = false) Integer views,

            @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
            @RequestParam(name = "from", required = false)
            LocalDateTime from,

            @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
            @RequestParam(name = "to", required = false)
            LocalDateTime to,

            @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(name = "orderBy") MediaProductSortBy orderBy,
            @RequestParam(name = "orderDir") Sort.Direction orderDir) {

        var pageRequest = new PageRequestDTO(pageNumber, pageSize, orderDir, orderBy);
        var searchRequest = new MediaProductSearchRequestDTO(views, from, to, pageRequest);
        var domainPage = mediaProductUseCase.search(searchRequest);

        return ResponseEntity.ok(mediaProductMapper.toMediaProductResponsePage(domainPage));
    }

}
