package com.thesplum.ssp.statement;


public abstract class SqlPlain {
    private String content;

    public SqlPlain() {
        // Bean requirement.
    }

    public SqlPlain(String content) {
        setContent(content);
    }

    public void setContent(String content) {
        if (content != null && !content.isBlank()) {
            this.content = content.strip();
        }
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return getContent();
    }
}
