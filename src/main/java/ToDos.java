public class ToDos extends Task {
    public ToDos(String description) {
        super(description);
    }

    public static ToDos parser(String input) throws EmptyDescriptionException {
        String description;
        if (input.toLowerCase().startsWith("todo")) {
            // if input is exactly "todo" or "todo " then description is empty
            if (input.length() <= 4) {
                description = "";
            } else {
                description = input.substring(4).trim(); 
            }
        } else {
            description = input.trim();
        }
        if (description.isEmpty()) {
            throw new EmptyDescriptionException("Description cannot be empty.");
        }
        return new ToDos(description);
    }

    @Override
    public String toString() {
        return "[T]" + (this.getIsComplete() ? "[X] " : "[ ] ") + super.toString();
    }
}
