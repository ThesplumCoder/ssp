package com.thesplum.ssp.parser.text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import com.thesplum.ssp.parser.text.tokenizer.Token;
import com.thesplum.ssp.parser.text.tokenizer.TokenParser;
import com.thesplum.ssp.parser.text.tokenizer.TypeToken;

public final class Parser implements TokenParser {

    @Override
    public Token identifyToken(String text) {
        Token res;

        if (isSymbol(text)) {
            res = new Token(text, TypeToken.SYMBOL);
        } else if (isKeyword(text)) {
            res = new Token(text, TypeToken.KEYWORD);
        } else if (isFunction(text)) {
            res = new Token(text, TypeToken.FUNCTION);
        } else {
            res = new Token(text, TypeToken.VALUE);
        }

        return res;
    }

    @Override
    public boolean isKeyword(String text) {
        boolean res = false;
        ArrayList<String> members = new ArrayList<>(
            Arrays.<String>asList("SELECT", "FROM", "WHERE", "AS", "JOIN", "ON", "GROUP", "BY", "LIMIT")
        );

        String membersRegex = String.join("|", members);
        Predicate<String> isMember = Pattern.compile(membersRegex, 2).asPredicate();

        res = isMember.test(text);
        return res;
    }

    @Override
    public boolean isFunction(String text) {
        boolean res = false;
        ArrayList<String> members = new ArrayList<>(
            Arrays.<String>asList("DATE", "AVG", "CONCAT", "CONCAT_WS", "NOW", "CURDATE", "CURRENT_DATE")
        );

        String membersRegex = String.join("|", members);
        Predicate<String> isMember = Pattern.compile(membersRegex, 2).asPredicate();

        res = isMember.test(text);
        return res;
    }

    @Override
    public boolean isSymbol(String text) {
        boolean res = false;
        Predicate<String> isMember = Pattern.compile("\\W").asPredicate();

        res = isMember.test(text);
        return res;
    }
}
