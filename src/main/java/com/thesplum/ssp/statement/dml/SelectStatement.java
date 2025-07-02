package com.thesplum.ssp.statement.dml;

import com.thesplum.ssp.statement.Statement;
import com.thesplum.ssp.statement.dml.element.Element;

public class SelectStatement extends Statement implements Element {
    
    public SelectStatement(String selectStatement) {
        super(selectStatement);
    }
}
