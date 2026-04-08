package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DISTANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MIN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEC;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddTimingCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.RunTiming;

/**
 * Parses user input arguments and creates an {@link AddTimingCommand}.
 *
 * <p>This parser extracts the athlete index and run timing parameters
 * from the user input. It validates the presence and correctness of
 * required fields before constructing the command.</p>
 *
 * <p>Expected command format:</p>
 * <pre>
 * addtiming INDEX min/MINUTES sec/SECONDS
 * </pre>
 */
public class AddTimingCommandParser implements Parser<AddTimingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * {@code AddTimingCommand} and returns an {@code AddTimingCommand}.
     *
     * @param args Full command arguments string.
     * @return A new {@code AddTimingCommand} containing the parsed values.
     * @throws ParseException If the user input does not conform to the expected format
     *                        or if the timing values are invalid.
     */
    @Override
    public AddTimingCommand parse(String args) throws ParseException {

        ArgumentMultimap map = ArgumentTokenizer.tokenize(args,
                PREFIX_DISTANCE, PREFIX_MIN, PREFIX_SEC);

        // Check duplicate fields
        map.verifyNoDuplicatePrefixesFor(PREFIX_DISTANCE, PREFIX_MIN, PREFIX_SEC);

        // Check missing required fields
        if (map.getValue(PREFIX_DISTANCE).map(String::isBlank).orElse(true)
                || map.getValue(PREFIX_MIN).map(String::isBlank).orElse(true)
                || map.getValue(PREFIX_SEC).map(String::isBlank).orElse(true)) {
            throw new ParseException("Missing required fields: dist/DISTANCE min/MINUTES sec/SECONDS");
        }

        Index index;
        String distance;
        int minutes;
        double seconds;

        try {
            index = ParserUtil.parseIndex(map.getPreamble());
            distance = map.getValue(PREFIX_DISTANCE).get();
            minutes = Integer.parseInt(map.getValue(PREFIX_MIN).get());
            seconds = Double.parseDouble(map.getValue(PREFIX_SEC).get());
        } catch (Exception e) {
            throw new ParseException(
                    "Invalid command format: addtiming INDEX dist/DISTANCE min/MINUTES sec/SECONDS");
        }

        RunTiming timing = getRunTiming(distance, minutes, seconds);

        return new AddTimingCommand(index, timing);
    }

    /**
     * Validates timing fields and constructs a {@code RunTiming}.
     *
     * @param distance Race distance.
     * @param minutes Minutes component.
     * @param seconds Seconds component.
     * @return Valid run timing.
     * @throws ParseException If any field is invalid.
     */
    private static RunTiming getRunTiming(String distance, int minutes, double seconds)
            throws ParseException {
        if (!RunTiming.isValidDistance(distance)) {
            throw new ParseException(RunTiming.MESSAGE_DISTANCE_CONSTRAINTS);
        }

        if (minutes < 0) {
            throw new ParseException("Invalid minutes: must be a non-negative integer");
        }

        if (seconds < 0 || seconds >= 60) {
            throw new ParseException("Invalid seconds: must be between 0 and 59.99");
        }

        if (minutes == 0 && seconds == 0) {
            throw new ParseException("Invalid timing: total time must be greater than 0");
        }
        return new RunTiming(distance, minutes, seconds);
    }
}
