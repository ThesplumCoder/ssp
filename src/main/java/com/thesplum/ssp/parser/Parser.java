package com.thesplum.ssp.parser;

import com.thesplum.ssp.statement.Statement;
import static com.thesplum.ssp.parser.TextUtils.cleanStatement;

public final class Parser {

    public Statement parseStatement(String statement) {
        Statement res = null;
        String stmt = cleanStatement(statement);
        // Divide the statement in elements.

        return res;
    }
    
}
