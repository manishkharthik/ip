/**
 * Entry point of the Salah chatbot.
 * Manages the main loop: reads user input, interprets commands,
 * and uses supporting classes (Ui, Storage, Task, etc.) to
 * perform actions and display responses.
 */

package salah;

import java.util.ArrayList;
import java.util.List;

/**
 * Main application class.
 */
public class Salah {
    /**
     * Application entry point.
     *
     * @param args command-line arguments (not used)
     */
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

                if (input.equals(CommandType.BYE.getKeyword())) {
                    ui.showBye();
                    break;

                } else if (input.startsWith(CommandType.DELETE.getKeyword())) {
                    int index = Integer.parseInt(input.substring(7)) - 1;
                    try {
                        Task t = userTasks.get(index);
                        userTasks.remove(index);
                        Storage.save(userTasks);
                        ui.showDeleted(t, userTasks.size());
                    } catch (IndexOutOfBoundsException e) {
                        ui.showError("the input number has exceeded the number of tasks.");
                    }

                } else if (input.equals(CommandType.LIST.getKeyword())) {
                    ui.showList(userTasks);

                } else if (input.startsWith(CommandType.MARK.getKeyword())) {
                    int index = Integer.parseInt(input.substring(5)) - 1;
                    try {
                        Task t = userTasks.get(index);
                        t.markAsComplete();
                        Storage.save(userTasks);
                        ui.showMarked(t);
                    } catch (IndexOutOfBoundsException e) {
                        ui.showError("the input number has exceeded the number of tasks.");
                    }

                } else if (input.startsWith(CommandType.UNMARK.getKeyword())) {
                    int index = Integer.parseInt(input.substring(7)) - 1;
                    try {
                        Task t = userTasks.get(index);
                        t.markAsIncomplete();
                        Storage.save(userTasks);
                        ui.showUnmarked(t);
                    } catch (IndexOutOfBoundsException e) {
                        ui.showError("the input number has exceeded the number of tasks.");
                    }

                } else if (input.startsWith(CommandType.DEADLINE.getKeyword())) {
                    try {
                        Deadlines d = Deadlines.parser(input);
                        userTasks.add(d);
                        Storage.save(userTasks);
                        ui.showAdded(d, userTasks.size());
                    } catch (EmptyDescriptionException | TaskFormattingException e) {
                        ui.showError(e.getMessage());
                    }

                } else if (input.startsWith(CommandType.EVENT.getKeyword())) {
                    try {
                        Events ev = Events.parser(input);
                        userTasks.add(ev);
                        Storage.save(userTasks);
                        ui.showAdded(ev, userTasks.size());
                    } catch (EmptyDescriptionException | TaskFormattingException e) {
                        ui.showError(e.getMessage());
                    }

                } else if (input.startsWith(CommandType.FIND.getKeyword())) {
                    String[] parts = input.split("\\s+", 2);
                    if (parts.length < 2 || parts[1].trim().isEmpty()) {
                        ui.showError("Please provide a keyword to search for.");
                    } else {
                        String keyword = parts[1].trim().toLowerCase();
                        List<Task> matches = new ArrayList<>();
                        for (Task t : userTasks) {
                            if (t.getDescription().toLowerCase().contains(keyword)) {
                                matches.add(t);
                            }
                        }
                        ui.showFindResults(keyword, matches);
                    }

                } else {
                    // Treat everything else as a ToDo
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

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        return "Salah heard: " + input;
    }
}