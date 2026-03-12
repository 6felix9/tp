package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteTimingCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input arguments and creates a {@link DeleteTimingCommand}.
 */
public class DeleteTimingCommandParser implements Parser<DeleteTimingCommand> {

    @Override
    public DeleteTimingCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        String[] tokens = trimmedArgs.split("\\s+");

        if (tokens.length != 2) {
            throw new ParseException(String.format(
                "Invalid command format: %s", DeleteTimingCommand.MESSAGE_USAGE));
        }

        try {
            Index athleteIndex = ParserUtil.parseIndex(tokens[0]);
            Index timingIndex = ParserUtil.parseIndex(tokens[1]);
            return new DeleteTimingCommand(athleteIndex, timingIndex);
        } catch (ParseException pe) {
            throw new ParseException(String.format(
                "Invalid command format: %s", DeleteTimingCommand.MESSAGE_USAGE));
        }
    }
}
