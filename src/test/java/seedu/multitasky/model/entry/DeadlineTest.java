package seedu.multitasky.model.entry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import seedu.multitasky.model.util.EntryBuilder;
import seedu.multitasky.model.util.TagSetBuilder;

//@@author A0126623L
public class DeadlineTest {

    public static final Deadline[] SAMPLE_DEADLINES_ARRAY_DATA = getSampleDeadlineArrayData();
    public Deadline deadline1, deadline2, deadline3, deadline4, deadline5;

    // @@author A0126623L
    /**
     * Gets an array of 5 sample deadlines.
     * The first two deadlines are meaningfully equivalent, the remaining are unique.
     *
     * @return Deadline[] of 5 sample deadlines.
     */
    public static Deadline[] getSampleDeadlineArrayData() {

        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2017, 6, 7, 18, 30); // 7th July 2017, 6:30pm
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2017, 6, 8, 18, 30); // 8th July 2017, 6:30pm
        Calendar calendar3 = Calendar.getInstance();
        calendar3.set(2017, 6, 9, 18, 30); // 9th July 2017, 6:30pm

        try {
            return new Deadline[] {
                new Deadline(new Name("SampleName1"), calendar1, TagSetBuilder.generateTagSet("tag1")),
                new Deadline(new Name("SampleName1"), calendar1, TagSetBuilder.generateTagSet("tag1")),
                new Deadline(new Name("SampleName2"), calendar1, TagSetBuilder.generateTagSet("tag1")),
                new Deadline(new Name("SampleName1"), calendar2, TagSetBuilder.generateTagSet("tag1")),
                new Deadline(new Name("SampleName1"), calendar1, TagSetBuilder.generateTagSet("tag2"))
            };
        } catch (Exception e) {
            fail("Deadline array initialisation failed.");
            return null;
        }
    }
    // @@author

    // @@author A0126623L
    @Before
    public void setUp() {
        deadline1 = SAMPLE_DEADLINES_ARRAY_DATA[0];
        deadline2 = SAMPLE_DEADLINES_ARRAY_DATA[1];
        deadline3 = SAMPLE_DEADLINES_ARRAY_DATA[2];
        deadline4 = SAMPLE_DEADLINES_ARRAY_DATA[3];
        deadline5 = SAMPLE_DEADLINES_ARRAY_DATA[4];
    }

    // @@author A0126623L
    @Test
    public void getNameTest() {
        assertEquals("error at getName()", "SampleName1", deadline1.getName().fullName);
    }

    // @@author A0126623L
    @Test
    public void getTagsTest() {
        // Same tags
        assertTrue(deadline1.getTags().equals(deadline2.getTags()));

        // Different tags
        assertFalse(deadline1.getTags().equals(deadline5.getTags()));
    }

    // @@author A0126623L
    @Test
    public void resetDataTest() {
        try {
            Entry tempDeadline = new EntryBuilder(deadline1).build();
            assert (tempDeadline instanceof Deadline) : "Error in DeadlineTest.resetDataTest().";

            Deadline tester999 = (Deadline) tempDeadline;
            assertFalse(tester999.equals(deadline3));
            tester999.resetData(deadline3);
            assertTrue(tester999.equals(deadline3));

        } catch (Exception e) {
            fail("DeadlineTest.resetDataTest() failed.");
        }
    }

    // @@author A0126623L
    @Test
    public void toStringTest() {
        assertEquals("Deadline formatting is wrong",
                     "SampleName1 Deadline: Jul 7, 2017 6:30 PM Tags: [tag1]",
                     deadline1.toString());
    }

    // @@author A0126623L
    @Test
    public void equalsTest() {
        // Equal
        assertTrue(deadline1.equals(deadline2));

        // Not equal
        assertFalse(deadline1.equals(deadline3));
        assertFalse(deadline1.equals(deadline4));
        assertFalse(deadline1.equals(deadline5));
    }

}
