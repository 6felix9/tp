package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a recorded run timing for an athlete.
 *
 * <p>A {@code RunTiming} stores the race distance together with the completion time,
 * expressed in minutes and seconds. This class is immutable.</p>
 */
public class RunTiming {

    /** Message shown when the provided distance does not match a supported distance. */
    public static final String MESSAGE_DISTANCE_CONSTRAINTS =
            "Distance must be one of: 2.4km, 400m, 10km, 42km";

    /** Distance category for this timing record. */
    private final String distance;

    /** Minutes component of the recorded timing. */
    private final int minutes;

    /** Seconds component of the recorded timing. */
    private final double seconds;

    /**
     * Creates a {@code RunTiming}.
     *
     * @param distance Distance category for the timing.
     * @param minutes Minutes component of the timing.
     * @param seconds Seconds component of the timing.
     * @throws NullPointerException if {@code distance} is null.
     */
    public RunTiming(String distance, int minutes, double seconds) {
        requireNonNull(distance);
        if (!isValidDistance(distance)) {
            throw new IllegalArgumentException(MESSAGE_DISTANCE_CONSTRAINTS);
        }

        this.distance = distance;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    /**
     * Returns whether the given distance is supported by the application.
     *
     * @param test Distance string to validate.
     * @return {@code true} if the distance is supported, {@code false} otherwise.
     */
    public static boolean isValidDistance(String test) {
        return "2.4km".equals(test)
                || "400m".equals(test)
                || "10km".equals(test)
                || "42km".equals(test);
    }

    /**
     * Returns the minutes component of the run timing.
     *
     * @return The recorded minutes.
     */
    public int getMinutes() {
        return this.minutes;
    }

    /**
     * Returns the seconds component of the run timing.
     *
     * @return The recorded seconds.
     */
    public double getSeconds() {
        return this.seconds;
    }

    /**
     * Returns the distance category of the run timing.
     *
     * @return The distance category.
     */
    public String getDistance() {
        return this.distance;
    }

    /**
     * Returns the total duration of the run timing in seconds.
     *
     * <p>This is useful when comparing timings to determine the fastest record
     * for a given distance.</p>
     *
     * @return Total time in seconds.
     */
    public double getTotalSeconds() {
        return minutes * 60 + seconds;
    }

    /**
     * Returns a compact display format of this timing.
     *
     * @return A formatted timing string, for example {@code "2.4km, 10min 30s"}.
     */
    public String getPrintFormat() {
        return this.distance + ", " + this.minutes + "min " + this.seconds + "s";
    }

    /**
     * Returns a human-readable string representation of this timing.
     *
     * @return A formatted timing string, for example {@code "2.4km in 10min 30s"}.
     */
    @Override
    public String toString() {
        return this.distance + " in " + this.minutes + "min " + this.seconds + "s";
    }

    /**
     * Returns {@code true} if both timings have the same distance and time values.
     *
     * @param other The object to compare against.
     * @return {@code true} if both objects represent the same timing, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof RunTiming)) {
            return false;
        }
        RunTiming otherTiming = (RunTiming) other;
        return distance.equals(otherTiming.distance)
                && minutes == otherTiming.minutes
                && Double.compare(seconds, otherTiming.seconds) == 0;
    }

    /**
     * Returns the hash code of this timing.
     *
     * @return Hash code value for this object.
     */
    @Override
    public int hashCode() {
        return distance.hashCode() + 31 * minutes + Double.hashCode(seconds);
    }
}
