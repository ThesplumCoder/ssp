package com.thesplum.ssp.statement.dml.element;

import com.thesplum.ssp.statement.SqlPlain;

public class Table extends SqlPlain implements Element {
    
    public Table(String tableElement) {
        setContent(tableElement);
    }
}
