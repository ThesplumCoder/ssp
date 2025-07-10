package com.thesplum.ssp.parser.statement.element;

import com.thesplum.ssp.parser.text.tokenizer.Token;

public interface ElementParser {
    
    Element tokenToElement(Token token);
}
