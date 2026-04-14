package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.RunTiming;
import seedu.address.testutil.PersonBuilder;

public class SortCommandTest {

    @Test
    public void execute_sortByPbAscending_forRequestedDistance() throws Exception {
        Person fasterFourHundred = createPersonWithTimings("Alpha", new RunTiming("400m", 0, 50.0));
        Person slowerFourHundred = createPersonWithTimings("Bravo", new RunTiming("400m", 0, 55.0));
        Person onlyOtherDistance = createPersonWithTimings("Charlie", new RunTiming("2.4km", 10, 0.0));

        ModelStubWithPersons model = new ModelStubWithPersons(onlyOtherDistance,
                slowerFourHundred, fasterFourHundred);

        new SortCommand(SortCommand.SortField.PB, "400m", SortCommand.SortOrder.ASC).execute(model);

        assertEquals(List.of(fasterFourHundred, slowerFourHundred, onlyOtherDistance),
                model.getFilteredPersonList());
    }

    @Test
    public void execute_sortByPbDescending_forRequestedDistance() throws Exception {
        Person fasterTenKm = createPersonWithTimings("Alpha", new RunTiming("10km", 38, 0.0));
        Person slowerTenKm = createPersonWithTimings("Bravo", new RunTiming("10km", 42, 0.0));
        Person onlyOtherDistance = createPersonWithTimings("Charlie", new RunTiming("400m", 0, 50.0));

        ModelStubWithPersons model = new ModelStubWithPersons(onlyOtherDistance,
                fasterTenKm, slowerTenKm);

        new SortCommand(SortCommand.SortField.PB, "10km", SortCommand.SortOrder.DESC).execute(model);

        assertEquals(List.of(slowerTenKm, fasterTenKm, onlyOtherDistance),
                model.getFilteredPersonList());
    }

    @Test
    public void execute_sortByPbAscending_doesNotUseOverallFastestTime() throws Exception {
        Person amelia = createPersonWithTimings("Amelia",
                new RunTiming("400m", 1, 0.0),
                new RunTiming("2.4km", 10, 0.0),
                new RunTiming("10km", 40, 0.0));

        Person jack = createPersonWithTimings("Jack",
                new RunTiming("400m", 0, 55.0),
                new RunTiming("2.4km", 20, 0.0),
                new RunTiming("10km", 180, 0.0));

        ModelStubWithPersons model = new ModelStubWithPersons(jack, amelia);

        new SortCommand(SortCommand.SortField.PB, "2.4km", SortCommand.SortOrder.ASC).execute(model);

        assertEquals(List.of(amelia, jack), model.getFilteredPersonList());
    }

    @Test
    public void execute_sortByNameAscending_ignoresDistance() throws Exception {
        Person charlie = createPersonWithTimings("Charlie", new RunTiming("400m", 0, 50.0));
        Person alpha = createPersonWithTimings("Alpha", new RunTiming("2.4km", 10, 0.0));

        ModelStubWithPersons model = new ModelStubWithPersons(charlie, alpha);

        new SortCommand(SortCommand.SortField.NAME, null, SortCommand.SortOrder.ASC).execute(model);

        assertEquals(List.of(alpha, charlie), model.getFilteredPersonList());
    }

    private Person createPersonWithTimings(String name, RunTiming... timings) {
        Person person = new PersonBuilder().withName(name).build();
        for (RunTiming timing : timings) {
            person.addRunTiming(timing);
        }
        return person;
    }

    private static class ModelStubWithPersons implements Model {
        private final ObservableList<Person> persons;

        ModelStubWithPersons(Person... persons) {
            this.persons = FXCollections.observableArrayList(persons);
        }

        @Override
        public void sortFilteredPersonList(Comparator<Person> comparator) {
            FXCollections.sort(persons, comparator);
        }

        @Override
        public void reapplyCurrentSort() {
            // no-op in test stub
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            return persons;
        }

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }
}
