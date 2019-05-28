package com.contacts.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility methods for the processing & handling of dates throughout the application.
 */
public class DateUtil {
    private static final String DATE_PATTERN = "dd.MM.yyyy";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

    /**
     * Returns the date formatted into a human-readable String.
     * Utilizes the local DateTimeFormatter instance to format the dates.
     *
     * @param date the LocalDate object to be formatted into a string
     * @return the date formatted into a readable String
     */
    public static String format(LocalDate date) {
        return (date == null) ? null : DATE_FORMATTER.format(date);
    }

    /**
     * Converts a date, represented as a String, into a LocalDate object in the format of the defined DATE_PATTERN.
     * Returns null if the String could not be properly parsed.
     *
     * @param dateString the date represented as a String
     * @return a LocalDate object or null if the String could not be properly parsed
     */
    private static LocalDate parse(String dateString) {
        try {
            return DATE_FORMATTER.parse(dateString, LocalDate::from);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Validates if the provided String is an acceptable date.
     *
     * @param dateString the date, represented as a String, whose value you want to validate
     * @return true if the String is a valid date
     */
    public static boolean isValidDate(String dateString) {
        return DateUtil.parse(dateString) != null;
    }
}
