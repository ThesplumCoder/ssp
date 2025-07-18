package com.thesplum.ssp.process;

import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import com.thesplum.ssp.automata.implementation.SpaceAutomata;

import static com.thesplum.ssp.parser.text.TextUtils.cleanStatement;

public final class Parameterize {
    
    private Parameterize() {
        // Not create instances.
    }

    /**
     * Convert a SQL statement to its parameterized version.
     * 
     * @param statement plain SQL statement.
     * @return Parameterized version of statement.
     */
    public static String parameterize(String statement) {
        String stmt = adjustSpaces(cleanStatement(statement));
        StringBuilder sb = new StringBuilder();
        ParameterAutomata pa = (new Parameterize()).new ParameterAutomata();

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

    /**
     * Adjust optional spaces among elements of statement.
     * 
     * In the statement, the additional spaces isn't taken into account and only 
     * one is conserved.
     *  
     * @param statement Statetent to adjust.
     * @return Statement with only necesary spaces between elements.
     */
    public static String adjustSpaces(String statement) {
        SpaceAutomata sp = new SpaceAutomata();
        StringBuilder sb = new StringBuilder();

        for (char c : statement.toCharArray()) {
            sp.transition(c);
            if (sp.isAccept()) {
                sb.append(c);
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
