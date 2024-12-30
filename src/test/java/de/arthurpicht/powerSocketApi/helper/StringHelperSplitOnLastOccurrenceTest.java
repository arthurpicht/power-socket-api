package de.arthurpicht.powerSocketApi.helper;

import de.arthurpicht.utils.core.assertion.MethodPreconditionFailedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StringHelperSplitOnLastOccurrenceTest {

    @Test
    public void simpleTest() {
        String[] splitString = StringHelper.splitOnLastOccurrence("dies:ist:ein:Test", ":");
        assertArrayEquals(new String[]{"dies:ist:ein", "Test"}, splitString);
    }

    @Test
    public void oneOccurrenceTest() {
        String[] splitString = StringHelper.splitOnLastOccurrence("ein:Test", ":");
        assertArrayEquals(new String[]{"ein", "Test"}, splitString);
    }

    @Test
    public void delimiterAsLastSignTest() {
        String[] splitString = StringHelper.splitOnLastOccurrence("test:", ":");
        assertArrayEquals(new String[]{"test", ""}, splitString);
    }

    @Test
    public void delimiterAsFirstSignTest() {
        String[] splitString = StringHelper.splitOnLastOccurrence(":test", ":");
        assertArrayEquals(new String[]{"", "test"}, splitString);
    }

    @Test
    public void longDelimiter() {
        String[] splitString = StringHelper.splitOnLastOccurrence("dies::ist::ein::Test", "::");
        assertArrayEquals(new String[]{"dies::ist::ein", "Test"}, splitString);
    }

    @Test
    public void longDelimiterAsLastSignTest() {
        String[] splitString = StringHelper.splitOnLastOccurrence("test::", "::");
        assertArrayEquals(new String[]{"test", ""}, splitString);
    }

    @Test
    public void longDelimiterAsFirstSignTest() {
        String[] splitString = StringHelper.splitOnLastOccurrence("::test", "::");
        assertArrayEquals(new String[]{"", "test"}, splitString);
    }


    @Test
    public void emptyDelimiterTest() {
        assertThrows(MethodPreconditionFailedException.class, () -> StringHelper.splitOnLastOccurrence(":test", ""));
    }

    @Test
    public void emptyStringTest() {
        assertThrows(MethodPreconditionFailedException.class, () -> StringHelper.splitOnLastOccurrence("", ":"));
    }

}