package seedu.address.logic.commands;

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

public class AddTimingCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() throws Exception {
        Person athlete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        RunTiming timing = new RunTiming("2.4km", 10, 30.0);

        AddTimingCommand command = new AddTimingCommand(INDEX_FIRST_PERSON, timing);
        CommandResult result = command.execute(model);

        String resultMessage = result.getFeedbackToUser();
        assertTrue(resultMessage.contains("Added timing for " + athlete.getName()));
        assertTrue(resultMessage.contains(timing.toString()));
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        RunTiming timing = new RunTiming("2.4km", 10, 30.0);
        AddTimingCommand command = new AddTimingCommand(outOfBoundIndex, timing);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }
}
