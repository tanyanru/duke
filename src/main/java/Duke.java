import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/

        Scanner sc = new Scanner(System.in);
        Border lines = new Border();
        System.out.println(lines.createLine() + "\n");
        String input = "Hello! I'm Duke\nWhat can I do for you?";
        while (!input.equals("bye")){
            System.out.println(String.format("%45s",input));
            System.out.println(lines.createLine() + "\n");
            input = sc.nextLine();
            System.out.println(lines.createLine() + "\n");
        }
        System.out.println(String.format("%50s","Bye. Hope to see you again soon!"));
    }
}

