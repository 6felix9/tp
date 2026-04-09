package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;

public class SortCommandParserTest {

    private final SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_sortByName_success() {
        assertParseSuccess(parser, " by/name",
                new SortCommand(SortCommand.SortField.NAME, null, SortCommand.SortOrder.ASC));
        assertParseSuccess(parser, " by/name ord/desc",
                new SortCommand(SortCommand.SortField.NAME, null, SortCommand.SortOrder.DESC));
    }

    @Test
    public void parse_sortByPbWithDistance_success() {
        assertParseSuccess(parser, " by/pb dist/400m",
                new SortCommand(SortCommand.SortField.PB, "400m", SortCommand.SortOrder.ASC));
        assertParseSuccess(parser, " by/pb dist/2.4km ord/desc",
                new SortCommand(SortCommand.SortField.PB, "2.4km", SortCommand.SortOrder.DESC));
    }

    @Test
    public void parse_missingDistanceForPb_failure() {
        assertParseFailure(parser, " by/pb",
                SortCommandParser.MESSAGE_MISSING_DISTANCE_FOR_PB);
    }

    @Test
    public void parse_distanceProvidedForName_failure() {
        assertParseFailure(parser, " by/name dist/400m",
                SortCommandParser.MESSAGE_UNEXPECTED_DISTANCE);
    }

    @Test
    public void parse_invalidDistance_failure() {
        assertParseFailure(parser, " by/pb dist/5km",
                SortCommandParser.MESSAGE_INVALID_DISTANCE_VALUE);
    }

    @Test
    public void parse_duplicateDistance_failure() {
        assertParseFailure(parser, " by/pb dist/400m dist/2.4km",
                seedu.address.logic.Messages.getErrorMessageForDuplicatePrefixes(
                        seedu.address.logic.parser.CliSyntax.PREFIX_DISTANCE));
    }
}
