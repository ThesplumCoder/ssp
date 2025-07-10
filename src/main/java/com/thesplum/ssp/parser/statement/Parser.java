package com.thesplum.ssp.parser.statement;

import java.util.List;
import com.thesplum.ssp.parser.statement.exception.BadStructureStatement;
import com.thesplum.ssp.parser.text.tokenizer.Token;
import com.thesplum.ssp.statement.Statement;
import com.thesplum.ssp.statement.StatementType;

public final class Parser implements StatementParser {
    private Statement stmt;

    /**
     * Construc a parser will use the tokens to make a statement.
     * 
     * @see com.thesplum.ssp.statement.Statement
     * @param tokens List of tokens to make a statement.
     */
    public Parser(List<Token> tokens) {
        stmt = new Statement(tokens);
        identifyStatement();
    }

    @Override
    public Statement parseStatement() {
        if (stmt.getType() == null) {
            throw BadStructureStatement.getNoTypeStatement();
        }

        // Depending on the statement, this internally use it's parser of elements.

        return stmt;
    }

    @Override
    public void identifyStatement() {
        String head = stmt.getTokens().get(0).getText();

        if (StatementType.SELECT.tester().test(head)) {
            stmt.setType(StatementType.SELECT);
        } else if (StatementType.INSERT.tester().test(head)) {
            stmt.setType(StatementType.INSERT);
        } else if (StatementType.UPDATE.tester().test(head)) {
            stmt.setType(StatementType.UPDATE);
        } else if (StatementType.DELETE.tester().test(head)) {
            stmt.setType(StatementType.DELETE);
        } else {
            throw new UnsupportedOperationException("The statement isn't supported.");
        }
    }
}
