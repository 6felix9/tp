package seedu.address.logic.parser;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.commands.SortCommand.SortField;
import seedu.address.logic.commands.SortCommand.SortOrder;


import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER;

public class SortCommandParser implements Parser<SortCommand> {

    public SortCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_BY, CliSyntax.PREFIX_ORDER);

        if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_BY)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_BY, PREFIX_ORDER);
        SortField sortField = ParserUtil.parseBy(argMultimap.getValue(PREFIX_BY).get());
        SortOrder sortOrder = ParserUtil.parseOrder(argMultimap.getValue(PREFIX_ORDER).orElse("asc"));

        return new SortCommand(sortField, sortOrder);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
