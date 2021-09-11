import java.util.Scanner;

public class Duke {
    public static void main(String[] args)
    {
        TaskList taskList = new TaskList();
        Scanner sc = new Scanner(System.in);
        Border lines = new Border();
        System.out.println(lines.createLine() + "\n" + "Hello! I'm Duke\nWhat can I do for you?" + "\n" + lines.createLine());
        String input = sc.nextLine();
        while (!input.equals("bye"))
            {
                taskList.addTask(input);
                input = sc.nextLine();
            }

        System.out.println(lines.createLine());
        System.out.println("Bye. Hope to see you again soon!" + "\n" + lines.createLine());
    }
}

