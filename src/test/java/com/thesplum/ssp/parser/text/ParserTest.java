package com.thesplum.ssp.parser.text;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.thesplum.ssp.parser.text.tokenizer.Token;
import com.thesplum.ssp.parser.text.tokenizer.Tokenizer;
import com.thesplum.ssp.parser.text.tokenizer.TypeToken;

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

    @Test
    void identifyTokenTest() {
        ArrayList<Token> tests = new ArrayList<>(
            Arrays.<Token>asList(new Token("select", TypeToken.KEYWORD), 
                                new Token("by", TypeToken.KEYWORD), 
                                new Token("id", TypeToken.VALUE), 
                                new Token("AVG", TypeToken.FUNCTION),
                                new Token("@", TypeToken.SYMBOL))
        );

        for (Token test : tests) {
            assertEquals(test, parser.identifyToken(test.getText()));
        }
    }

    //@Test
    void identifyStatementTest() {
        String query = "SELECT name FROM Cars c WHERE model='Newer sport'";
        ArrayList<Token> tokens = new ArrayList<>();

        for (String s : Tokenizer.getTokens(query)) {
            tokens.add(parser.identifyToken(s));
        }

        assertTrue(true);
    }
}
