import java.util.ArrayList;

public class Salah {
    public static void main(String[] args) {
        ArrayList<Task> userTasks = Storage.load();

        String salah =
            """
                           /^\\
                          /___\\
                         ( o o )
                         /  ^  \\
                  ____ _/|  |  |\\_ ____
                       / |  |  | \\
                      /  |  |  |  \\
                     /___|  |  |___\\
                          \\  |  /
                           \\ | /
                        ____\\|/____
                       /____/ \\____\\
                           /_____\\
            """;

        Ui ui = new Ui();
        ui.showGreeting(salah);

        try {
            while (true) {
                String input = ui.readCommand();

                if (input.equals(CommandType.BYE.keyword())) {
                    ui.showBye();
                    break;

                } else if (input.startsWith(CommandType.DELETE.keyword())) {
                    int index = Integer.parseInt(input.substring(7)) - 1;
                    try {
                        Task t = userTasks.get(index);
                        userTasks.remove(index);
                        Storage.save(userTasks);
                        ui.showDeleted(t, userTasks.size());
                    } catch (IndexOutOfBoundsException e) {
                        ui.showError("the input number has exceeded the number of tasks.");
                    }

                } else if (input.equals(CommandType.LIST.keyword())) {
                    ui.showList(userTasks);

                } else if (input.startsWith(CommandType.MARK.keyword())) {
                    int index = Integer.parseInt(input.substring(5)) - 1;
                    try {
                        Task t = userTasks.get(index);
                        t.markAsComplete();
                        Storage.save(userTasks);
                        ui.showMarked(t);
                    } catch (IndexOutOfBoundsException e) {
                        ui.showError("the input number has exceeded the number of tasks.");
                    }

                } else if (input.startsWith(CommandType.UNMARK.keyword())) {
                    int index = Integer.parseInt(input.substring(7)) - 1;
                    try {
                        Task t = userTasks.get(index);
                        t.markAsIncomplete();
                        Storage.save(userTasks);
                        ui.showUnmarked(t);
                    } catch (IndexOutOfBoundsException e) {
                        ui.showError("the input number has exceeded the number of tasks.");
                    }

                } else if (input.startsWith(CommandType.DEADLINE.keyword())) {
                    try {
                        Deadlines d = Deadlines.parser(input);
                        userTasks.add(d);
                        Storage.save(userTasks);
                        ui.showAdded(d, userTasks.size());
                    } catch (EmptyDescriptionException | TaskFormattingException e) {
                        ui.showError(e.getMessage());
                    }

                } else if (input.startsWith(CommandType.EVENT.keyword())) {
                    try {
                        Events ev = Events.parser(input);
                        userTasks.add(ev);
                        Storage.save(userTasks);
                        ui.showAdded(ev, userTasks.size());
                    } catch (EmptyDescriptionException | TaskFormattingException e) {
                        ui.showError(e.getMessage());
                    }

                } else { // ToDo
                    try {
                        ToDos todo = ToDos.parser(input);
                        userTasks.add(todo);
                        Storage.save(userTasks);
                        ui.showAdded(todo, userTasks.size());
                    } catch (EmptyDescriptionException e) {
                        ui.showError(e.getMessage());
                    }
                }
            }
        } finally {
            ui.close();
        }
    }
}
