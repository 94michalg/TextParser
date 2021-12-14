package com.gorzkowicz;

import com.thoughtworks.xstream.XStream;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {

    public static final List<Sentence> sentences = new ArrayList<>();
    public static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {

        try (BufferedReader br =
                     new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            while ((line = br.readLine()) != null) {
                parseLineIntoObjects(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(parseListToCSV(sentences));
        System.out.println(parseListToXML(sentences));
    }

    public static void parseLineIntoObjects(String line) {
        // Pattern to extract single sentences
        Pattern pattern = Pattern.compile(".*?[.?!]");
        Matcher matcher;

        // If StringBuilder contains some text from previous iteration append it to current line
        if (!sb.toString().isBlank()) {
            sb.append(line);
            line = sb.toString();
            sb.setLength(0);
        }

        matcher = pattern.matcher(line);
        int endIndexOfLastMatch = 0;
        while (matcher.find()) {
            sentences.add(new Sentence(matcher.group()));
            endIndexOfLastMatch = matcher.end();
        }

        // Saving rest of the line (text after last dot) to StringBuilder
        if (endIndexOfLastMatch != line.length()) {
            sb.append(line.substring(endIndexOfLastMatch));
        }
    }

    public static String parseListToXML(List<Sentence> sentences) {
        if (sentences == null) return null;

        XStream xstream = new XStream();
        xstream.alias("text", List.class);
        xstream.alias("sentence", Sentence.class);
        xstream.addImplicitCollection(Sentence.class, "words");
        xstream.alias("word", String.class);
        return xstream.toXML(sentences);
    }

    public static String parseListToCSV(List<Sentence> sentences) {
        if (sentences == null) return null;

        StringBuilder csv = new StringBuilder();
        for (int i = 0; i < Sentence.maxWordsNumber; i++) {
            csv.append(String.format(", Word %d", i));
        }
        csv.append(System.lineSeparator());
        int id = 1;
        for (Sentence sentence : sentences) {
            csv.append(String.format("Sentence %d", id++));
            for (String word : sentence.getWords()) {
                csv.append(String.format(", %s", word));
            }
            csv.append(System.lineSeparator());
        }
        return csv.toString();
    }
}
