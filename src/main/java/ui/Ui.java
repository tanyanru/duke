package ui;

import task.Task;
import task.TaskList;

import java.util.Scanner;

public class Ui {
    Scanner sc = new Scanner(System.in);
    public  void showWelcome() {
        String logo = "____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        showLine();
        System.out.println("Hello! I'm \n" + logo + "What can I do for you?");
        showLine();
    }

    public void showGoodbye(){
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }

    public void showLine(){
        String output = "";
        for (int n = 0; n < 80; n++) {
            output += "_";
        }
        System.out.println(output);
    }

    public void readTask(Task newTask, int taskNum){
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask.toString());
        System.out.println("Now you have " + taskNum + " tasks in the list.");
        showLine();
    }

    public void readDelete(Task removeTask, int taskNum){
        System.out.println("Noted. I've removed this task:");
        System.out.println(removeTask.toString());
        System.out.println("Now you have " + taskNum + " tasks in the list.");
        showLine();
    }

    public void readDone(Task completed_Task){
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(completed_Task.toString());
        showLine();
    }

    public void readList(TaskList tasks){
        System.out.println("Here are the tasks in your list: ");
        System.out.println(tasks);
        showLine();
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showError(String error_msg) {
        System.out.println("Your error message: " + error_msg);
        showLine();
    }
    public void showLoadingError(){
        System.out.println("Loading Error!");
        showLine();
    }
}