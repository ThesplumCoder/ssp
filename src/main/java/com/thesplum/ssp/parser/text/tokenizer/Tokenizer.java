package com.thesplum.ssp.parser.text.tokenizer;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Tokenizer {

    /**
     * Split text in each space, also remove additional spaces.
     * @param text Text to split.
     * @return List of strings without spaces.
     */
    private static LinkedList<String> spaceSplit(String text) {
        LinkedList<String> res = new LinkedList<>();

        for (String piece : text.split(" ")) {
            if (!piece.isBlank()) {
                res.add(piece);
            }
        }
        return res;
    }

    /**
     * Split a string with symbols in pieces with alphanumeric and symbols 
     * isolated.
     * @param str String with/without symbols.
     * @return List of strings of alphanumeric or symbol.
     */
    private static LinkedList<String> symbolStringSplit(String str) {
        LinkedList<String> res = new LinkedList<>();
        boolean isFirst = true;
        boolean lastIsSymbol = false;

        Pattern pattern = Pattern.compile("[\\w]");
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            Matcher matcher = pattern.matcher(String.valueOf(c));
            if (isFirst) {
                if (matcher.find()) {
                    sb.append(c);
                } else {
                    res.add(String.valueOf(c));
                    lastIsSymbol = true;
                }
                isFirst = false;
            } else {
                if (matcher.find()) {
                    sb.append(c);
                    lastIsSymbol = false;
                } else if (lastIsSymbol) {
                    res.add(String.valueOf(c));
                } else {
                    // The case of the last char is alphanumeric and found symbol.
                    res.add(sb.toString());
                    res.add(String.valueOf(c));
                    sb.setLength(0);
                    lastIsSymbol = true;
                }
            }
        }
        if (sb.length() > 0) {
            res.add(sb.toString());
        }

        return res;
    }

    /**
     * Split each piece to string of alphanumeric or symbol.
     * 
     * @param pieces List of strings that can be contain symbols.
     * @return List of strings that can be alphanumeric or just a symbol.
     */
    private static LinkedList<String> symbolSplit(LinkedList<String> pieces) {
        LinkedList<String> res = new LinkedList<>();
        
        for (String piece : pieces) {
            res.addAll(symbolStringSplit(piece));
        }

        return res;
    }


    /**
     * Separate a statement in valid tokens.
     * 
     * @param statement Split the statement into tokens.
     * @return List of tokens as strings.
     */
    public static LinkedList<String> getTokens(String statement) {
        LinkedList<String> res = symbolSplit(spaceSplit(statement));
        return res;
    }
}
