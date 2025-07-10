package com.thesplum.ssp.parser.text;

import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

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

    /**
     * Convert a SQL statement to its parameterized version.
     * 
     * @param statement plain SQL statement.
     * @return Parameterized version of statement.
     */
    public static String parameterize(String statement) {
        String stmt = cleanStatement(statement);
        StringBuilder sb = new StringBuilder();
        ParameterAutomata pa = (new TextUtils()).new ParameterAutomata();

        int state = 1;
        boolean isReplaced = false;
        for (char c : stmt.toCharArray()) {
            state = pa.transition(state, c);
            if (pa.isAccept(state)) {
                sb.append(c);
                isReplaced = false;
            } else {
                if (!isReplaced) {
                    sb.append('?');
                    isReplaced = true;
                }
            }
        }
        return sb.toString();
    }

    class ParameterAutomata {
        private int[] acceptState = {1, 2, 3, 6};
        private Pattern space = Pattern.compile("[\\s]");
        private Pattern quote = Pattern.compile("[']");
        private Pattern dot = Pattern.compile(".");
        private Pattern setD = Pattern.compile("[\\d]");
        private Pattern setS = Pattern.compile("[=><]");
        private Predicate<String> spaceTester = space.asPredicate();
        private Predicate<String> quoteTester = quote.asPredicate();
        private Predicate<String> dotTester = dot.asPredicate();
        private Predicate<String> dTester = setD.asPredicate();
        private Predicate<String> sTester = setS.asPredicate();
        private Predicate<String> aTester = sTester.negate();

        public int transition(int state, char token) {
            int newState = state;
            String tkn = String.valueOf(token);

            if (state == 1) {
                if (aTester.test(tkn)) {
                    newState = 1;
                } else {
                    newState = 2;
                }
            } else if (state == 2) {
                if (sTester.test(tkn)) {
                    newState = 2;
                } else if (spaceTester.test(tkn)) {
                    newState = 3;
                } else if (quoteTester.test(tkn)) {
                    newState = 4;
                } else if (dTester.test(tkn)) {
                    newState = 7;
                } else {
                    throw new IllegalStateException("Transition not supported for char: " + tkn + " in state: " + state);
                }
            } else if (state == 3) {
                if (spaceTester.test(tkn)) {
                    newState = 3;
                } else if (quoteTester.test(tkn)) {
                    newState = 4;
                } else {
                    newState = 7;
                }
            } else if (state == 4) {
                if (quoteTester.test(tkn)) {
                    newState = 5;
                } else {
                    newState = 4;
                }
            } else if (state == 5) {
                if (spaceTester.test(tkn)) {
                    newState = 6;
                }
            } else if (state == 6) {
                if (aTester.test(tkn)) {
                    newState = 1;
                }
            } else {
                if (spaceTester.test(tkn)) {
                    newState = 1;
                } else {
                    newState = 7;
                }
            }

            return newState;
        }

        /**
         * Test if the state is of acceptation.
         * 
         * @param state The number of state to check.
         * @return If state is of acceptation return true, else return false.
         */
        public boolean isAccept(int state) {
            return IntStream.of(acceptState).anyMatch(x -> x == state);
        }
    }
}
