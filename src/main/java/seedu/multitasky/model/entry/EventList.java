package seedu.multitasky.model.entry;

import java.util.List;

import seedu.multitasky.model.entry.exceptions.DuplicateEntryException;
import seedu.multitasky.model.entry.exceptions.EntryNotFoundException;
import seedu.multitasky.model.entry.util.Comparators;

//@@author A0126623L
/**
 * A list of Event objects that does not allow nulls.
 */
public class EventList extends EntryList {

    // @@author A0125586X
    public EventList() {
        super();
        comparator = Comparators.EVENT_DEFAULT;
    }

    // @@author A0126623L
    /**
     * Adds an event's reference to the list.
     *
     * @param toAdd is of type Event and must not be null.
     * @throws DuplicateEntryException if {@code toAdd} already exists in the list.
     */
    @Override
    public void add(ReadOnlyEntry toAdd) throws DuplicateEntryException {
        super.add(toAdd);

        if (!(toAdd instanceof Event)) {
            throw new AssertionError("Non-Event type cannot be added to an EventList.");
        }

        internalList.add((Event) toAdd);
        sortInternalList();
    }

    // @@author A0126623L
    /**
     * Checks if a given event has overlapping time with any existing active events in the event list.
     */
    public boolean hasOverlappingEvent(ReadOnlyEntry other) {
        for (Entry existingEntry : internalList) {
            assert (existingEntry instanceof Event) : "Non-event should not exist in an event list.";
            if (!(other instanceof OverlapCapable)) {
                return false;
            }
            Event otherEvent = (Event) other;
            Event existingEvent = (Event) existingEntry;

            if (existingEvent.isActive()
                && existingEvent.overlapsWith(otherEvent)) {
                return true;
            }
        }
        return false;
    }

    // @@author A0126623L
    /**
     * Checks if a given event will have overlapping time with any existing active events in the
     * event list after being updated.
     */
    public boolean hasOverlappingEventAfterUpdate(ReadOnlyEntry target, ReadOnlyEntry prospectiveEntry) {
        for (Entry existingEntry : internalList) {
            assert (existingEntry instanceof Event) : "Non-event should not exist in an event list.";
            if (!(prospectiveEntry instanceof OverlapCapable)) {
                return false;
            }
            Event prospectiveEvent = (Event) prospectiveEntry;
            Event existingEvent = (Event) existingEntry;

            if (existingEvent.isActive()
                && existingEvent.overlapsWith(prospectiveEvent)
                && !(existingEvent.equals(target))) {
                return true;
            }
        }
        return false;
    }

    // @@author A0125586X
    /**
     * Updates an existing entry with the data of a given Entry.
     * The list is sorted after updating in case start date was changed.
     *
     * @throws EntryNotFoundException if {@code target} could not be found in the list.
     * @throws DuplicateEntryException if {@code editedEntry} already exists in the list.
     */
    @Override
    public void updateEntry(ReadOnlyEntry target, ReadOnlyEntry editedEntry)
            throws DuplicateEntryException, EntryNotFoundException {
        super.updateEntry(target, editedEntry);
        sortInternalList();
    }

    // @@author A0126623L
    /**
     * Clears the current list of events and add all elements from the list of entries given.
     *
     * @param entries must be a list of events.
     */
    public void setEntries(List<? extends ReadOnlyEntry> entries) throws DuplicateEntryException {
        final EventList replacement = new EventList();
        for (final ReadOnlyEntry entry : entries) {
            replacement.add(entry);     // Type check is done within add().
        }
        super.setEntries(replacement);
    }

}
