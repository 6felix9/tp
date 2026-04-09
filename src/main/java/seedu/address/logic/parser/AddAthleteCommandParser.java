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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAthleteCommand.MESSAGE_USAGE));
        }

        List<String> missingFields = getMissingFields(argMultimap);
        if (!missingFields.isEmpty()) {
            throw new ParseException(buildMissingFieldsMessage(missingFields));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_AGE, PREFIX_PHONE,
                PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_EMERGENCY_CONTACT, PREFIX_START_DATE);
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Age age = ParserUtil.parseAge(argMultimap.getValue(PREFIX_AGE).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        StartDate startDate = ParserUtil.parseStartDate(argMultimap.getValue(PREFIX_START_DATE).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Set<AvailableDay> availableDays = ParserUtil.parseAvailableDays(argMultimap
                .getAllValues(PREFIX_AVAILABLE_DAY));
        EmergencyContact emergencyContact = ParserUtil.parseEmergencyContact(
                argMultimap.getValue(PREFIX_EMERGENCY_CONTACT).get());

        Person person = new Person(name, age, phone, email, address,
                emergencyContact, startDate, tagList, availableDays);

        return new AddAthleteCommand(person);
    }

    /**
     * Returns a list of required fields that are missing from the given {@code ArgumentMultimap}.
     *
     * @param argMultimap The argument multimap containing the parsed user input.
     * @return A list of missing required field names with their corresponding prefixes.
     */
    private List<String> getMissingFields(ArgumentMultimap argMultimap) {
        List<String> missingFields = new ArrayList<>();

        if (argMultimap.getValue(PREFIX_NAME).isEmpty()) {
            missingFields.add("name (n/)");
        }
        if (argMultimap.getValue(PREFIX_AGE).isEmpty()) {
            missingFields.add("age (a/)");
        }
        if (argMultimap.getValue(PREFIX_PHONE).isEmpty()) {
            missingFields.add("phone (p/)");
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isEmpty()) {
            missingFields.add("email (e/)");
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isEmpty()) {
            missingFields.add("address (ad/)");
        }
        if (argMultimap.getValue(PREFIX_EMERGENCY_CONTACT).isEmpty()) {
            missingFields.add("emergency contact (ec/)");
        }
        if (argMultimap.getValue(PREFIX_START_DATE).isEmpty()) {
            missingFields.add("start date (d/)");
        }

        return missingFields;
    }

    /**
     * Builds a user-friendly error message listing the missing required fields.
     *
     * @param missingFields A list of missing required field names.
     * @return A formatted error message indicating which required fields are missing.
     */
    private String buildMissingFieldsMessage(List<String> missingFields) {
        if (missingFields.size() == 1) {
            return "Missing required field: " + missingFields.get(0);
        }
        return "Missing required fields: " + String.join(", ", missingFields);
    }
}
