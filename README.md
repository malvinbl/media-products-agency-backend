# Media Products Agency backend

## Overview
The repo contains the implementation of a Rest API that provides a solution to the Media Products Agency Challenge.

"Media Products Agency Challenge"

There is an agency in charge to insert media products.

Each product has category: Trailer TV, Outdoors, Trailer Movie, Internet, etc.

Each category has a flag “hasLength”.

If product category has a “hasLength” user need to insert the length of the product. (2)

Product has: Name, Type, release date, insert date. Number of views. Abbreviation. (1)

```
```

Need to provide a rest endpoint to list (with search and order by release date and views, desc and asc), add, delete and edit advertisements.

Mandatory:
- Use Spring/Spring boot
- Add unit test

```
```

(1) If the user don’t insert the Abbreviation we need to generate it.
The Abbreviation will be in base of the product name.
The Abbreviation will has at least 3 characters. 
Ex:
- Spiderman: SPI

- New Things: NET

- Five Feed Apart: FFA

- Spiderman the invisible men: STIM


(2) The format will insert in seconds and we need to format to hh:mm:ss
- If the movie has less than 1h will be mm:ss

- If has less than 1 min will be ss

## Guidelines

1. Clone this repository

2. Go to the project directory

3. Run the project
```sh
./mvnw spring-boot:run
```

### Swagger
http://localhost:8080/api/v1/swagger-ui/index.html

## More Info
It is used:

Maven

Java 21

Spring Boot v3.5.3

In memory H2 Data Base
