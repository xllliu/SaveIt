package seedu.address.model.issue.solution;

import seedu.address.commons.util.AppUtil;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Issue's remark in saveit.
 */
public class Remark {

    public static final String MESSAGE_REMARK_CONSTRAINTS =
            "Remarks can take any values, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String REMARK_VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code Remark}.
     *
     * @param remark A valid remark.
     */
    public Remark(String remark) {
        requireNonNull(remark);
        AppUtil.checkArgument(isValidRemark(remark), MESSAGE_REMARK_CONSTRAINTS);
        value = remark;
    }

    /**
     * Returns true if a given string is a valid remark.
     */
    public static boolean isValidRemark(String test) {
        return test.matches(REMARK_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Remark // instanceof handles nulls
                && value.equals(((Remark) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
