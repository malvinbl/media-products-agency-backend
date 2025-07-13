package es.mbl_cu.mpa.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Table(name = "media_product")
@Entity
public class MediaProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "release_date")
    private LocalDateTime releaseDate;

    @Column(name = "views")
    private Long views;

    @Column(name = "length")
    private Integer length;

    @Column(name = "abbreviation", nullable = false, length = 25)
    private String abbreviation;

    @Setter
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity categoryEntity;

    public MediaProductEntity(Long id, String name, LocalDateTime releaseDate, Long views, Integer length, String abbreviation) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.views = views;
        this.length = length;
        this.abbreviation = abbreviation;
    }

}
