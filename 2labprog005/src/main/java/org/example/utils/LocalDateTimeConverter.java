package org.example.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeConverter {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
      Converts LocalDateTime to a formatted string for CSV storage.

    public static String toString(LocalDateTime dateTime) {
        return dateTime != null ? dateTime.format(DATE_FORMATTER) : "";
    }

    /**
     * Parses a formatted string from CSV into a LocalDateTime object.
     */
    public static LocalDateTime fromString(String dateTimeStr) {
        return (dateTimeStr == null || dateTimeStr.isEmpty()) ? null : LocalDateTime.parse(dateTimeStr, DATE_FORMATTER);
    }
}