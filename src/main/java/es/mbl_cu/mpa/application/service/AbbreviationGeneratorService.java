package es.mbl_cu.mpa.application.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AbbreviationGeneratorService {

    public String generate(String name) {
        if (name == null || name.isBlank()) return "";

        String[] words = name.trim().split("[\\s:]+");

        return switch (words.length) {
            case 1 -> abbreviateSingleWord(words[0]);
            case 2 -> abbreviateTwoWords(words[0], words[1]);
            default -> abbreviateMultipleWords(words);
        };
    }

    private String abbreviateSingleWord(String word) {
        return new StringBuilder()
                .append(word, 0, Math.min(3, word.length()))
                .toString()
                .toUpperCase();
    }

    private String abbreviateTwoWords(String first, String second) {
        return new StringBuilder()
                .append(first, 0, Math.min(2, first.length()))
                .append(second.charAt(0))
                .toString()
                .toUpperCase();
    }

    private String abbreviateMultipleWords(String[] words) {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(words)
                .filter(w -> !w.isEmpty())
                .forEach(w -> sb.append(w.charAt(0)));
        return sb.toString().toUpperCase();
    }

}
