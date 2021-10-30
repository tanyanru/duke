package task;

import exception.DukeException;
import filewriter.Storage;
import task.Task;

import java.util.ArrayList;

/**
 * Object containing data structure to store Tasks.
 */
public class TaskList
{
    ArrayList <Task> taskList = new ArrayList<> ();
    public int taskNum;
    public boolean isFirst;

    /**
     * Constructor for Task List.
     * Calls when generating Task List from txt file.
     * @param storage Contains ArrayList of Task specified from txt file.
     */
    public TaskList(Storage storage) {
        try {
            this.taskList = storage.getSchedule();
            taskNum = taskList.size();
            if (taskNum == 0){
                isFirst = true;
            } else {
                isFirst = false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Constructor for Task List.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
        taskNum = taskList.size();
        if (taskNum == 0) {
            isFirst = true;
        } else {
            isFirst = false;
        }
    }


    /**
     * Constructor for Task List.
     * @param schedule ArrayList of tasks.
     */
    public TaskList(ArrayList<Task> schedule) {
        this.taskList = schedule;
        taskNum = taskList.size();
        if (taskNum == 0) {
            isFirst = true;
        } else {
            isFirst = false;
        }
    }

    /**
     * Gets Task corresponding to specified index.
     * @param index Indicates which task in the task list to return.
     * @return Task as specified by index.
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Marks a Task in task list as done.
     * @param index Specifies which task to mark as done.
     * @return completed Task.
     * @throws NullPointerException
     * @throws IndexOutOfBoundsException
     * @throws NumberFormatException
     * @throws DukeException
     */
    public Task complete(int index) throws NullPointerException, IndexOutOfBoundsException, NumberFormatException, DukeException {
        Task completedTask = taskList.get(index);
        completedTask.markAsDone();
        return completedTask;
    }

    /**
     * Adds new Task to TaskList.
     * @param task
     * @throws DukeException
     */
    public void addTask(Task task) throws DukeException {
        taskList.add(task);
        taskNum++;
    }

    /**
     * Deletes Task from TaskList.
     * @param index
     * @return deleted Task.
     * @throws NullPointerException
     * @throws IndexOutOfBoundsException
     * @throws NumberFormatException
     * @throws DukeException
     */
    public Task remove(int index) throws NullPointerException, IndexOutOfBoundsException, NumberFormatException, DukeException {
        Task removeTask = taskList.get(index);
        taskList.remove(index);
        taskNum--;
        return removeTask;
    }

    /**
     * Retrieves ArrayList<Task> schedule from TaskList.
     * Calls when execute method of FindCommand is called.
     * @return ArrayList<Task>
     */
    public ArrayList<Task> getList() {
        return taskList;
    }

    /**
     * toString method of TaskList.
     * @return contents of TaskList. i.e. tasks.
     */
    public String toString() {
        String output = "";
        for (int idx = 0; idx < taskNum; idx ++) {
            Task task = taskList.get(idx);
            output += ((idx + 1) + "." + task.toString() + "\n");
        }
        return output.substring(0, output.length() - 1);
    }
}


