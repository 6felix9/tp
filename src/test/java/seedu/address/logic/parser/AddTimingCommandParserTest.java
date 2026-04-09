package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

public class AddTimingCommandParserTest {

    private AddTimingCommandParser parser = new AddTimingCommandParser();

    /**
     * Tests parse failures when required fields are missing.
     */
    @Test
    public void parse_missingFields_failure() {
        // missing all prefixed fields
        assertParseFailure(parser, "1", "Missing required fields: dist/DISTANCE min/MINUTES sec/SECONDS");

        // missing sec/
        assertParseFailure(parser, "1 dist/2.4km min/10",
                "Missing required fields: dist/DISTANCE min/MINUTES sec/SECONDS");

        // missing min/
        assertParseFailure(parser, "1 dist/2.4km sec/30",
                "Missing required fields: dist/DISTANCE min/MINUTES sec/SECONDS");
    }

    /**
     * Tests parse failures when invalid values are provided.
     */
    @Test
    public void parse_invalidValues_failure() {
        // invalid distance
        assertParseFailure(parser, "1 dist/5km min/10 sec/30",
                "Distance must be one of: 2.4km, 400m, 10km, 42km");

        // negative minutes
        assertParseFailure(parser, "1 dist/2.4km min/-1 sec/30",
                "Invalid minutes: must be a non-negative integer");

        // seconds out of range
        assertParseFailure(parser, "1 dist/2.4km min/10 sec/60",
                "Invalid seconds: must be between 0 and 59.99");

        // zero total time
        assertParseFailure(parser, "1 dist/2.4km min/0 sec/0",
                "Invalid timing: total time must be greater than 0");
    }
}
