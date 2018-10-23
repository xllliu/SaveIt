package seedu.address.model.issue;

import java.util.Comparator;

import seedu.address.model.Issue;

/**
 * Comparator used to sort the Issues in chronological order
 */
public class IssueChroSort implements Comparator<Issue> {
    public int compare(Issue a, Issue b) {
        return b.getFrequency().compare(a.getFrequency());
    }
}
