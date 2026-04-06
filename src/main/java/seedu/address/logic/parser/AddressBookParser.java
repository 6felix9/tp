package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddAthleteCommand;
import seedu.address.logic.commands.AddTimingCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteAthleteCommand;
import seedu.address.logic.commands.DeleteTimingCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.ViewAthleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final Logger logger = LogsCenter.getLogger(AddressBookParser.class);

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        logger.log(Level.INFO, "Starting to parse user input: {0}", userInput);

        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            logger.warning("User input does not match basic command format.");
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        // Note to developers: Change the log level in config.json to enable lower level (i.e., FINE, FINER and lower)
        // log messages such as the one below.
        // Lower level log messages are used sparingly to minimize noise in the code.
        logger.fine("Command word: " + commandWord + "; Arguments: " + arguments);

        switch (commandWord) {

        case AddAthleteCommand.COMMAND_WORD:
            logger.log(Level.INFO, "Parsing add command");
            return new AddAthleteCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            logger.log(Level.INFO, "Parsing edit command");
            return new EditCommandParser().parse(arguments);

        case DeleteAthleteCommand.COMMAND_WORD:
            logger.log(Level.INFO, "Parsing delete athlete command");
            return new DeleteAthleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            logger.log(Level.INFO, "Parsing clear command");
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            logger.log(Level.INFO, "Parsing find command");
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            logger.log(Level.INFO, "Parsing list command");
            return new ListCommand();

        case ViewAthleteCommand.COMMAND_WORD:
            logger.log(Level.INFO, "Parsing view athlete command");
            return new ViewAthleteCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            logger.log(Level.INFO, "Parsing exit command");
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            logger.log(Level.INFO, "Parsing help command");
            return new HelpCommand();

        case AddTimingCommand.COMMAND_WORD:
            logger.log(Level.INFO, "Parsing add timing command");
            return new AddTimingCommandParser().parse(arguments);

        case DeleteTimingCommand.COMMAND_WORD:
            logger.log(Level.INFO, "Parsing delete timing command");
            return new DeleteTimingCommandParser().parse(arguments);

        case SortCommand.COMMAND_WORD:
            return new SortCommandParser().parse(arguments);

        default:
            logger.log(Level.WARNING, "Unknown command entered: {0}", userInput);
            logger.finer("This user input caused a ParseException: " + userInput);
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
