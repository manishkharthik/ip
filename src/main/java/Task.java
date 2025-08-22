public class Task {
    private final String description;
    private boolean isComplete;

    public String getDescription() {
        return this.description;
    }

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
}
