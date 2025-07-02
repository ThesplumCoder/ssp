package com.thesplum.ssp.statement.dml.element;

import com.thesplum.ssp.statement.dml.DmlBase;

public class Alias extends DmlBase {
    private static final String keyword = "AS";

    public Alias() {
        //
    }

    public Alias(String elementWithAlias) {
        setContent(elementWithAlias);
    }

    private void parseTable(Table table) {
        // WIP
    }
}
