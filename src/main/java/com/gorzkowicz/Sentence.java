package com.gorzkowicz;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Setter
@ToString
public class Sentence {
    public static int maxWordsNumber = 0;
    private List<String> words;

    public Sentence (String sentence) {
        if (words == null) words = new ArrayList<>();

        // Pattern to extract single words
        Pattern p = Pattern.compile("\\w+");
        Matcher m = p.matcher(sentence);
        while (m.find()) {
            words.add(m.group());
        }
        words.sort(String.CASE_INSENSITIVE_ORDER);

        // Update maxWordsNumber
        if (words.size() > maxWordsNumber) maxWordsNumber = words.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sentence sentence = (Sentence) o;
        return Objects.equals(words, sentence.words);
    }

    @Override
    public int hashCode() {
        return Objects.hash(words);
    }
}
