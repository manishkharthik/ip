package salah;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public final class DateTimeParser {
    private DateTimeParser() {
        // leave as empty
    }

    private static final DateTimeFormatter[] DATE_TIME_PATTERNS = new DateTimeFormatter[] {
        DateTimeFormatter.ofPattern("d/M/uuuu HHmm"),
        DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm"),
        DateTimeFormatter.ofPattern("uuuu-MM-dd HHmm")
    };
    private static final DateTimeFormatter[] DATE_ONLY_PATTERNS = new DateTimeFormatter[] {
        DateTimeFormatter.ISO_LOCAL_DATE,              
        DateTimeFormatter.ofPattern("d/M/uuuu")
    };

    // Try to parse to LocalDateTime; if only a date is given, return date at 00:00
    public static LocalDateTime parseFlexible(String raw) {
        String s = raw.trim();

        // try datetime first
        for (DateTimeFormatter fmt : DATE_TIME_PATTERNS) {
            try { return LocalDateTime.parse(s, fmt); } catch (DateTimeParseException ignored) {}
        }
        // then date-only → atStartOfDay
        for (DateTimeFormatter fmt : DATE_ONLY_PATTERNS) {
            try { return LocalDate.parse(s, fmt).atStartOfDay(); } catch (DateTimeParseException ignored) {}
        }
        // last resort: fail clearly
        throw new IllegalArgumentException("Unrecognized date/time format: \"" + raw + "\"");
    }

    // Pretty print: date only (e.g., Oct 15 2019)
    public static String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM dd uuuu"));
    }

    // Pretty print: date+time when time is present (e.g., Oct 15 2019, 6:00 PM)
    public static String formatPretty(LocalDateTime dt) {
        if (dt.toLocalTime().equals(LocalTime.MIDNIGHT)) {
            return dt.format(DateTimeFormatter.ofPattern("MMM dd uuuu"));
        }
        return dt.format(DateTimeFormatter.ofPattern("MMM dd uuuu, h:mm a"));
    }
}
