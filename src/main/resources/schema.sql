CREATE SEQUENCE category_seq START WITH 1;

CREATE TABLE IF NOT EXISTS category
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    name       VARCHAR(50) NOT NULL,
    has_length  BOOLEAN,
    CONSTRAINT pk_category PRIMARY KEY (id)
);


CREATE SEQUENCE media_product_seq START WITH 1;

CREATE TABLE IF NOT EXISTS media_product
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    name          VARCHAR(50) NOT NULL,
    release_date  TIMESTAMP,
    views         BIGINT,
    length        BIGINT,
    abbreviation  VARCHAR(25) NOT NULL,
    category_id   BIGINT NOT NULL,
    CONSTRAINT pk_media_product PRIMARY KEY (id),
    FOREIGN KEY (category_id) REFERENCES category(id)
);
