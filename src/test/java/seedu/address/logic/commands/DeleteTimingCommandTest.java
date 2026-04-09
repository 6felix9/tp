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

public class DeleteTimingCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndices_success() throws Exception {
        // First add a timing so there's something to delete
        Person athlete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        RunTiming timing = new RunTiming("2.4km", 10, 30.0);
        athlete.addRunTiming(timing);

        DeleteTimingCommand command = new DeleteTimingCommand(INDEX_FIRST_PERSON, INDEX_FIRST_PERSON);
        CommandResult result = command.execute(model);

        String resultMessage = result.getFeedbackToUser();
        assertTrue(resultMessage.contains("Deleted timing for " + athlete.getName()));
    }

    @Test
    public void execute_invalidAthleteIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeleteTimingCommand command = new DeleteTimingCommand(outOfBoundIndex, INDEX_FIRST_PERSON);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }
}
