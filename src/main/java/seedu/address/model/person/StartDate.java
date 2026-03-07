package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Represents a Person's start date in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidStartDate(String)}
 */
public class StartDate {

    public static final String MESSAGE_CONSTRAINTS =
            "Start date must be a real date and should be in format DD/MM/YYYY ";
    private static final String VALIDATION_REGEX =
            "^(0[1-9]|[12][0-9]|3[01])/"
                    + "(0[1-9]|1[0-2])/"
                    + "\\d{4}$";
    public final String value;

    /**
     * Constructs a {@code StartDate}.
     *
     * @param date A valid start date.
     */
    public StartDate(String date) {
        requireNonNull(date);
        checkArgument(isValidStartDate(date), MESSAGE_CONSTRAINTS);
        value = date;
    }

    /**
     * Returns true if a given string is a valid start date.
     */
    public static boolean isValidStartDate(String test) {
        if (!test.matches(VALIDATION_REGEX)) {
            return false;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu")
                .withResolverStyle(ResolverStyle.STRICT);
        try {
            LocalDate.parse(test, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StartDate)) {
            return false;
        }

        StartDate otherStartDate = (StartDate) other;
        return value.equals(otherStartDate.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
