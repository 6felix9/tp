package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s available days contain any of the keywords given.
 * Keyword matching is case-insensitive.
 */
public class AvailableDayContainsKeywordsPredicate implements Predicate<Person> {

    private final List<String> keywords;

    public AvailableDayContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        if (keywords.isEmpty()) {
            return true;
        }
        return keywords.stream()
                .anyMatch(keyword -> person.getAvailableDays().stream()
                        .anyMatch(day -> day.availableDay.equalsIgnoreCase(keyword)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof AvailableDayContainsKeywordsPredicate)) {
            return false;
        }
        AvailableDayContainsKeywordsPredicate otherPredicate = (AvailableDayContainsKeywordsPredicate) other;
        return keywords.equals(otherPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("keywords", keywords)
                .toString();
    }
}
