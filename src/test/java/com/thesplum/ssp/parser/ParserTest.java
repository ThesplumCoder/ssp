package com.thesplum.ssp.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class ParserTest {
    Parser parser = new Parser();
    
    @Test
    void isKeywordTest() {
        ArrayList<String> tests = new ArrayList<>(
            Arrays.<String>asList("select", "From", "WHERE", "aS", "group"));

        for (String test : tests) {
            assertTrue(parser.isKeyword(test));
        }
    }

    @Test
    void isFunctionTest() {
        ArrayList<String> tests = new ArrayList<>(
            Arrays.<String>asList("curdate", "date", "CONCaT", "noW", "avg")
        );

        for (String test : tests) {
            assertTrue(parser.isFunction(test));
        }
    }

    @Test
    void isSymbolTest() {
        ArrayList<String> tests = new ArrayList<>(
            Arrays.asList(".", ",", "-", "@", "(", ")", "'", "=", "<", ">", "*", "+", "\"")
        );

        for (String test : tests) {
            assertTrue(parser.isSymbol(test));
        }
    }
}
