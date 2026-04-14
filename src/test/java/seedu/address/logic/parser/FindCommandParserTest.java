package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_EMPTY_ARGUMENTS;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.availableday.AvailableDay;

public class FindCommandParserTest {

    private final FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertThrows(ParseException.class,
            MESSAGE_EMPTY_ARGUMENTS + FindCommand.MESSAGE_USAGE, () -> parser.parse("     "));
    }

    @Test
    public void parse_noRecognisedPrefix_throwsParseException() {
        assertThrows(ParseException.class,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE), () -> parser.parse("Alice Bob"));
    }

    @Test
    public void parse_namePrefix_returnsFindCommand() throws Exception {
        assertTrue(parser.parse(" " + PREFIX_NAME + "Alice Bob") instanceof FindCommand);
        assertTrue(parser.parse("  " + PREFIX_NAME + "Alice   Bob  ") instanceof FindCommand);
    }

    @Test
    public void parse_phonePrefix_returnsFindCommand() throws Exception {
        assertTrue(parser.parse(" " + PREFIX_PHONE + "91234567") instanceof FindCommand);
    }

    @Test
    public void parse_tagPrefix_returnsFindCommand() throws Exception {
        assertTrue(parser.parse(" " + PREFIX_TAG + "friends") instanceof FindCommand);
        assertTrue(parser.parse(" " + PREFIX_TAG + "friends " + PREFIX_TAG + "runner") instanceof FindCommand);
    }

    @Test
    public void parse_multiplePrefixes_returnsFindCommand() throws Exception {
        assertTrue(parser.parse(" " + PREFIX_NAME + "Alice " + PREFIX_PHONE + "91234567") instanceof FindCommand);
        assertTrue(parser.parse(" " + PREFIX_NAME + "Alice " + PREFIX_TAG + "friends") instanceof FindCommand);
        assertTrue(parser.parse(" " + PREFIX_PHONE + "91234567 " + PREFIX_TAG + "friends") instanceof FindCommand);
    }

    @Test
    public void parse_invalidAvailableDay_throwsParseException() {
        assertThrows(ParseException.class,
                AvailableDay.MESSAGE_CONSTRAINTS, () -> parser.parse(" av/Funday"));
    }
}
