package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.RunTiming;

/**
 * Contains integration tests for {@code AddTimingCommand}.
 */
public class AddTimingCommandTest {

    /**
     * Returns a fresh model instance for each test.
     */
    private Model getModel() {
        return new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    /**
     * Tests successful execution for a valid 400m timing.
     */
    @Test
    public void execute_valid400m_success() throws Exception {
        Model model = getModel();
        Person athlete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        RunTiming timing = new RunTiming("400m", 0, 55.0);

        AddTimingCommand command = new AddTimingCommand(INDEX_FIRST_PERSON, timing);
        CommandResult result = command.execute(model);

        String resultMessage = result.getFeedbackToUser();
        assertTrue(resultMessage.contains("Added timing for " + athlete.getName()));
        assertTrue(resultMessage.contains(timing.toString()));
        assertTrue(resultMessage.contains("New personal best for 400m"));
    }

    /**
     * Tests successful execution for a valid 2.4km timing.
     */
    @Test
    public void execute_valid24km_success() throws Exception {
        Model model = getModel();
        Person athlete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        RunTiming timing = new RunTiming("2.4km", 10, 30.0);

        AddTimingCommand command = new AddTimingCommand(INDEX_FIRST_PERSON, timing);
        CommandResult result = command.execute(model);

        String resultMessage = result.getFeedbackToUser();
        assertTrue(resultMessage.contains("Added timing for " + athlete.getName()));
        assertTrue(resultMessage.contains(timing.toString()));
    }

    /**
     * Tests successful execution for a valid 10km timing.
     */
    @Test
    public void execute_valid10km_success() throws Exception {
        Model model = getModel();
        Person athlete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        RunTiming timing = new RunTiming("10km", 47, 30.0);

        AddTimingCommand command = new AddTimingCommand(INDEX_FIRST_PERSON, timing);
        CommandResult result = command.execute(model);

        String resultMessage = result.getFeedbackToUser();
        assertTrue(resultMessage.contains("Added timing for " + athlete.getName()));
        assertTrue(resultMessage.contains(timing.toString()));
        assertTrue(resultMessage.contains("New personal best for 10km"));
    }

    /**
     * Tests successful execution for a valid 42km timing.
     */
    @Test
    public void execute_valid42km_success() throws Exception {
        Model model = getModel();
        Person athlete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        RunTiming timing = new RunTiming("42km", 240, 30.0);

        AddTimingCommand command = new AddTimingCommand(INDEX_FIRST_PERSON, timing);
        CommandResult result = command.execute(model);

        String resultMessage = result.getFeedbackToUser();
        assertTrue(resultMessage.contains("Added timing for " + athlete.getName()));
        assertTrue(resultMessage.contains(timing.toString()));
        assertTrue(resultMessage.contains("New personal best for 42km"));
    }

    /**
     * Tests failure when the target athlete index is invalid.
     */
    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Model model = getModel();
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        RunTiming timing = new RunTiming("2.4km", 10, 30.0);
        AddTimingCommand command = new AddTimingCommand(outOfBoundIndex, timing);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Tests that a new personal best message is shown when the added timing
     * is faster than an existing timing for the same distance.
     */
    @Test
    public void execute_personalBest_messageShown() throws Exception {
        Model model = getModel();
        Person athlete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        athlete.addRunTiming(new RunTiming("2.4km", 11, 0.0));

        AddTimingCommand command = new AddTimingCommand(INDEX_FIRST_PERSON,
                new RunTiming("2.4km", 10, 15.0));
        CommandResult result = command.execute(model);

        String resultMessage = result.getFeedbackToUser();
        assertTrue(resultMessage.contains("Added timing for " + athlete.getName()));
        assertTrue(resultMessage.contains("New personal best for 2.4km"));
    }

    /**
     * Tests that the personal best message is not shown when the added timing
     * is slower than an existing timing for the same distance.
     */
    @Test
    public void execute_nonPersonalBest_messageNotShown() throws Exception {
        Model model = getModel();
        Person athlete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        athlete.addRunTiming(new RunTiming("2.4km", 10, 0.0));

        AddTimingCommand command = new AddTimingCommand(INDEX_FIRST_PERSON,
                new RunTiming("2.4km", 11, 30.0));
        CommandResult result = command.execute(model);

        String resultMessage = result.getFeedbackToUser();
        assertTrue(resultMessage.contains("Added timing for " + athlete.getName()));
        assertFalse(resultMessage.contains("New personal best for 2.4km"));
    }

    /**
     * Tests that the list order is automatically corrected after {@code addtime}
     * changes a PB ranking (issue #261).
     *
     * <p>Steps:
     * <ol>
     *   <li>Give athlete 1 a slow 400m time (1:10) and athlete 2 a slower 400m time (1:20).</li>
     *   <li>Sort by 400m PB ascending — athlete 1 should be first.</li>
     *   <li>Add a faster 400m time (0:50) to athlete 2 via {@code AddTimingCommand}.</li>
     *   <li>Athlete 2 should now appear first in the list without a manual re-sort.</li>
     * </ol>
     * </p>
     */
    @Test
    public void execute_addtimeAfterSort_listOrderUpdated() throws Exception {
        Model model = getModel();

        // Give both athletes 400m timings
        Person athlete1 = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person athlete2 = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        athlete1.addRunTiming(new RunTiming("400m", 1, 10.0)); // 70 s
        athlete2.addRunTiming(new RunTiming("400m", 1, 20.0)); // 80 s

        // Sort by 400m PB ascending — athlete1 first
        Comparator<Person> pbAsc = Comparator
                .comparing((Person p) -> p.getBestTimeForDistance("400m") == Double.MAX_VALUE)
                .thenComparingDouble(p -> p.getBestTimeForDistance("400m"))
                .thenComparing(p -> p.getName().toString().toLowerCase());
        model.sortFilteredPersonList(pbAsc);
        assertEquals(athlete1, model.getFilteredPersonList().get(0));

        // AddTimingCommand gives athlete2 a faster 400m time (50 s)
        new AddTimingCommand(INDEX_SECOND_PERSON, new RunTiming("400m", 0, 50.0)).execute(model);

        // Athlete2 should now be first without a manual re-sort
        assertEquals(athlete2, model.getFilteredPersonList().get(0));
    }
}
