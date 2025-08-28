import java.util.List;
import java.util.Scanner;

public class Ui {
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showGreeting(String asciiArt) {
        System.out.println("Hi, I'm Mohammed Salah, The Egyptian King");
        System.out.println(asciiArt);
        System.out.println("How may I assist you today?");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLine() {
        System.out.println("------------------------------------------------------------------------");
    }

    public void showError(String message) {
        showLine();
        System.out.println("Error: " + message);
        showLine();
    }

    public void showBye() {
        showLine();
        System.out.println("Hope you have a great day! Remember, you'll never walk alone :))");
        showLine();
    }

    public void showList(List<Task> tasks) {
        showLine();
        System.out.println("The Egyptian King has these tasks for you to complete:");
        int i = 1;
        for (Task t : tasks) {
            System.out.println(i + ". " + t.toString());
            i++;
        }
        showLine();
    }

    public void showAdded(Task task, int total) {
        showLine();
        System.out.println("added: " + task.toString());
        System.out.println("The Egyptian King detects " + total + " tasks in your list!");
        showLine();
    }

    public void showDeleted(Task task, int remaining) {
        showLine();
        System.out.println("The Egyptian king has removed this task:");
        System.out.println(task.toString());
        System.out.println("There are still " + remaining + " tasks in your list!");
        showLine();
    }

    public void showMarked(Task task) {
        showLine();
        System.out.println("This task is marked as complete. Well done!");
        System.out.println(task.toString());
        showLine();
    }

    public void showUnmarked(Task task) {
        showLine();
        System.out.println("Task has been marked as incomplete.");
        System.out.println(task.toString());
        showLine();
    }

    /** Close resources when app exits. */
    public void close() {
        scanner.close();
    }
}
