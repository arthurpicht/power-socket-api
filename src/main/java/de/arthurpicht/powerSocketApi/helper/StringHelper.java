package de.arthurpicht.powerSocketApi.helper;

import java.util.Arrays;
import java.util.List;

import static de.arthurpicht.utils.core.assertion.MethodPreconditions.assertArgumentNotNull;
import static de.arthurpicht.utils.core.assertion.MethodPreconditions.assertArgumentNotNullAndNotEmpty;

public class StringHelper {

    public static String[] splitOnLastOccurrence(String string, String delimiter) {
        assertArgumentNotNullAndNotEmpty("string", string);
        assertArgumentNotNullAndNotEmpty("delimiter", delimiter);
        if (!string.contains(delimiter))
            throw new IllegalArgumentException("String to be split does not contain delimiter.");

        int lastIndex = string.lastIndexOf(delimiter);
        String stringPre = string.substring(0, lastIndex);
        String stringPost = string.substring(lastIndex + delimiter.length());

        return new String[]{stringPre, stringPost};
    }

    /**
     * Splits the specified string into substrings based on the line breaks it contains.
     * Both Linux line breaks and Windows line breaks are taken into account.
     *
     * @param string string to be split
     * @return List of strings
     */
    public static List<String> splitByLineBreaks(String string) {
        assertArgumentNotNull("string", string);
        return Arrays.asList(string.split("\\r?\\n"));
    }

}
