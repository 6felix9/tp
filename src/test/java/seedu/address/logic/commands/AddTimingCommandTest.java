package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

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
}
