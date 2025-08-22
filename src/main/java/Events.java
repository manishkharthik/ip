public class Events extends Task {
    private final String from;
    private final String to;

    public Events(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public static Events parser(String input) {
        String[] parts = input.substring(6).split("/from|/to");
        String description = parts[0].trim();
        String from = parts[1].trim();
        String to = parts[2].trim();
        return new Events(description, from, to);
    }


    @Override
    public String toString() {
        return "[E]" + (this.getIsComplete() ? "[X] " : "[ ] ") + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
