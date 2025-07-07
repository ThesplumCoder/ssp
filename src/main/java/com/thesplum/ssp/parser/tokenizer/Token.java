package com.thesplum.ssp.parser.tokenizer;

/**
 * Models the form of each token found by parser.
 * 
 * @author Anderson Acuña.
 */
public class Token {
    String text;
    TypeToken typeToken;

    /**
     * Construct a token.
     * 
     * @param text Text to represent token.
     * @param typeToken Type of token.
     * @throws NullPointerException If any param is null.
     */
    public Token(String text, TypeToken typeToken) throws NullPointerException {
        setText(text);
        setTypeToken(typeToken);
    }

    public void setText(String text) throws NullPointerException {
        if (text == null) {
            throw new NullPointerException("The text of token can't be null.");
        }
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setTypeToken(TypeToken typeToken) {
        if (typeToken == null) {
            throw new NullPointerException("The type of token can't be null.");
        }
        this.typeToken = typeToken;
    }

    public TypeToken getTypeToken() {
        return typeToken;
    }

    @Override
    public boolean equals(Object otherToken) {
        Token t = (Token) otherToken;
        if (text.equals(t.getText()) && typeToken.equals(t.getTypeToken())) {
            return true;
        } else {
            return false;
        }
    }
}
