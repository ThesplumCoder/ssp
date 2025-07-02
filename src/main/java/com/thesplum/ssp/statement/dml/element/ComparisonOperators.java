package com.thesplum.ssp.statement.dml.element;

public enum ComparisonOperators {
    EQUALS("=");

    private String text;

    ComparisonOperators(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
