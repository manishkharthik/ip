package salah;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;
    private static final DateTimeFormatter PRETTY_DATE = DateTimeFormatter.ofPattern("MMM dd uuuu");

    public Events(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public LocalDateTime getFrom() { 
        return this.from; 
    }

    public LocalDateTime getTo() { 
        return this.to; 
    }

    // converts input from user to be displayed
    public static Events parser(String input) throws EmptyDescriptionException, TaskFormattingException{
        if (!input.contains("/from") || !input.contains("/to")) {
            throw new TaskFormattingException("Invalid event format. Make sure to include /from and /to");
        }
        String[] parts = input.substring(6).split("/from|/to");
        String description = parts[0].trim();
        String from = parts[1].trim();
        String to = parts[2].trim();

        if (description.isEmpty()) {
            throw new EmptyDescriptionException("Description cannot be empty.");
        }
        if (from.isEmpty()) {
            throw new EmptyDescriptionException("Start time cannot be empty.");
        }
        if (to.isEmpty()) {
            throw new EmptyDescriptionException("End time cannot be empty.");
        }

        LocalDateTime fromDate = DateTimeParser.parseFlexible(from);
        LocalDateTime toDate = DateTimeParser.parseFlexible(to);

        return new Events(description, fromDate, toDate);
    }

    @Override
    public String toString() {
        return "[E]" + (this.getIsComplete() ? "[X] " : "[ ] ")
                + super.toString()
                + " (from: " + DateTimeParser.formatPretty(this.from)
                + " to: " + DateTimeParser.formatPretty(this.to) + ")";
    }

    // converts object to string for saving
    @Override
    public String serialize() {
        return "E | " + (getIsComplete() ? "1" : "0") + " | " + getDescription() + " | " + from + " | " + to;
    }

    // converts string from file to object
    public static Events fromData(String[] parts) {
        boolean done = parts[1].trim().equals("1");
        String desc = parts[2].trim();
        LocalDateTime from = LocalDateTime.parse(parts[3].trim());
        LocalDateTime to   = LocalDateTime.parse(parts[4].trim());
        Events e = new Events(desc, from, to);
        if (done) e.markAsComplete();
        return e;
    }
}