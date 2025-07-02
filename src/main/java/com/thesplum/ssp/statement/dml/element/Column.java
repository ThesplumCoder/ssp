package com.thesplum.ssp.statement.dml.element;

import com.thesplum.ssp.statement.SqlPlain;

public class Column extends SqlPlain implements Element {

    public Column(String column) {
        super(column);
    }

}
