package es.mbl_cu.mpa.infrastructure.persistence.repository;

import es.mbl_cu.mpa.infrastructure.persistence.entity.MediaProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaMediaProductRepository extends JpaRepository<MediaProductEntity, Long>, JpaSpecificationExecutor<MediaProductEntity> {
}
