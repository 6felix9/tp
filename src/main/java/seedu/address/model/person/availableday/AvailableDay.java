package seedu.address.model.person.availableday;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Set;

/**
 * Represents an available day in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDay(String)}.
 */
public class AvailableDay {

    public static final String MESSAGE_CONSTRAINTS = "Day should be a valid day "
            + "(Mon, Tue, Wed, Thu, Fri, Sat, Sun)";
    public static final String MESSAGE_DUPLICATE_AVAILABLE_DAYS =
            "Available days should not contain duplicates.";
    private static final Set<String> VALID_DAYS = Set.of(
            "MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"
    );

    public final String availableDay;

    /**
     * Constructs a {@code availableday}.
     *
     * @param availableDay A valid day.
     */
    public AvailableDay(String availableDay) {
        requireNonNull(availableDay);
        checkArgument(isValidDay(availableDay), MESSAGE_CONSTRAINTS);
        this.availableDay = availableDay.toUpperCase();
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidDay(String test) {
        requireNonNull(test);
        return VALID_DAYS.contains(test.toUpperCase());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AvailableDay)) {
            return false;
        }

        AvailableDay otherAvailableDay = (AvailableDay) other;
        return availableDay.equals(otherAvailableDay.availableDay);
    }

    @Override
    public int hashCode() {
        return availableDay.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + availableDay + ']';
    }

}
