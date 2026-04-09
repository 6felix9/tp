package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Age;
import seedu.address.model.person.Email;
import seedu.address.model.person.EmergencyContact;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.StartDate;
import seedu.address.model.person.availableday.AvailableDay;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Age("18"), new Phone("87438807"),
                    new Email("alexyeoh@example.com"), new Address("Blk 30 Geylang Street 29, #06-40"),
                    new EmergencyContact("Mom 91234567"), new StartDate("01/01/2001"),
                    getTagSet("sprinter", "team A"),
                    getAvailableDaySet("Mon", "Wed")),
            new Person(new Name("Bernice Yu"), new Age("25"), new Phone("99272758"),
                    new Email("berniceyu@example.com"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    new EmergencyContact("Dad 92345678"), new StartDate("02/02/2002"),
                    getTagSet("sprinter", "team B"), getAvailableDaySet("Mon", "Wed")),
            new Person(new Name("Charlotte Oliveiro"), new Age("63"), new Phone("93210283"),
                    new Email("charlotte@example.com"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    new EmergencyContact("Sister 93456789"), new StartDate("03/03/2003"),
                    getTagSet("marathoner", "team B"),
                    getAvailableDaySet("Mon")),
            new Person(new Name("David Li"), new Age("34"), new Phone("91031282"),
                    new Email("lidavid@example.com"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    new EmergencyContact("Brother 94567890"), new StartDate("04/04/2004"),
                    getTagSet("sprinter", "team B"),
                    getAvailableDaySet("Wed")),
            new Person(new Name("Irfan Ibrahim"), new Age("20"), new Phone("92492021"),
                    new Email("irfan@example.com"), new Address("Blk 47 Tampines Street 20, #17-35"),
                    new EmergencyContact("Uncle 95678901"), new StartDate("05/05/2005"),
                    getTagSet("sprinter", "team A"),
                    getAvailableDaySet("Thu")),
            new Person(new Name("Roy Balakrishnan"), new Age("29"), new Phone("92624417"),
                    new Email("royb@example.com"), new Address("Blk 45 Aljunied Street 85, #11-31"),
                    new EmergencyContact("Aunt 96789012"), new StartDate("06/06/2006"),
                    getTagSet("marathoner"),
                    getAvailableDaySet("Fri"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    public static Set<AvailableDay> getAvailableDaySet(String... strings) {
        return Arrays.stream(strings)
                .map(AvailableDay::new)
                .collect(Collectors.toSet());
    }

}
