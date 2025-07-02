package com.thesplum.ssp.statement.dml.element;

import com.thesplum.ssp.statement.dml.DmlBase;

public class Table extends DmlBase {
    
    public Table(String tableElement) {
        setContent(tableElement);
    }
}
