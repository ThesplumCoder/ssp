package com.thesplum.ssp.parser.text;

public final class TextUtils {

    /**
     * Remove spaces, trailing symbols and semicolons present in the sides of 
     * the statement.
     * 
     * @param statement The statement to clean.
     * @return Cleaned statement.
     * @throws NullPointerException If the statement is null or is blank (empty).
     */
    public static String cleanStatement(String statement) throws NullPointerException {
        String res;
        if (statement == null || statement.isBlank()) {
            throw new NullPointerException("The statement is a null reference or it's blank.");
        }
        res = statement.strip();
        res = cleanSemicolon(res);

        return res;
    }

    /**
     * Remove the semicolon if exists in the statement.
     * 
     * This method assumes clean statement of final spaces, so that the last 
     * symbol is semicolon.
     * 
     * @param statement The statement with/without the semicolon.
     * @return The statement text without semicolon.
     */
    private static String cleanSemicolon(String statement) {
        String res = statement;
        if (statement.endsWith(";")) {
            return statement.substring(0, statement.length() - 1);
        }

        return res;
    }

}
