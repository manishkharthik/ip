package salah;

public abstract class Task {
    private final String description;
    private boolean isComplete;

    public boolean getIsComplete() {
        return this.isComplete;
    }

    public void markAsComplete() {
        this.isComplete = true;
    }

    public void markAsIncomplete() {
        this.isComplete = false;
    }

    public Task(String description) {
        this.description = description;
        this.isComplete = false;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return this.description;
    }

    // converts object to string for saving
    public abstract String serialize();

    // converts string from file to object by calling functions in subclasses
    public static Task deserialize(String line) {
        String[] parts = line.split("\\|");
        for (int i = 0; i < parts.length; i++) parts[i] = parts[i].trim();
        switch (parts[0]) {
            case "T": return ToDos.fromData(parts);
            case "D": return Deadlines.fromData(parts);
            case "E": return Events.fromData(parts);
            default: return null;
        }
    }
}