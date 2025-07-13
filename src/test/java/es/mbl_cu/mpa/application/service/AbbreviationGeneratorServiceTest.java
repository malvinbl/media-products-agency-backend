package es.mbl_cu.mpa.application.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbbreviationGeneratorServiceTest {

    private AbbreviationGeneratorService abbreviationGeneratorService;

    @BeforeEach
    void setUp() {
        abbreviationGeneratorService = new AbbreviationGeneratorService();
    }

    @Test
    void shouldGenerateAbbreviationForSingleWord() {
        assertEquals("SPI", abbreviationGeneratorService.generate("Spiderman"));
        assertEquals("FOO", abbreviationGeneratorService.generate("FooBar"));
        assertEquals("AB", abbreviationGeneratorService.generate("Ab"));
    }

    @Test
    void shouldGenerateAbbreviationForTwoWords() {
        assertEquals("NET", abbreviationGeneratorService.generate("New Things"));
        assertEquals("HEA", abbreviationGeneratorService.generate("Hello Africa"));
    }

    @Test
    void shouldGenerateAbbreviationForMultipleWords() {
        assertEquals("FFA", abbreviationGeneratorService.generate("Five Feed Apart"));
        assertEquals("STIM", abbreviationGeneratorService.generate("Spiderman the invisible men"));
        assertEquals("ABCDEF", abbreviationGeneratorService.generate("A B C D E F"));
    }

    @Test
    void shouldReturnEmptyStringForNullOrBlank() {
        assertEquals("", abbreviationGeneratorService.generate(null));
        assertEquals("", abbreviationGeneratorService.generate(""));
        assertEquals("", abbreviationGeneratorService.generate("  "));
    }

    @Test
    void shouldHandleSpecialDelimitersLikeColon() {
        assertEquals("STIM", abbreviationGeneratorService.generate("Spiderman: the invisible men"));
    }

}
