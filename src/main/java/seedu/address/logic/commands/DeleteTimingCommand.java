package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.RunTiming;

/**
 * Deletes a run timing record from an existing athlete in the address book.
 */
public class DeleteTimingCommand extends Command {

    public static final String COMMAND_WORD = "deltime";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Deletes a run timing from the athlete identified by the index number.\n"
        + "Parameters: ATHLETE_INDEX TIMING_INDEX\n"
        + "Example: " + COMMAND_WORD + " 1 2";

    private final Index athleteIndex;
    private final Index timingIndex;

    /**
     * Creates a {@code DeleteTimingCommand}.
     *
     * @param athleteIndex Index of the athlete in the displayed athlete list.
     * @param timingIndex Index of the timing in the athlete's timing list.
     */
    public DeleteTimingCommand(Index athleteIndex, Index timingIndex) {
        this.athleteIndex = athleteIndex;
        this.timingIndex = timingIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> athletes = model.getFilteredPersonList();

        if (athleteIndex.getZeroBased() >= athletes.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person athlete = athletes.get(athleteIndex.getZeroBased());
        List<RunTiming> runTimings = athlete.getRunTimings();

        if (timingIndex.getZeroBased() >= runTimings.size()) {
            throw new CommandException("The timing index provided is invalid.");
        }

        RunTiming deletedTiming = athlete.deleteRunTiming(timingIndex.getZeroBased());

        String resultMessage = "Deleted timing for "
            + athlete.getName()
            + ": "
            + deletedTiming;

        return new CommandResult(resultMessage);
    }
}
