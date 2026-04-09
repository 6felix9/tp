package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddTimingCommand;
import seedu.address.model.person.RunTiming;

/**
 * Contains parser tests for {@code AddTimingCommandParser}.
 */
public class AddTimingCommandParserTest {

    private static final String INVALID_FORMAT_MESSAGE =
            "Invalid command format: addtime INDEX dist/DISTANCE min/MINUTES sec/SECONDS";

    private static final String MISSING_FIELDS_MESSAGE =
            "Missing required fields: dist/DISTANCE min/MINUTES sec/SECONDS";

    private final AddTimingCommandParser parser = new AddTimingCommandParser();

    /**
     * Tests parse success for a valid 400m timing command.
     */
    @Test
    public void parse_valid400m_success() {
        assertDoesNotThrow(() -> {
            AddTimingCommand command = parser.parse("1 dist/400m min/0 sec/55");
            assertTrue(command instanceof AddTimingCommand);
        });
    }

    /**
     * Tests parse success for a valid 42km timing command.
     */
    @Test
    public void parse_valid42km_success() {
        assertDoesNotThrow(() -> {
            AddTimingCommand command = parser.parse("1 dist/42km min/240 sec/30");
            assertTrue(command instanceof AddTimingCommand);
        });
    }

    /**
     * Tests parse failures when required fields are missing.
     */
    @Test
    public void parse_missingFields_failure() {
        assertParseFailure(parser, "1", MISSING_FIELDS_MESSAGE);

        assertParseFailure(parser, "1 min/10 sec/30", MISSING_FIELDS_MESSAGE);

        assertParseFailure(parser, "1 dist/2.4km sec/30", MISSING_FIELDS_MESSAGE);

        assertParseFailure(parser, "1 dist/2.4km min/10", MISSING_FIELDS_MESSAGE);
    }

    /**
     * Tests parse failures when duplicate prefixes are provided.
     */
    @Test
    public void parse_duplicateFields_failure() {
        assertParseFailure(parser, "1 dist/2.4km dist/10km min/10 sec/30",
                "Multiple values specified for the following single-valued field(s): dist/");

        assertParseFailure(parser, "1 dist/2.4km min/10 min/11 sec/30",
                "Multiple values specified for the following single-valued field(s): min/");

        assertParseFailure(parser, "1 dist/2.4km min/10 sec/30 sec/31",
                "Multiple values specified for the following single-valued field(s): sec/");
    }

    /**
     * Tests parse failures for invalid values.
     */
    @Test
    public void parse_invalidValues_failure() {
        assertParseFailure(parser, "1 dist/5km min/10 sec/30",
                RunTiming.MESSAGE_DISTANCE_CONSTRAINTS);

        assertParseFailure(parser, "1 dist/2.4km min/-1 sec/30",
                "Invalid minutes: must be a non-negative integer\n" + COMMAND_FORMAT);

        assertParseFailure(parser, "1 dist/2.4km min/abc sec/30",
                INVALID_FORMAT_MESSAGE);

        assertParseFailure(parser, "1 dist/2.4km min/10 sec/-1",
                "Invalid seconds: must be between 0 and 59.99");

        assertParseFailure(parser, "1 dist/2.4km min/10 sec/60",
                "Invalid seconds: must be a number from 0 to <60\n" + COMMAND_FORMAT);

        assertParseFailure(parser, "1 dist/2.4km min/10 sec/abc",
                INVALID_FORMAT_MESSAGE);

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

    /**
     * Tests parse failures when the athlete index is invalid.
     */
    @Test
    public void parse_invalidIndex_failure() {
        assertParseFailure(parser, "abc dist/2.4km min/10 sec/30",
                INVALID_FORMAT_MESSAGE);

        assertParseFailure(parser, "-1 dist/2.4km min/10 sec/30",
                INVALID_FORMAT_MESSAGE);
    }
}
