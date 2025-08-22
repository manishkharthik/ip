public class Events extends Task {
    private final String from;
    private final String to;

    public Events(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

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
        return new Events(description, from, to);
    }


    @Override
    public String toString() {
        return "[E]" + (this.getIsComplete() ? "[X] " : "[ ] ") + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}