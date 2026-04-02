package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.availableday.AvailableDay;

/**
 * Jackson-friendly version of {@link AvailableDay}.
 */
public class JsonAdaptedAvailableDay {

    private final String availableDay;

    /**
     * Constructs a {@code JsonAdaptedAvailableDay} with the given {@code day}.
     */
    @JsonCreator
    public JsonAdaptedAvailableDay(String availableDay) {
        this.availableDay = availableDay;
    }

    /**
     * Converts a given {@code AvailableDay} into this class for Jackson use.
     */
    public JsonAdaptedAvailableDay(AvailableDay source) {
        availableDay = source.availableDay;
    }

    @JsonValue
    public String getAvailableDay() {
        return availableDay;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public AvailableDay toModelType() throws IllegalValueException {
        if (!AvailableDay.isValidDay(availableDay)) {
            throw new IllegalValueException(AvailableDay.MESSAGE_CONSTRAINTS);
        }
        return new AvailableDay(availableDay);
    }

}
