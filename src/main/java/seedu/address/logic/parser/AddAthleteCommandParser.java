package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AVAILABLE_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMERGENCY_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddAthleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Age;
import seedu.address.model.person.Email;
import seedu.address.model.person.EmergencyContact;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.StartDate;
import seedu.address.model.person.availableday.AvailableDay;
import seedu.address.model.tag.Tag;


/**
 * Parses input arguments and creates a new AddAthleteCommand object
 */
public class AddAthleteCommandParser implements Parser<AddAthleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddAthleteCommand
     * and returns an AddAthleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddAthleteCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        PREFIX_NAME, PREFIX_AGE, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_EMERGENCY_CONTACT, PREFIX_START_DATE, PREFIX_TAG, PREFIX_AVAILABLE_DAY);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_AGE, PREFIX_ADDRESS,
                PREFIX_PHONE, PREFIX_EMAIL, PREFIX_START_DATE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAthleteCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_AGE, PREFIX_PHONE,
                PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_START_DATE);
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Age age = ParserUtil.parseAge(argMultimap.getValue(PREFIX_AGE).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        StartDate startDate = ParserUtil.parseStartDate(argMultimap.getValue(PREFIX_START_DATE).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Set<AvailableDay> availableDays = ParserUtil.parseAvailableDays(argMultimap
                .getAllValues(PREFIX_AVAILABLE_DAY));
        EmergencyContact emergencyContact;

        if (argMultimap.getValue(PREFIX_EMERGENCY_CONTACT).isPresent()) {
            emergencyContact = new EmergencyContact(argMultimap.getValue(PREFIX_EMERGENCY_CONTACT).get());
        } else {
            emergencyContact = new EmergencyContact("N/A");
        }

        Person person = new Person(name, age, phone, email, address,
                emergencyContact, startDate, tagList, availableDays);

        return new AddAthleteCommand(person);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
