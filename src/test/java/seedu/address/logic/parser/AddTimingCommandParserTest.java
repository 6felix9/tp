package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

/**
 * Contains parser tests for {@code AddTimingCommandParser}.
 */
public class AddTimingCommandParserTest {

    private static final String COMMAND_FORMAT =
            "Correct command format: addtime INDEX dist/DISTANCE min/MINUTES sec/SECONDS";

    private final AddTimingCommandParser parser = new AddTimingCommandParser();

    /**
     * Tests parse failures when required fields are missing.
     */
    @Test
    public void parse_missingFields_failure() {
        // missing all prefixed fields -> first missing field detected is dist/
        assertParseFailure(parser, "1",
                "Missing required field: dist/DISTANCE\n" + COMMAND_FORMAT);

        // missing sec/
        assertParseFailure(parser, "1 dist/2.4km min/10",
                "Missing required field: sec/SECONDS\n" + COMMAND_FORMAT);

        // missing min/
        assertParseFailure(parser, "1 dist/2.4km sec/30",
                "Missing required field: min/MINUTES\n" + COMMAND_FORMAT);

        // missing dist/
        assertParseFailure(parser, "1 min/10 sec/30",
                "Missing required field: dist/DISTANCE\n" + COMMAND_FORMAT);
    }

    /**
     * Tests parse failures when invalid values are provided.
     */
    @Test
    public void parse_invalidValues_failure() {
        // invalid distance
        assertParseFailure(parser, "1 dist/5km min/10 sec/30",
                "Invalid distance: supported distances are 400m, 2.4km, 10km, and 42km.");

        // negative minutes
        assertParseFailure(parser, "1 dist/2.4km min/-1 sec/30",
                "Invalid minutes: must be a non-negative integer\n" + COMMAND_FORMAT);

        // seconds out of range
        assertParseFailure(parser, "1 dist/2.4km min/10 sec/60",
                "Invalid seconds: must be a number from 0 to <60\n" + COMMAND_FORMAT);

        // zero total time
        assertParseFailure(parser, "1 dist/2.4km min/0 sec/0",
                "Invalid timing: total time must be greater than 0\n" + COMMAND_FORMAT);
    }

    /**
     * Tests parse failures when duplicate prefixes are provided.
     */
    @Test
    public void parse_duplicateFields_failure() {
        assertParseFailure(parser, "1 dist/2.4km dist/10km min/10 sec/30",
                "Invalid command: each field (dist/, min/, sec/) can only be provided once\n"
                        + COMMAND_FORMAT);

        assertParseFailure(parser, "1 dist/2.4km min/10 min/11 sec/30",
                "Invalid command: each field (dist/, min/, sec/) can only be provided once\n"
                        + COMMAND_FORMAT);

        assertParseFailure(parser, "1 dist/2.4km min/10 sec/30 sec/31",
                "Invalid command: each field (dist/, min/, sec/) can only be provided once\n"
                        + COMMAND_FORMAT);
    }

    /**
     * Tests parse failures when the athlete index is invalid.
     */
    @Test
    public void parse_invalidIndex_failure() {
        assertParseFailure(parser, "abc dist/2.4km min/10 sec/30",
                "Invalid index: please provide a positive integer athlete index\n" + COMMAND_FORMAT);

        assertParseFailure(parser, "-1 dist/2.4km min/10 sec/30",
                "Invalid index: please provide a positive integer athlete index\n" + COMMAND_FORMAT);
    }
}
