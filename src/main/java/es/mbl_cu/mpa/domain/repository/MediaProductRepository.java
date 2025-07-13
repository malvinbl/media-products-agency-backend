package es.mbl_cu.mpa.domain.repository;

import es.mbl_cu.mpa.domain.dto.MediaProductSearchDTO;
import es.mbl_cu.mpa.domain.model.MediaProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface MediaProductRepository {

    MediaProduct save(MediaProduct mediaProduct);
    Page<MediaProduct> findAll(PageRequest pageRequest);
    boolean exists(Long mediaProductId);
    Page<MediaProduct> search(MediaProductSearchDTO mediaProductSearch);

}
