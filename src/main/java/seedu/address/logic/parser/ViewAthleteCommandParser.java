package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ViewAthleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ViewAthleteCommand object.
 */
public class ViewAthleteCommandParser implements Parser<ViewAthleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * ViewAthleteCommand
     * and returns a ViewAthleteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewAthleteCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ViewAthleteCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewAthleteCommand.MESSAGE_USAGE), pe);
        }
    }

}
