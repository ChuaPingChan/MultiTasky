package seedu.multitasky.model.entry;

import static seedu.multitasky.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Calendar;
import java.util.Objects;
import java.util.Set;

import seedu.multitasky.model.tag.Tag;

public class Deadline extends Entry {

    private Calendar _endDateAndTime;

    /**
     * Every field must be present and not null.
     */
    public Deadline(Name name, Calendar endDateAndTime, Set<Tag> tags) {
        super(name, tags);
        requireAllNonNull(endDateAndTime);
        _endDateAndTime = endDateAndTime;
    }

    /**
     * Creates a copy of the given ReadOnlyEntry.
     *
     * @param source must be type Deadline.
     */
    public Deadline(ReadOnlyEntry source) {
        super(source.getName(), source.getTags());

        // Checks if source has an end time.
        requireAllNonNull(source.getEndDateAndTime());
        setEndDateAndTime(source.getEndDateAndTime());
    }

    /**
     * Updates this entry with the details of {@code replacement}.
     *
     * @param replacement must be of type Deadline.
     */
    @Override
    public void resetData(ReadOnlyEntry replacement) {
        super.resetData(replacement);

        assert (replacement instanceof Deadline);
        setEndDateAndTime(replacement.getEndDateAndTime());
    }

    public String getStartDateAndTimeString() {
        return null; // Deadlines has no start time.
    }

    @Override
    public Calendar getStartDateAndTime() {
        Calendar startDateAndTime = null; // Deadlines has no start time.
        return startDateAndTime;
    }

    public void setEndDateAndTime(Calendar endDateAndTime) {
        _endDateAndTime = endDateAndTime;
    }

    public String getEndDateAndTimeString() {
        return dateFormatter.format(getEndDateAndTime().getTime());
    }

    @Override
    public Calendar getEndDateAndTime() {
        return _endDateAndTime;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
               || this.isSameStateAs((ReadOnlyEntry) other);
    }

    // @@author A0126623L
    @Override
    public boolean isSameStateAs(ReadOnlyEntry other) {
        return (other instanceof Deadline
                && this.getName().equals(other.getName()) // instanceof handles nulls
                && this.getEndDateAndTime().equals(other.getEndDateAndTime())
                && this.getTags().equals(other.getTags()));
    }
    // @@author

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(getName(), getEndDateAndTime(), getTags());
    }

    // @@author A0126623L
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
               .append(" Deadline: ")
               .append(dateFormatter.format(getEndDateAndTime().getTime()))
               .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }
    // @@author

}
