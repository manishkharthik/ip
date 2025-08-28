import java.util.ArrayList;
import java.util.Scanner;

public class Salah {
    public static void main(String[] args) {
        // initialise an ArrayList to hold user tasks
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
        """ ;

        // Greeting message
        System.out.println("Hi, I'm Mohammed Salah, The Egyptian King\n" + salah);
        System.out.println("How may I assist you today?");

        // Create a Scanner object for user input
        Scanner scan = new Scanner(System.in);
        while (true) { 
            String input = scan.nextLine();

            // Exit message if input is "bye"
            if (input.equals(CommandType.BYE.keyword())) {
                System.out.println("------------------------------------------------------------------------");
                System.out.println("Hope you have a great day! Remember, you'll never walk alone :))");
                System.out.println("------------------------------------------------------------------------");
                break;
            
            // Delete task
            } else if (input.startsWith(CommandType.DELETE.keyword())) {
                int index = Integer.parseInt(input.substring(7)) - 1;
                // Check if index is valid, if not throw out of bounds exception
                try {
                    Task currentTask = userTasks.get(index);
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("The Egyptian king has removed this task:");
                    System.out.println(currentTask.toString());
                    userTasks.remove(index);
                    System.out.println("There are still " + userTasks.size() + " tasks in your list!");
                    Storage.save(userTasks);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("Error: the input number has exceeded the number of tasks.");
                }
                System.out.println("------------------------------------------------------------------------");
            
            // List all tasks if input is "list"
            } else if (input.equals(CommandType.LIST.keyword())) {
                System.out.println("------------------------------------------------------------------------");
                System.out.println("The Egyptian King has these tasks for you to complete:");
                // Include task number, description and completion status in output
                int num = 1;
                for (Task task : userTasks) {
                    if (task.getIsComplete()) {
                        System.out.println(num + ". " + task.toString());
                    } else {
                        System.out.println(num + ". " + task.toString());
                    }
                    num++;
                }
                System.out.println("------------------------------------------------------------------------");

            // Mark a task as complete, taking the task number as input
            } else if (input.startsWith(CommandType.MARK.keyword())) {
                int index = Integer.parseInt(input.substring(5)) - 1;
                // Check if index is valid, if not throw out of bounds exception
                try {
                    Task currentTask = userTasks.get(index);
                    currentTask.markAsComplete();
                    Storage.save(userTasks);
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("This task is marked as complete. Well done!");
                    System.out.println(currentTask.toString());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("Error: the input number has exceeded the number of tasks.");
                }
                System.out.println("------------------------------------------------------------------------");

            // Mark a task as incomplete, taking the task number as input
            } else if (input.startsWith(CommandType.UNMARK.keyword())) {
                int index = Integer.parseInt(input.substring(7)) - 1;
                // Check if index is valid, if not throw out of bounds exception
                try {
                    Task currentTask = userTasks.get(index);
                    Storage.save(userTasks);
                    currentTask.markAsIncomplete();
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("Task has been marked as incomplete.");
                    System.out.println(currentTask.toString());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("Error: the input number has exceeded the number of tasks.");
                }
                System.out.println("------------------------------------------------------------------------");

            // Create a Deadline task
            } else if (input.startsWith(CommandType.DEADLINE.keyword())) {
                // Call the parser method and pass into Deadlines constructor
                try {
                    Deadlines deadlineTask = Deadlines.parser(input);
                    userTasks.add(deadlineTask);
                    Storage.save(userTasks);
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("added: " + deadlineTask.toString());
                    System.out.println("The Egyptian King detects " + userTasks.size() + " tasks in your list!");
                } catch (EmptyDescriptionException | TaskFormattingException e) {
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("Error: " + e.getMessage());
                }
                System.out.println("------------------------------------------------------------------------");

            // Create an Events task
            } else if (input.startsWith(CommandType.EVENT.keyword())) {
                // Call the parser method and pass into Events constructor
                try {
                    Events eventTask = Events.parser(input);
                    userTasks.add(eventTask);
                    Storage.save(userTasks);
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("added: " + eventTask.toString());
                    System.out.println("The Egyptian King detects " + userTasks.size() + " tasks in your list!");
                } catch (EmptyDescriptionException | TaskFormattingException e) {
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("Error: " + e.getMessage());
                }
                System.out.println("------------------------------------------------------------------------");
            
            // For any other input, simply add the input as a new todo task 
            } else {
                // Call the parser method and pass into ToDos constructor
                try {
                    ToDos todoTask = ToDos.parser(input);
                    userTasks.add(todoTask);
                    Storage.save(userTasks);
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("added: " + todoTask.toString());
                    System.out.println("The Egyptian King detects " + userTasks.size() + " tasks in your list!");
                } catch (EmptyDescriptionException e) {
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("Error: " + e.getMessage());
                }
                System.out.println("------------------------------------------------------------------------");
            }
        }
        // Close the scanner
        scan.close();
    }
}