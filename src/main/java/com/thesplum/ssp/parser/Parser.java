package com.thesplum.ssp.parser;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import com.thesplum.ssp.statement.Statement;
import com.thesplum.ssp.statement.StatementParser;
import com.thesplum.ssp.statement.dml.SelectStatement;
import com.thesplum.ssp.parser.tokenizer.Token;
import com.thesplum.ssp.parser.tokenizer.TokenParser;
import com.thesplum.ssp.parser.tokenizer.Tokenizer;
import com.thesplum.ssp.parser.tokenizer.TypeToken;
import static com.thesplum.ssp.parser.TextUtils.cleanStatement;

public final class Parser implements TokenParser, StatementParser {

    /**
     * Parse text to statement.
     * 
     * @param statement Text to convert in corresponding statement.
     * @return The statement representation.
     */
    public Statement parseStatement(String statement) {
        String stmt = cleanStatement(statement);
        LinkedList<String> plainTokens = Tokenizer.getTokens(stmt);
        LinkedList<Token> tokens = new LinkedList<>();
        
        for (String str : plainTokens) {
            tokens.add(identifyToken(str));
        }

        return identifyStatement(tokens);
    }

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

    @Override
    public Statement identifyStatement(List<Token> tokens) {
        Token head = tokens.get(0);
        Predicate<String> isQuery = Pattern.compile("SELECT", 2).asPredicate();

        if (isQuery.test(head.getText())) {
            return new SelectStatement(tokens);
        } else {
            throw new UnsupportedOperationException("The statement isn't supported.");
        }
    }
}
