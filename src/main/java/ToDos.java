public class ToDos extends Task {
    public ToDos(String description) {
        super(description);
    }

    public static ToDos parser(String input) {
        String description = input.startsWith("todo")
                    ? input.substring(5).trim()
                    : input;
        return new ToDos(description);
    }

    @Override
    public String toString() {
        return "[T]" + (this.getIsComplete() ? "[X] " : "[ ] ") + super.toString();
    }
}
