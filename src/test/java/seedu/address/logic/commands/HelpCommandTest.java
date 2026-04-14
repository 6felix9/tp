package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class HelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, true, false);
        assertCommandSuccess(new HelpCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void showingHelpMessage_editCommandFormat_usesSupportedPrefixes() {
        assertTrue(SHOWING_HELP_MESSAGE.contains("[ec/EMERGENCY_CONTACT]"));
        assertTrue(SHOWING_HELP_MESSAGE.contains("[ta/TAG]..."));
        assertTrue(SHOWING_HELP_MESSAGE.contains("[td/TAG]..."));
        assertTrue(SHOWING_HELP_MESSAGE.contains("[av/AVAILABLE_DAY]..."));
        assertFalse(SHOWING_HELP_MESSAGE.contains("edit          INDEX [n/NAME] [a/AGE] [p/PHONE] [e/EMAIL] "
                + "[ad/ADDRESS] [d/START_DATE] [t/TAG]..."));
    }

    @Test
    public void showingHelpMessage_findCommandFormat_doesNotUseKeywordPreamble() {
        assertTrue(SHOWING_HELP_MESSAGE.contains("find          [n/NAME]... [p/PHONE]... [t/TAG]... "
                + "[av/AVAILABLE_DAY]..."));
        assertFalse(SHOWING_HELP_MESSAGE.contains("find KEYWORD"));
    }
}
