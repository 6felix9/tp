package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.RunTiming;

/**
 * Jackson-friendly version of {@link RunTiming}.
 */
class JsonAdaptedRunTiming {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "RunTiming's %s field is missing!";

    private final String distance;
    private final int minutes;
    private final double seconds;

    @JsonCreator
    public JsonAdaptedRunTiming(@JsonProperty("distance") String distance,
                                @JsonProperty("minutes") int minutes,
                                @JsonProperty("seconds") double seconds) {
        this.distance = distance;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public JsonAdaptedRunTiming(RunTiming source) {
        this.distance = source.getDistance();
        this.minutes = source.getMinutes();
        this.seconds = source.getSeconds();
    }

    public RunTiming toModelType() throws IllegalValueException {
        if (distance == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "distance"));
        }
        if (!RunTiming.isValidDistance(distance)) {
            throw new IllegalValueException(RunTiming.MESSAGE_DISTANCE_CONSTRAINTS);
        }
        if (minutes < 0) {
            throw new IllegalValueException("Invalid minutes: must be a non-negative integer");
        }
        if (seconds < 0 || seconds >= 60) {
            throw new IllegalValueException("Invalid seconds: must be between 0 and 59.99");
        }
        if (minutes == 0 && seconds == 0) {
            throw new IllegalValueException("Invalid timing: total time must be greater than 0");
        }
        return new RunTiming(distance, minutes, seconds);
    }
}
