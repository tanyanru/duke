import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        TaskList taskList = new TaskList();
        Scanner sc = new Scanner(System.in);
        Border lines = new Border();
        System.out.println(lines.createLine() + "\n" + "Hello! I'm Duke\nWhat can I do for you?" + "\n" + lines.createLine());
        String input = sc.nextLine();
        while (!input.equals("bye"))
            /*if (input.equals("list"))*/
            {
               /* System.out.println(lines.createLine());
                System.out.println(taskList.showTaskList());*/
                taskList.addTask(input);
                input = sc.nextLine();
            }
           /* taskList.addTask(input);
            input = sc.nextLine();*/

        System.out.println(lines.createLine());
        System.out.println(String.format("%50s","Bye. Hope to see you again soon!") + "\n" + lines.createLine());
    }
}

