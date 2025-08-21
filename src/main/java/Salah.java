import java.util.Scanner;

public class Salah {
    public static void main(String[] args) {
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
        
        System.out.println("Hi, I'm Mohammed Salah, The Egyptian King\n" + salah);
        System.out.println("How may I assist you today?");
        Scanner scan = new Scanner(System.in);
        while (true) { 
            String input = scan.nextLine();
            if (input.equals("bye")) {
                System.out.println("Hope you have a great day! Remember, you'll never walk alone :))");
                break;
            } else {
                System.out.println("-------------------------------------------------");
                System.out.println(input);
                System.out.println("-------------------------------------------------");
            }
        }
        scan.close();
    }
}
