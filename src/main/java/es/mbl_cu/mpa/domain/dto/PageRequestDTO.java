package es.mbl_cu.mpa.domain.dto;

import es.mbl_cu.mpa.domain.vo.MediaProductSortBy;
import org.springframework.data.domain.Sort;

public record PageRequestDTO(
        int pageNumber,
        int pageSize,
        Sort.Direction orderDir,
        MediaProductSortBy orderBy) {
}
