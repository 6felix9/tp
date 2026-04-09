package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AVAILABLE_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.AvailableDayContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;
import seedu.address.model.tag.TagContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        // Clean the input args of whitespaces at the ends
        String trimmedArgs = args.trim();

        // Handle case when there is no args to use
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        // Get the hashmap of prefixes to their string list
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME,
                PREFIX_TAG, PREFIX_PHONE, PREFIX_AVAILABLE_DAY);

        // If the obtained multimap does not have at least one prefix,
        // or if there is a non-empty preamble, then it is an error
        if (!isOnePrefixPresent(argMultimap, PREFIX_NAME, PREFIX_TAG, PREFIX_PHONE, PREFIX_AVAILABLE_DAY)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindCommand.MESSAGE_USAGE));
        }

        // Verify that for phone number and name, there is only one prefix
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE);

        // Create the predicates
        NameContainsKeywordsPredicate namePredicate =
            new NameContainsKeywordsPredicate(splitKeywords(argMultimap.getAllValues(PREFIX_NAME)));
        TagContainsKeywordsPredicate tagPredicate =
            new TagContainsKeywordsPredicate(argMultimap.getAllValues(PREFIX_TAG));
        PhoneContainsKeywordsPredicate phonePredicate =
            new PhoneContainsKeywordsPredicate(splitKeywords(argMultimap.getAllValues(PREFIX_PHONE)));
        AvailableDayContainsKeywordsPredicate availableDayPredicate =
                new AvailableDayContainsKeywordsPredicate(argMultimap.getAllValues(PREFIX_AVAILABLE_DAY));

        // Compose the predicates
        Predicate<Person> compositePredicate = namePredicate
                .and(tagPredicate)
                .and(phonePredicate)
                .and(availableDayPredicate);

        // Return the appropriate find command object
        return new FindCommand(compositePredicate);
    }

    /**
     * Returns true if at least one of the prefixes is present within the map
     * @param argumentMultimap
     * @param prefixes
     */
    private static boolean isOnePrefixPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Splits multi-word argument values into individual keywords for predicates that match one word at a time.
     */
    private static List<String> splitKeywords(List<String> values) {
        return values.stream()
                .flatMap(value -> Arrays.stream(value.trim().split("\\s+")))
                .filter(keyword -> !keyword.isEmpty())
                .collect(Collectors.toList());
    }
}
