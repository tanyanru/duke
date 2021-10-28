package task;

import exception.DukeException;
import filewriter.Storage;
import task.Task;

import java.util.ArrayList;

public class TaskList
{
    ArrayList <Task> taskList = new ArrayList<> ();
    public int taskNum;
    public boolean isFirst;

    public TaskList(Storage storage) {
        try {
            this.taskList = storage.getSchedule();
            taskNum = taskList.size();
            if (taskNum == 0){
                isFirst = true;
            } else {
                isFirst = false;
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public TaskList() {
        this.taskList = new ArrayList<Task>();
        taskNum = taskList.size();
        if (taskNum == 0){
            isFirst = true;
        } else {
            isFirst = false;
        }
        System.out.println(taskNum);
    }


    public TaskList(ArrayList<Task> schedule) {
        this.taskList = schedule;
        taskNum = taskList.size();
        if (taskNum == 0) {
            isFirst = true;
        } else {
            isFirst = false;
        }
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public Task complete(int index)
            throws NullPointerException, IndexOutOfBoundsException, NumberFormatException, DukeException{
        Task completedTask = taskList.get(index);
        completedTask.markAsDone();
        return completedTask;
    }

    public void addTask(Task task) throws DukeException {
        taskList.add(task);
        taskNum++;
    }

    public Task remove(int index)
            throws NullPointerException, IndexOutOfBoundsException, NumberFormatException, DukeException{
        Task removeTask = taskList.get(index);
        taskList.remove(index);
        taskNum--;
        return removeTask;
    }

    public ArrayList<Task> getList() {
        return taskList;
    }

    public String toString() {
        String output = "";
        for (int idx = 0; idx < taskNum; idx ++)
        {
            Task task = taskList.get(idx);
            output += ((idx + 1) + "." + task.toString() + "\n");
        }
        return output.substring(0, output.length() - 1);
    }
}

    /*public void addTask(String task) {
        String[] arr = task.split(" ", 2);

        try {
            Activity activity = Activity.getByAction(arr[0]);
            switch (activity) {
                case LIST:
                    System.out.println(this);
                    break;

                case DONE:
                    try {
                        try {
                            if (arr.length < 2)
                                throw new exception.DukeException((lines.createLine()) + "\n Which tasks have u completed? \n" + (lines.createLine()));
                            taskList.get(Integer.parseInt(arr[1]) - 1).markAsDone();
                            FileWriterClass.writeFile(filePath,taskList.toString());
                            System.out.println(lines.createLine());
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println(taskList.get(Integer.parseInt(arr[1]) - 1).toString());
                            System.out.println(lines.createLine());
                        } catch (NullPointerException | IndexOutOfBoundsException e) {
                            throw new exception.DukeException((lines.createLine()) + "\n Index out of bounds. \n" + (lines.createLine()));
                        } catch (NumberFormatException e) {
                            throw new exception.DukeException((lines.createLine()) + "\n Please enter the task number that you have completed.  \n" + (lines.createLine()));
                        }
                    } catch (exception.DukeException e) {
                        System.out.println(e.getMessage());
                    } finally {
                        break;
                    }
                case DELETE:
                    try {
                        try {
                            if (arr.length < 2)
                                throw new exception.DukeException((lines.createLine()) + "\n Which tasks have u completed? \n" + (lines.createLine()));
                            task.Task removeTask = taskList.get(Integer.parseInt(arr[1]) - 1);
                            taskList.remove(removeTask);
                            FileWriterClass.writeFile(filePath, taskList.toString());
                            taskNum--;
                            System.out.println(lines.createLine());
                            System.out.println("Noted. I've removed this task: \n" + removeTask.toString());
                            System.out.println("Now you have " + taskNum + " in the list.");
                            System.out.println(lines.createLine());
                        } catch (NullPointerException | IndexOutOfBoundsException e) {
                            throw new exception.DukeException(lines.createLine() + "\n Index out of bounds. \n" + (lines.createLine()));
                        } catch (NumberFormatException e) {
                            throw new exception.DukeException((lines.createLine()) + "\n Please enter the task number that you would like to delete.  \n" + (lines.createLine()));
                        }
                    } catch (exception.DukeException e) {
                        System.out.println(e.getMessage());
                    } finally {
                        break;
                    }

                default:
                    try {
                        task.Task newTask = track(activity, arr);
                        taskList.add(newTask);
                        FileWriterClass.writeFile(filePath, taskList.toString());
                        taskNum++;
                        System.out.println(lines.createLine());
                        System.out.println("Got it. I've added this task: " + "\n" + newTask.toString());
                        System.out.println("Now you have " + taskNum + " task(s) in the list." + "\n" + lines.createLine());
                        break;
                    } catch (exception.DukeException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }  catch (exception.DukeException e) {
            System.out.println(e.getMessage());
        }
    }


    private task.Task track(Activity activity, String[] arr) throws exception.DukeException
    {
        try
        {
            switch (activity) {
                case TODO:
                    checkDescription(arr, "todo");
                    return new task.Todo(arr[1]);
                case DEADLINE:
                    checkDescription(arr, "deadline");
                    return new task.Deadline(arr[1]);
                default:
                    checkDescription(arr, "event");
                    return new task.Event(arr[1]);
            }
        }
        catch(exception.DukeException e)
        {
            throw e;
        }
    }

    private void checkDescription (String[] arr, String taskType) throws exception.DukeException
    {
        if (arr.length < 2)
        {
            throw new exception.DukeException((lines.createLine()) + "\n The description of a " + taskType + " cannot be empty.\n" + (lines.createLine()));
        }
    }*/


