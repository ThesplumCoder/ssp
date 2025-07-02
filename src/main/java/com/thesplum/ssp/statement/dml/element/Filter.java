package com.thesplum.ssp.statement.dml.element;

import com.thesplum.ssp.statement.SqlPlain;

public class Filter extends SqlPlain implements Element {
    
    public Filter(String plainFilter) {
        super(plainFilter);
    }
}
