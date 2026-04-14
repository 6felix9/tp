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

public class ViewAthleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndex_success() throws Exception {
        Person athlete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        ViewAthleteCommand command = new ViewAthleteCommand(INDEX_FIRST_PERSON);

        CommandResult result = command.execute(model);

        String resultMessage = result.getFeedbackToUser();
        assertTrue(resultMessage.contains(athlete.getName().toString()));
        assertTrue(resultMessage.contains(athlete.getPhone().toString()));
        assertTrue(resultMessage.contains(athlete.getEmail().toString()));
        assertTrue(resultMessage.contains("Start Date:"));
        assertFalse(resultMessage.contains("StartDate:"));
        assertTrue(resultMessage.contains("Run Timings:"));
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        ViewAthleteCommand command = new ViewAthleteCommand(outOfBoundIndex);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }
}
