package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DISTANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER;

import java.util.Optional;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.SortCommand.SortField;
import seedu.address.logic.commands.SortCommand.SortOrder;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code SortCommand} object.
 */
public class SortCommandParser implements Parser<SortCommand> {

    public static final String MESSAGE_MISSING_SORT_FIELD =
            "Missing required field: by/FIELD\n"
                    + "Example: sort by/name ord/desc";

    public static final String MESSAGE_MISSING_BY_VALUE =
            "Missing required value for by/\n"
                    + "Supported fields: name, pb\n"
                    + "Example: sort by/name ord/desc";

    public static final String MESSAGE_INVALID_BY_VALUE =
            "Invalid value for by/\n"
                    + "Supported fields: name, pb\n"
                    + "Example: sort by/name ord/desc";

    public static final String MESSAGE_MISSING_ORDER_VALUE =
            "Missing required value for ord/\n"
                    + "Supported orders: asc, desc\n"
                    + "Example: sort by/name ord/desc";

    public static final String MESSAGE_INVALID_ORDER_VALUE =
            "Invalid value for ord/\n"
                    + "Supported orders: asc, desc\n"
                    + "Example: sort by/name ord/desc";

    public static final String MESSAGE_MISSING_DISTANCE_VALUE =
            "Missing required value for dist/\n"
                    + "Supported distances: 400m, 2.4km, 10km, 42km\n"
                    + "Example: sort by/pb dist/2.4km ord/asc";

    public static final String MESSAGE_INVALID_DISTANCE_VALUE =
            "Invalid value for dist/\n"
                    + "Supported distances: 400m, 2.4km, 10km, 42km\n"
                    + "Example: sort by/pb dist/2.4km ord/asc";

    public static final String MESSAGE_MISSING_DISTANCE_FOR_PB =
            "Missing required field: dist/DISTANCE when sorting by pb\n"
                    + "Supported distances: 400m, 2.4km, 10km, 42km\n"
                    + "Example: sort by/pb dist/2.4km ord/asc";

    public static final String MESSAGE_UNEXPECTED_DISTANCE =
            "dist/ can only be used when sorting by pb\n"
                    + "Example: sort by/name ord/desc";

    /**
     * Parses the given {@code String} of arguments in the context of the {@code SortCommand}
     * and returns a {@code SortCommand} object for execution.
     *
     * Expected format:
     * {@code by/FIELD [dist/DISTANCE] [ord/ORDER]}
     *
     * @param args User input arguments.
     * @return A {@code SortCommand} containing the parsed sort field, distance, and sort order.
     * @throws ParseException If the user input does not conform to the expected format.
     */
    @Override
    public SortCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_BY, PREFIX_DISTANCE, PREFIX_ORDER);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_BY, PREFIX_DISTANCE, PREFIX_ORDER);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        Optional<String> byValue = argMultimap.getValue(PREFIX_BY);
        if (byValue.isEmpty()) {
            throw new ParseException(MESSAGE_MISSING_SORT_FIELD);
        }

        String rawBy = byValue.get().trim();
        if (rawBy.isEmpty()) {
            throw new ParseException(MESSAGE_MISSING_BY_VALUE);
        }

        SortField sortField;
        try {
            sortField = ParserUtil.parseBy(rawBy);
        } catch (ParseException e) {
            throw new ParseException(MESSAGE_INVALID_BY_VALUE);
        }

        String distance = null;
        Optional<String> distanceValue = argMultimap.getValue(PREFIX_DISTANCE);
        if (distanceValue.isPresent()) {
            String rawDistance = distanceValue.get().trim();
            if (rawDistance.isEmpty()) {
                throw new ParseException(MESSAGE_MISSING_DISTANCE_VALUE);
            }
            try {
                distance = ParserUtil.parseDistance(rawDistance);
            } catch (ParseException e) {
                throw new ParseException(MESSAGE_INVALID_DISTANCE_VALUE);
            }
        }

        SortOrder sortOrder = SortOrder.ASC;
        Optional<String> orderValue = argMultimap.getValue(PREFIX_ORDER);
        if (orderValue.isPresent()) {
            String rawOrder = orderValue.get().trim();
            if (rawOrder.isEmpty()) {
                throw new ParseException(MESSAGE_MISSING_ORDER_VALUE);
            }
            try {
                sortOrder = ParserUtil.parseOrder(rawOrder);
            } catch (ParseException e) {
                throw new ParseException(MESSAGE_INVALID_ORDER_VALUE);
            }
        }

        if (sortField == SortField.PB && distance == null) {
            throw new ParseException(MESSAGE_MISSING_DISTANCE_FOR_PB);
        }

        if (sortField != SortField.PB && distance != null) {
            throw new ParseException(MESSAGE_UNEXPECTED_DISTANCE);
        }

        return new SortCommand(sortField, distance, sortOrder);
    }
}
