package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.RunTiming;

/**
 * Views a person identified using their displayed index from the address book.
 */
public class ViewAthleteCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views the athlete identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_VIEW_ATHLETE_SUCCESS = "Athlete Details:\n"
            + "Name:                 %1$s\n"
            + "Age:                  %2$s\n"
            + "Phone:                %3$s\n"
            + "Email:                %4$s\n"
            + "Emergency Contact:    %5$s\n"
            + "Address:              %6$s\n"
            + "StartDate:            %7$s\n"
            + "Tags:                 %8$s\n"
            + "Available Days:       %9$s\n";

    private final Index targetIndex;

    public ViewAthleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person athlete = lastShownList.get(targetIndex.getZeroBased());

        String tags = athlete.getTags().stream()
                .map(tag -> "[" + tag.tagName + "]")
                .reduce("", (a, b) -> a + " " + b)
                .strip();

        String availableDays = athlete.getAvailableDays().stream()
                .map(day -> "[" + day.availableDay + "]")
                .reduce("", (a, b) -> a + " " + b)
                .strip();

        String baseDetails = String.format(MESSAGE_VIEW_ATHLETE_SUCCESS,
                athlete.getName(),
                athlete.getAge(),
                athlete.getPhone(),
                athlete.getEmail(),
                athlete.getEmergencyContact(),
                athlete.getAddress(),
                athlete.getStartDate(),
                tags.isEmpty() ? "-" : tags,
                availableDays.isEmpty() ? "-" : availableDays);

        StringBuilder result = new StringBuilder(baseDetails);

        List<RunTiming> timings = athlete.getRunTimings();

        result.append("\nRun Timings:\n");

        if (timings.isEmpty()) {
            result.append("  No run timings recorded.\n");
        } else {
            int i = 1;
            for (RunTiming timing : timings) {
                result.append("  ")
                        .append(i++)
                        .append(". ")
                        .append(timing.getPrintFormat())
                        .append("\n");
            }
        }

        return new CommandResult(result.toString());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ViewAthleteCommand)) {
            return false;
        }

        ViewAthleteCommand otherCommand = (ViewAthleteCommand) other;
        return targetIndex.equals(otherCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
