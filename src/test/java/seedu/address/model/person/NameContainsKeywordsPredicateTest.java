package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class NameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        NameContainsKeywordsPredicate firstPredicate = new NameContainsKeywordsPredicate(firstPredicateKeywordList);
        NameContainsKeywordsPredicate secondPredicate = new NameContainsKeywordsPredicate(secondPredicateKeywordList);

        assertTrue(firstPredicate.equals(firstPredicate));
        assertTrue(firstPredicate.equals(new NameContainsKeywordsPredicate(firstPredicateKeywordList)));
        assertFalse(firstPredicate.equals(1));
        assertFalse(firstPredicate.equals(null));
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // Full name match
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Any keyword matches
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Carol").build()));

        // Case-insensitive match
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Partial / substring match
        predicate = new NameContainsKeywordsPredicate(Collections.singletonList("Han"));
        assertTrue(predicate.test(new PersonBuilder().withName("Hans").build()));

        predicate = new NameContainsKeywordsPredicate(Collections.singletonList("ali"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Empty keywords list matches everyone
        predicate = new NameContainsKeywordsPredicate(Collections.emptyList());
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        predicate = new NameContainsKeywordsPredicate(Arrays.asList("92345678", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("92345678")
            .withEmail("alice@email.com").withAddress("Main Street").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(keywords);

        String expected = NameContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
