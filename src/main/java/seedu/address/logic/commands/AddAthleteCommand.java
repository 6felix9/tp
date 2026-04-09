package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AVAILABLE_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMERGENCY_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Adds a person to the address book.
 */
public class AddAthleteCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_AGE + "AGE "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_EMERGENCY_CONTACT + "EMERGENCY_CONTACT "
            + PREFIX_START_DATE + "START DATE "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "[" + PREFIX_AVAILABLE_DAY + "AVAILABLE DAY]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_AGE + "18 "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_EMERGENCY_CONTACT + "Jane Doe 91234567 "
            + PREFIX_START_DATE + "06/03/2026 "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney "
            + PREFIX_AVAILABLE_DAY + "Mon "
            + PREFIX_AVAILABLE_DAY + "Wed";

    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";

    private final Person toAdd;

    /**
     * Creates an AddAthleteCommand to add the specified {@code Person}
     */
    public AddAthleteCommand(Person person) {
        requireNonNull(person);
        toAdd = person;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPerson(toAdd);
        return new CommandResult(formatAthleteAddedMessage(toAdd));
    }

    /**
     * Returns a formatted success message for the specified {@code Person}.
     *
     * @param athlete The athlete whose details are to be included in the success message.
     * @return A formatted success message containing the athlete's details.
     */
    public static String formatAthleteAddedMessage(Person athlete) {
        String tags = athlete.getTags().isEmpty() ? "-" : athlete.getTags().toString();
        String availableDays = athlete.getAvailableDays().isEmpty() ? "-" : athlete.getAvailableDays().toString();

        return String.format(
                "New athlete added:%n"
                        + "  Name: %s%n"
                        + "  Age: %s%n"
                        + "  Phone: %s%n"
                        + "  Email: %s%n"
                        + "  Address: %s%n"
                        + "  Emergency Contact: %s%n"
                        + "  Start Date: %s%n"
                        + "  Tags: %s%n"
                        + "  Available Days: %s",
                athlete.getName(),
                athlete.getAge(),
                athlete.getPhone(),
                athlete.getEmail(),
                athlete.getAddress(),
                athlete.getEmergencyContact(),
                athlete.getStartDate(),
                tags,
                availableDays
        );
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddAthleteCommand)) {
            return false;
        }

        AddAthleteCommand otherAddCommand = (AddAthleteCommand) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
