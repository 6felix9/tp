package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that the person's phone number contains the keyword specified by the user in the command
 */
public class PhoneContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public PhoneContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        if (keywords.isEmpty()) {
            return true;
        }
        return keywords.stream()
            .anyMatch(keyword -> person.getPhone().value.contains(keyword));
    }
}
