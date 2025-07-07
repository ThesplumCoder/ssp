package com.thesplum.ssp.statement;

import java.util.List;

import com.thesplum.ssp.parser.tokenizer.Token;
import com.thesplum.ssp.statement.dml.clause.Clause;

public abstract class Statement extends SqlPlain {
    private List<Clause> clauses;
    private List<Token> tokens;
    
    public Statement(String plainStatement) {
        setContent(plainStatement);
    }

    public Statement(List<Token> tokens) {
        setTokens(tokens);
    }

    public void setClauses(List<Clause> clauses) {
        if (clauses != null) {
            this.clauses = clauses;
        }
    }

    public List<Clause> getClauses() {
        return clauses;
    }

    public void setTokens(List<Token> tokens) {
        if (tokens != null && !tokens.isEmpty()) {
            this.tokens = tokens;
        } else {
            throw new NullPointerException("The list of token for statement is null or empty.");
        }
    }

    public List<Token> getTokens() {
        return tokens;
    }
}
