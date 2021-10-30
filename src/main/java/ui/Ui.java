package ui;

import task.Task;
import task.TaskList;

import java.util.Scanner;

/**
 * Determines what is displayed to user.
 */
public class Ui {
    Scanner sc = new Scanner(System.in);

    /**
     * Calls at the start of program.
     */
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

    /**
     * Calls when execute method of ExitCommand is called.
     */
    public void showGoodbye(){
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }

    /**
     * Prints border.
     */
    public void showLine(){
        String output = "";
        for (int n = 0; n < 80; n++) {
            output += "_";
        }
        System.out.println(output);
    }

    /**
     * Calls when a task is entered by user.
     * Calls when execute of AddTask is called.
     * @param newTask task entered.
     * @param taskNum number of tasks in Task List.
     */
    public void readTask(Task newTask, int taskNum){
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask.toString());
        System.out.println("Now you have " + taskNum + " tasks in the list.");
        showLine();
    }

    /**
     * Displays delete message.
     * Calls when execute of DeleteCommand is called.
     * @param removeTask
     * @param taskNum
     */
    public void readDelete(Task removeTask, int taskNum){
        System.out.println("Noted. I've removed this task:");
        System.out.println(removeTask.toString());
        System.out.println("Now you have " + taskNum + " tasks in the list.");
        showLine();
    }

    /**
     * Displays message when a user finishes a task.
     * Calls when execute of EditCommand is called.
     * @param completed_Task Task which has been marked as done.
     */
    public void readDone(Task completed_Task){
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(completed_Task.toString());
        showLine();
    }

    /**
     * Displays contents of Task List.
     * Calls when execute of ListCommand is called.
     * @param tasks TaskList
     */
    public void readList(TaskList tasks){
        System.out.println("Here are the tasks in your list: ");
        System.out.println(tasks);
        showLine();
    }

    /**
     * Reads user input.
     * @return String to be parsed by parser.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Displays message when DukeException is caught.
     * @param error_msg DukeException e.getMessage()
     */
    public void showError(String error_msg) {
        System.out.println("Your error message: " + error_msg);
        showLine();
    }

    /**
     * Calls when Duke Exception is thrown when instantiating Task List and/ or Storage Objects.
     * Calls by run method in Duke class.
     */
    public void showLoadingError(){
        System.out.println("Loading Error!");
        showLine();
    }

    /**
     * Displays matching Tasks.
     * Calls when execute method of FindCommand is called.
     * @param tasks TaskList of tasks with keyword.
     */
    public void showMatches(TaskList tasks) {
        System.out.println("Here are the matching task(s) in your list: ");
        System.out.println(tasks);
        showLine();
    }
}