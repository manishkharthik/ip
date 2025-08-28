package salah;

public class ToDos extends Task {
    public ToDos(String description) {
        super(description);
    }

    // converts input from user to be displayed
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

    // converts object to string for saving
    @Override
    public String serialize() {
        return "T | " + (getIsComplete() ? "1" : "0") + " | " + getDescription();
    }

    // converts string from file to object
    public static ToDos fromData(String[] parts) {
        boolean done = parts[1].trim().equals("1");
        String desc = parts[2].trim();
        ToDos todo = new ToDos(desc);
        if (done) todo.markAsComplete();
        return todo;
    }
}