public class Deadlines extends Task {
    private final String by;

    public Deadlines(String description, String by) {
        super(description);
        this.by = by;
    }

    public static Deadlines parser(String input) {
        String[] parts = input.substring(8).split("/by", 2);
        String description = parts[0].trim();
        String by = parts[1].trim();
        return new Deadlines(description, by);
    }

    @Override
    public String toString() {
        return "[D]" + (this.getIsComplete() ? "[X] " : "[ ] ") + super.toString() + " (by: " + this.by + ")";
    }
}
