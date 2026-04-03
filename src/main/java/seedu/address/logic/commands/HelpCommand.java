package seedu.address.logic.commands;

import seedu.address.model.Model;
import seedu.address.ui.HelpWindow;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE =
            "Commands summary:\n"
                    + "------------------------------------------------------\n"
                    + "addathlete    n/NAME a/AGE p/PHONE e/EMAIL ad/ADDRESS d/START_DATE [t/TAG]... [av/AVAILABLE_DAY]...\n"
                    + "addtiming     INDEX dist/DISTANCE min/MINUTES sec/SECONDS\n"
                    + "deleteathlete INDEX\n"
                    + "deletetiming  ATHLETE_INDEX TIMING_INDEX\n"
                    + "edit          INDEX [n/NAME] [a/AGE] [p/PHONE] "
                    + "[e/EMAIL] [ad/ADDRESS] [d/START_DATE] [t/TAG]...\n"
                    + "find          KEYWORD [n/NAME] [p/PHONE] [t/TAG]... [av/AVAILABLE_DAY]...\n"
                    + "sort          by/FIELD [order/ORDER]   (fields: name, pb  |  orders: asc, desc)\n"
                    + "viewathlete   INDEX\n"
                    + "list\n"
                    + "clear\n"
                    + "exit\n"
                    + "------------------------------------------------------\n"
                    + "For full details: " + HelpWindow.USERGUIDE_URL;

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, false, false);
    }
}
