package com.thesplum.ssp.statement.dml;

import java.util.List;

import com.thesplum.ssp.parser.tokenizer.Token;
import com.thesplum.ssp.statement.Statement;
import com.thesplum.ssp.statement.dml.element.Element;

public class SelectStatement extends Statement implements Element {
    
    public SelectStatement(String selectStatement) {
        super(selectStatement);
    }

    public SelectStatement(List<Token> tokens) {
        super(tokens);
    }
}
