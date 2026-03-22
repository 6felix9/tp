package seedu.address.logic.commands;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

import java.util.Comparator;

import static java.util.Objects.requireNonNull;

public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the displayed athlete list by the specified field.\n"
            + "Parameters: by/FIELD [order/ORDER]\n"
            + "Supported fields: name, pb\n"
            + "Supported orders: asc, desc\n"
            + "Example: " + COMMAND_WORD + " by/name order/asc";

    public static final String MESSAGE_SUCCESS = "Sorted athletes by %s in %s order.";

    private final SortField sortField;
    private final SortOrder sortOrder;

    public SortCommand(SortField sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Comparator<Person> comparator = getComparator();
        model.sortFilteredPersonList(comparator);
        return new CommandResult(String.format(MESSAGE_SUCCESS, sortField, sortOrder));
    }

    private Comparator<Person> getComparator() throws CommandException {
        switch (sortField) {
            case NAME:
                Comparator<Person> nameComparator = Comparator.comparing(
                        person -> person.getName().toString().toLowerCase());
                return sortOrder == SortOrder.DESC ? nameComparator.reversed() : nameComparator;

            case PB:
                if (sortOrder == SortOrder.ASC) {
                    return Comparator
                            .comparing((Person person) -> !person.hasRunTimings())
                            .thenComparingDouble(Person::getBestTime);
                } else {
                    return Comparator
                            .comparing((Person person) -> !person.hasRunTimings())
                            .thenComparing(Comparator.comparingDouble(Person::getBestTime).reversed());
                }

            default:
                throw new CommandException("Unsupported sort field: " + sortField);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SortCommand)) {
            return false;
        }

        SortCommand otherSortCommand = (SortCommand) other;
        return sortField.equals(otherSortCommand.sortField)
                && sortOrder.equals(otherSortCommand.sortOrder);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("sortField", sortField)
                .add("sortOrder", sortOrder)
                .toString();
    }

    public enum SortField {
        NAME,
        PB;

        @Override
        public String toString() {
            switch (this) {
                case NAME:
                    return "name";
                case PB:
                    return "personal best";
                default:
                    throw new AssertionError("Unknown sort field: " + this);
            }
        }
    }

    public enum SortOrder {
        ASC,
        DESC;

        @Override
        public String toString() {
            switch (this) {
                case ASC:
                    return "ascending";
                case DESC:
                    return "descending";
                default:
                    throw new AssertionError("Unknown sort order: " + this);
            }
        }
    }
}