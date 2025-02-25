package entity;

import interfaces.ICipherText;
import util.Language;

import java.util.*;

public class CipherText implements ICipherText {
    private final String text;
    private final Language language;
    private int expectedKeyLength;

    public CipherText(String text, Language language) {
        this.text = text;
        this.language = language;
        this.expectedKeyLength = 0; 
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public Language getLanguage() {
        return language;
    }

    @Override
    public Map<String, List<Integer>> findRepeatingPatterns(int length) {
        Map<String, List<Integer>> patterns = new HashMap<>();
        
        for (int i = 0; i <= text.length() - length; i++) {
            String pattern = text.substring(i, i + length);
            patterns.computeIfAbsent(pattern, k -> new ArrayList<>()).add(i);
        }
 
        patterns.entrySet().removeIf(entry -> entry.getValue().size() < 2);
        
        return patterns;
    }

    @Override
    public List<String> getSubstrings(int keyLength) {
        List<String> substrings = new ArrayList<>();
        

        StringBuilder[] builders = new StringBuilder[keyLength];
        for (int i = 0; i < keyLength; i++) {
            builders[i] = new StringBuilder();
        }
        

        for (int i = 0; i < text.length(); i++) {
            builders[i % keyLength].append(text.charAt(i));
        }
        

        for (StringBuilder builder : builders) {
            substrings.add(builder.toString());
        }
        
        return substrings;
    }

    @Override
    public void setExpectedKeyLength(int length) {
        this.expectedKeyLength = length;
    }

    @Override
    public int getExpectedKeyLength() {
        return expectedKeyLength;
    }
}
