package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.RunTiming;

/**
 * Adds a run timing record to an existing athlete in the address book.
 *
 * <p>The athlete is identified using the index number from the currently
 * displayed athlete list. The recorded timing will be added to the athlete's
 * list of recorded run timings.</p>
 *
 * <p>If the newly added timing is faster than all previously recorded timings,
 * it will be considered the athlete's new personal best and an additional
 * confirmation message will be shown.</p>
 */
public class AddTimingCommand extends Command {

    /** Command word used to invoke this command. */
    public static final String COMMAND_WORD = "addtime";

    /** Usage instructions for this command. */
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a run timing to the athlete identified by the index number.\n"
            + "Parameters: INDEX dist/DISTANCE min/MINUTES sec/SECONDS\n"
            + "Supported distances: 400m, 2.4km, 10km, 42km\n"
            + "Example: " + COMMAND_WORD + " 1 dist/2.4km min/10 sec/30";

    /** Logger for this command. */
    private static final Logger logger = LogsCenter.getLogger(AddTimingCommand.class);

    /** Index of the athlete whose timing is to be recorded. */
    private final Index index;

    /** The run timing record to be added. */
    private final RunTiming timing;

    /**
     * Creates an {@code AddTimingCommand}.
     *
     * @param index Index of the athlete in the displayed athlete list.
     * @param timing The run timing record to be added.
     */
    public AddTimingCommand(Index index, RunTiming timing) {
        this.index = index;
        this.timing = timing;
    }

    /**
     * Executes the add timing command.
     *
     * <p>The specified run timing is added to the athlete's timing records.
     * If the timing is the fastest recorded for the athlete, a personal
     * best notification will also be included in the result message.</p>
     *
     * @param model The model which the command should operate on.
     * @return A {@code CommandResult} containing the result message to display to the user.
     * @throws CommandException If the specified index does not correspond to a valid athlete.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        logger.info("Executing addtiming command for athlete index "
                + index.getOneBased() + " with timing " + timing);

        List<Person> athletes = model.getFilteredPersonList();

        if (index.getZeroBased() >= athletes.size()) {
            logger.warning("Invalid athlete index for addtiming: " + index.getOneBased());
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person athlete = athletes.get(index.getZeroBased());
        logger.info("Adding timing to athlete: " + athlete.getName());

        boolean fastest = athlete.addRunTiming(timing);

        String resultMessage = checkFastest(athlete, fastest);
        logger.info("AddTimingCommand completed successfully. Result: " + resultMessage.trim());

        return new CommandResult(resultMessage);
    }

    /**
     * Generates the result message after adding a timing.
     *
     * <p>If the newly added timing is the athlete's fastest recorded timing,
     * an additional message indicating a new personal best will be appended.</p>
     *
     * @param athlete The athlete whose timing was updated.
     * @param fastest {@code true} if the timing is the athlete's new personal best.
     * @return A formatted message describing the result of the operation.
     */
    private String checkFastest(Person athlete, boolean fastest) {
        String resultMessage = "Added timing for "
                + athlete.getName()
                + ": "
                + timing + "\n";

        if (fastest) {
            resultMessage += "New personal best for "
                    + timing.getDistance()
                    + ": "
                    + timing.getMinutes() + "min "
                    + timing.getSeconds() + "s\n";
            logger.info("New personal best recorded for athlete " + athlete.getName()
                    + " at distance " + timing.getDistance());
        }

        return resultMessage;
    }
}
