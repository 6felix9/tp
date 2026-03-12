package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a recorded 2.4km run timing for an athlete.
 *
 * <p>A {@code RunTiming} stores the completion time of a fixed-distance
 * 2.4 kilometre run using minutes and seconds. This format mirrors how
 * timing is typically recorded in athletics training and fitness tests
 * such as the IPPT 2.4km run.</p>
 *
 * <p>This class is immutable. Once created, the stored timing values
 * cannot be modified.</p>
 *
 * <p>The run distance is fixed at {@code 2.4km} and therefore not stored
 * as a mutable field.</p>
 */
public class RunTiming {

    /** Fixed distance of the run in kilometres. */
    private static final double DISTANCE = 2.4;

    /** Minutes component of the recorded timing. */
    private final int minutes;

    /** Seconds component of the recorded timing. */
    private final double seconds;

    /**
     * Constructs a {@code RunTiming}.
     *
     * @param minutes The minutes component of the timing.
     * @param seconds The seconds component of the timing.
     * @throws NullPointerException if any argument is null.
     */
    public RunTiming(int minutes, double seconds) {
        requireNonNull(minutes);
        requireNonNull(seconds);

        this.minutes = minutes;
        this.seconds = seconds;
    }

    /**
     * Returns the minutes component of the run timing.
     *
     * @return the recorded minutes.
     */
    public int getMinutes() {
        return minutes;
    }

    /**
     * Returns the seconds component of the run timing.
     *
     * @return the recorded seconds.
     */
    public double getSeconds() {
        return seconds;
    }

    /**
     * Returns the fixed run distance.
     *
     * @return the run distance in kilometres (always {@code 2.4}).
     */
    public double getDistance() {
        return DISTANCE;
    }

    /**
     * Returns the total run time in seconds.
     *
     * <p>This value is useful for comparing run timings,
     * such as when calculating an athlete's personal best.</p>
     *
     * @return total run time in seconds.
     */
    public double getTotalSeconds() {
        return minutes * 60 + seconds;
    }

    /**
     * Returns a formatted string representation suitable for compact display.
     *
     * @return formatted timing string (e.g. {@code "2.4km, 10min 30s"}).
     */
    public String getPrintFormat() {
        return DISTANCE + "km, " + minutes + "min " + seconds + "s";
    }

    /**
     * Returns the standard string representation of this run timing.
     *
     * @return formatted timing string (e.g. {@code "2.4km in 10min 30s"}).
     */
    @Override
    public String toString() {
        return DISTANCE + "km in " + minutes + "min " + seconds + "s";
    }
}
