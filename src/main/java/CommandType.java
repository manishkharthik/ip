public enum CommandType {
    BYE("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    DEADLINE("deadline"),
    EVENT("event"),
    TODO("todo");

    private final String keyword;

    CommandType(String keyword) {
        this.keyword = keyword;
    }

    public String keyword() {
        return keyword;
    }
}
