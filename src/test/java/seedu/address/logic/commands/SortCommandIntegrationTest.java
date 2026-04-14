package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.tag.TagContainsKeywordsPredicate;

public class SortCommandIntegrationTest {

    @Test
    public void execute_sortFilteredThenList_restoresOriginalFullOrder() throws Exception {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        List<Person> originalFullOrder = List.copyOf(model.getFilteredPersonList());

        new FindCommand(new TagContainsKeywordsPredicate(List.of("friends"))).execute(model);

        List<Person> expectedSortedSubset = model.getFilteredPersonList().stream()
                .sorted(Comparator.comparing(
                        (Person person) -> person.getName().toString().toLowerCase()).reversed())
                .collect(Collectors.toList());

        new SortCommand(SortCommand.SortField.NAME, null, SortCommand.SortOrder.DESC).execute(model);

        assertEquals(expectedSortedSubset, List.copyOf(model.getFilteredPersonList()));

        new ListCommand().execute(model);

        assertEquals(originalFullOrder, List.copyOf(model.getFilteredPersonList()));
    }
}