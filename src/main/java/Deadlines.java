public class Deadlines extends Task {
    private final String by;

    public Deadlines(String description, String by) {
        super(description);
        this.by = by;
    }

    // converts input from user to be displayed
    public static Deadlines parser(String input) throws EmptyDescriptionException, TaskFormattingException{
        if (!input.contains("/by")) {
            throw new TaskFormattingException("Invalid deadline format. Make sure to include /by");
        }
        String[] parts = input.substring(8).split("/by", 2);
        String description = parts[0].trim();
        String by = parts[1].trim();
        if (description.isEmpty()) {
            throw new EmptyDescriptionException("Description cannot be empty.");
        }
        if (by.isEmpty()) {
            throw new EmptyDescriptionException("Deadline cannot be empty.");
        }
        return new Deadlines(description, by);
    }

    @Override
    public String toString() {
        return "[D]" + (this.getIsComplete() ? "[X] " : "[ ] ") + super.toString() + " (by: " + this.by + ")";
    }

    // converts object to string for saving
    @Override
    public String serialize() {
        return "D | " + (getIsComplete() ? "1" : "0") + " | " + getDescription() + " | " + by;
    }

    // converts string from file to object
    public static Deadlines fromData(String[] parts) {
        boolean done = parts[1].trim().equals("1");
        String desc = parts[2].trim();
        String by = parts[3].trim();
        Deadlines deadline = new Deadlines(desc, by);
        if (done) deadline.markAsComplete();
        return deadline;
    }
}