package seedu.multitasky.commons.util.match;

// @@author A0125586X
/**
 * Class that performs prefix matching.
 */
public class PrefixMatch implements Match {

    public String match(final String input, final String... potentialMatches) {
        if (input == null || potentialMatches == null || potentialMatches.length == 0) {
            return null;
        }
        return MatchUtil.getRegexMatch(getRegex(input), potentialMatches);
    }

    public boolean isMatch(final String input, final String potentialMatch) {
        if (input == null || potentialMatch == null) {
            return false;
        }
        return MatchUtil.getRegexMatch(getRegex(input), potentialMatch) != null;
    }

    private static String getRegex(final String input) {
        return input + MatchUtil.REGEX_PRESENT_NON_WHITESPACE;
    }

}
