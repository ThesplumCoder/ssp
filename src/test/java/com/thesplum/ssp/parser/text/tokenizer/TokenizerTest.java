package com.thesplum.ssp.parser.text.tokenizer;

import java.util.Arrays;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class TokenizerTest {
    String statement = "SELECT name FROM Cars c WHERE model='Newer sport'";
    LinkedList<String> expected = new LinkedList<>(
        Arrays.<String>asList("SELECT", "name", "FROM", "Cars", "c", "WHERE", "model", "=", "'", "Newer", "sport", "'"));
    
    @Test
    void getTokensTest() {
        LinkedList<String> tokens = Tokenizer.getTokens(statement);

        assertIterableEquals(expected, tokens);
    }
}
