package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Sorts the displayed athlete list by the specified field and order.
 */
public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the displayed athlete list by the specified field.\n"
            + "Parameters: by/FIELD [dist/DISTANCE] [ord/ORDER]\n"
            + "Supported fields: name, pb\n"
            + "For pb sorting, dist/DISTANCE is required.\n"
            + "Supported distances: 400m, 2.4km, 10km, 42km\n"
            + "Supported orders: asc, desc\n"
            + "Examples: " + COMMAND_WORD + " by/name ord/asc\n"
            + "          " + COMMAND_WORD + " by/pb dist/400m ord/desc";

    public static final String MESSAGE_INVALID_SORT_FIELD =
            "Invalid sort field: '%s'.\nSupported fields: name, pb\nExample: sort by/name ord/desc";

    public static final String MESSAGE_INVALID_SORT_ORDER =
            "Invalid sort order: '%s'.\nSupported orders: asc, desc\nExample: sort by/pb dist/400m ord/desc";

    public static final String MESSAGE_SUCCESS = "Sorted athletes by %s in %s order.";
    public static final String MESSAGE_SUCCESS_WITH_DISTANCE = "Sorted athletes by %s for %s in %s order.";

    private final SortField sortField;
    private final String distance;
    private final SortOrder sortOrder;

    /**
     * Creates a SortCommand to sort the displayed athlete list by the given field and order.
     *
     * @param sortField Field to sort the displayed athlete list by.
     * @param distance Distance to scope personal-best sorting to. Ignored for non-pb sorts.
     * @param sortOrder Order to sort the displayed athlete list in.
     */
    public SortCommand(SortField sortField, String distance, SortOrder sortOrder) {
        this.sortField = sortField;
        this.distance = distance;
        this.sortOrder = sortOrder;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Comparator<Person> comparator = getComparator();
        model.sortFilteredPersonList(comparator);
        if (sortField == SortField.PB) {
            return new CommandResult(String.format(MESSAGE_SUCCESS_WITH_DISTANCE, sortField, distance, sortOrder));
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, sortField, sortOrder));
    }

    /**
     * Returns a comparator corresponding to the configured sort field and sort order.
     * For personal best sorting, athletes without run timings are placed after athletes
     * with recorded timings regardless of sort direction.
     *
     * @return Comparator used to sort the displayed athlete list.
     * @throws CommandException if the sort field is unsupported.
     */
    private Comparator<Person> getComparator() throws CommandException {
        switch (sortField) {
        case NAME:
            Comparator<Person> nameComparator = Comparator.comparing(
                    person -> person.getName().toString().toLowerCase());
            return sortOrder == SortOrder.DESC ? nameComparator.reversed() : nameComparator;

        case PB:
            Comparator<Person> pbComparator = Comparator
                    .comparing((Person person) -> person.getBestTimeForDistance(distance) == Double.MAX_VALUE)
                    .thenComparingDouble(person -> person.getBestTimeForDistance(distance))
                    .thenComparing(person -> person.getName().toString().toLowerCase());

            return sortOrder == SortOrder.DESC
                    ? Comparator
                    .comparing((Person person) -> person.getBestTimeForDistance(distance) == Double.MAX_VALUE)
                    .thenComparing(
                            Comparator.comparingDouble((Person person) -> person.getBestTimeForDistance(distance))
                                    .reversed())
                    .thenComparing(person -> person.getName().toString().toLowerCase())
                    : pbComparator;

        default:
            throw new CommandException("Unsupported sort field: " + sortField);
        }
    }

    private boolean hasTimingForDistance(Person person) {
        return getSortTimeForDistance(person) != Double.MAX_VALUE;
    }

    private double getSortTimeForDistance(Person person) {
        return person.getBestTimeForDistance(distance);
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
                && Objects.equals(distance, otherSortCommand.distance)
                && sortOrder.equals(otherSortCommand.sortOrder);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("sortField", sortField)
                .add("distance", distance)
                .add("sortOrder", sortOrder)
                .toString();
    }

    public Optional<String> getDistance() {
        return Optional.ofNullable(distance);
    }

    /**
     * Represents the supported fields that the displayed athlete list can be sorted by.
     */
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

    /**
     * Represents the supported fields that the displayed athlete list can be sorted by.
     */
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
