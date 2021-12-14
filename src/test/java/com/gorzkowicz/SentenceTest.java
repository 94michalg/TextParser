package com.gorzkowicz;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class SentenceTest {


    @Test
    public void testConstructor() {
        Sentence sentence = new Sentence("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        List<String> expected = List.of("adipiscing", "amet", "consectetur", "dolor", "elit", "ipsum", "Lorem", "sit");

        Assert.assertEquals(expected, sentence.getWords());
    }

    @Test
    public void testConstructor2() {
        Sentence sentence = new Sentence("      Aenean    ,    rhoncus      semper      ullamcorper      .      ");
        List<String> expected = List.of("Aenean", "rhoncus", "semper", "ullamcorper");

        Assert.assertEquals(expected, sentence.getWords());
    }

    @Test
    public void testMaxNumberOfWords() {
        Sentence sentence = new Sentence("      Aenean    ,    rhoncus      semper      ullamcorper      .      ");
        Assert.assertEquals(4, Sentence.maxWordsNumber);

        Sentence sentence2 = new Sentence("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        Assert.assertEquals(8, Sentence.maxWordsNumber);

    }
}
