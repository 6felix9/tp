package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents an Emergency Contact for a Person.
 * Guarantees: immutable; is valid as declared in {@link #isValidEmergencyContact(String)}
 */
public class EmergencyContact {

    public static final String MESSAGE_CONSTRAINTS =
            "Emergency contact should not be blank.";

    public final String value;

    /**
     * Constructs an {@code EmergencyContact}.
     *
     * @param emergencyContact A valid emergency contact.
     */
    public EmergencyContact(String emergencyContact) {
        requireNonNull(emergencyContact);
        if (!isValidEmergencyContact(emergencyContact)) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
        value = emergencyContact;
    }

    /**
     * Returns true if a given string is a valid emergency contact.
     */
    public static boolean isValidEmergencyContact(String test) {
        return !test.trim().isEmpty();
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
        if (!(other instanceof EmergencyContact)) {
            return false;
        }
        EmergencyContact otherEc = (EmergencyContact) other;
        return value.equals(otherEc.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
