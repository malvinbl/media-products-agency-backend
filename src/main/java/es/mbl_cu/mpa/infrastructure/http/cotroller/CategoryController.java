package es.mbl_cu.mpa.infrastructure.http.cotroller;

import es.mbl_cu.mpa.application.usecase.CategoryUseCase;
import es.mbl_cu.mpa.infrastructure.http.cotroller.dto.CategoryDTO;
import es.mbl_cu.mpa.infrastructure.http.cotroller.mapper.CategoryMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Category Controller")
@RequiredArgsConstructor
@RequestMapping("/categories")
@RestController
public class CategoryController {

    private final CategoryMapper categoryMapper;
    private final CategoryUseCase categoryUseCase;

    @Operation(summary = "Create a new Category")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<CategoryDTO> create(
            @Valid @RequestBody CategoryDTO request) {

        var category = categoryMapper.toDomain(request);
        var categoryCreated = categoryUseCase.create(category);

        return ResponseEntity.ok(categoryMapper.toCategoryDTO(categoryCreated));
    }

    @Operation(summary = "Update a given Category")
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> update(
            @PathVariable(name = "categoryId") Long categoryId,

            @Valid @RequestBody CategoryDTO request) {

        var category = categoryMapper.toDomain(categoryId, request);
        var categoryUpdated = categoryUseCase.update(category);

        return ResponseEntity.ok(categoryMapper.toCategoryDTO(categoryUpdated));
    }

    @Operation(summary = "Get all Categories")
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAll() {
        var categories = categoryUseCase.getAll();
        return ResponseEntity.ok(categoryMapper.toCategoryDTOList(categories));
    }

}
