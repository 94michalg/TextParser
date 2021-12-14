package com.gorzkowicz;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest {

    List<Sentence> sentences;

    @Before
    public void before() {
        sentences = new ArrayList<>();
        sentences.add(new Sentence("Lorem ipsum dolor sit amet, consectetur adipiscing elit."));
        sentences.add(new Sentence("Nulla bibendum, lacus vel blandit maximus, mi magna porttitor nibh, vitae placerat nibh erat ac augue?"));
        sentences.add(new Sentence("      Aenean    ,    rhoncus      semper      ullamcorper      ."));
    }

    @Test
    public void checkParsersForNullArgument() {
        Assert.assertNull(App.parseListToCSV(null));
        Assert.assertNull(App.parseListToXML(null));
    }


    @Test
    public void checkXMLParserResult() {
        String expected =
                        "<text>\n" +
                        "  <sentence>\n" +
                        "    <word>adipiscing</word>\n" +
                        "    <word>amet</word>\n" +
                        "    <word>consectetur</word>\n" +
                        "    <word>dolor</word>\n" +
                        "    <word>elit</word>\n" +
                        "    <word>ipsum</word>\n" +
                        "    <word>Lorem</word>\n" +
                        "    <word>sit</word>\n" +
                        "  </sentence>\n" +
                        "  <sentence>\n" +
                        "    <word>ac</word>\n" +
                        "    <word>augue</word>\n" +
                        "    <word>bibendum</word>\n" +
                        "    <word>blandit</word>\n" +
                        "    <word>erat</word>\n" +
                        "    <word>lacus</word>\n" +
                        "    <word>magna</word>\n" +
                        "    <word>maximus</word>\n" +
                        "    <word>mi</word>\n" +
                        "    <word>nibh</word>\n" +
                        "    <word>nibh</word>\n" +
                        "    <word>Nulla</word>\n" +
                        "    <word>placerat</word>\n" +
                        "    <word>porttitor</word>\n" +
                        "    <word>vel</word>\n" +
                        "    <word>vitae</word>\n" +
                        "  </sentence>\n" +
                        "  <sentence>\n" +
                        "    <word>Aenean</word>\n" +
                        "    <word>rhoncus</word>\n" +
                        "    <word>semper</word>\n" +
                        "    <word>ullamcorper</word>\n" +
                        "  </sentence>\n" +
                        "</text>";

        Assert.assertEquals(expected, App.parseListToXML(sentences));
    }

    @Test
    public void checkCSVParserResult() {
        String expected =
                        ", Word 0, Word 1, Word 2, Word 3, Word 4, Word 5, Word 6, Word 7, Word 8, Word 9, Word 10, Word 11, Word 12, Word 13, Word 14, Word 15" + System.lineSeparator() +
                        "Sentence 1, adipiscing, amet, consectetur, dolor, elit, ipsum, Lorem, sit" + System.lineSeparator() +
                        "Sentence 2, ac, augue, bibendum, blandit, erat, lacus, magna, maximus, mi, nibh, nibh, Nulla, placerat, porttitor, vel, vitae" + System.lineSeparator() +
                        "Sentence 3, Aenean, rhoncus, semper, ullamcorper" + System.lineSeparator();

        Assert.assertEquals(expected, App.parseListToCSV(sentences));
    }

    @Test
    public void checkSentencesParsing() {
        App.parseLineIntoObjects("Mary had a little lamb. Peter called for a ");

        Assert.assertEquals(1, App.sentences.size());

        App.parseLineIntoObjects("wolf, and Aesop came. Cinderella likes ");

        Assert.assertEquals(2, App.sentences.size());

        App.parseLineIntoObjects("shoes.");

        Assert.assertEquals(3, App.sentences.size());

        Assert.assertEquals(new Sentence("Cinderella likes shoes"), App.sentences.get(2));
    }

}
