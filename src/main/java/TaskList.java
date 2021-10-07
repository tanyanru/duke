import java.util.ArrayList;

public class TaskList
{
    ArrayList <Task> taskList = new ArrayList<> ();

    int taskNum = 0;
    Border lines = new Border();

    public void addTask(String task) {
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
                                throw new DukeException((lines.createLine()) + "\n Which tasks have u completed? \n" + (lines.createLine()));
                            taskList.get(Integer.parseInt(arr[1]) - 1).markAsDone();
                            System.out.println(lines.createLine());
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println(taskList.get(Integer.parseInt(arr[1]) - 1).toString());
                            System.out.println(lines.createLine());
                        } catch (NullPointerException | IndexOutOfBoundsException e) {
                            throw new DukeException((lines.createLine()) + "\n Index out of bounds. \n" + (lines.createLine()));
                        } catch (NumberFormatException e) {
                            throw new DukeException((lines.createLine()) + "\n Please enter the task number that you have completed.  \n" + (lines.createLine()));
                        }
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    } finally {
                        break;
                    }
                case DELETE:
                    try {
                        try {
                            if (arr.length < 2)
                                throw new DukeException((lines.createLine()) + "\n Which tasks have u completed? \n" + (lines.createLine()));
                            Task removeTask = taskList.get(Integer.parseInt(arr[1]) - 1);
                            taskList.remove(removeTask);
                            taskNum--;
                            System.out.println(lines.createLine());
                            System.out.println("Noted. I've removed this task: \n" + removeTask.toString());
                            System.out.println("Now you have " + taskNum + " in the list.");
                            System.out.println(lines.createLine());
                        } catch (NullPointerException | IndexOutOfBoundsException e) {
                            throw new DukeException(lines.createLine() + "\n Index out of bounds. \n" + (lines.createLine()));
                        } catch (NumberFormatException e) {
                            throw new DukeException((lines.createLine()) + "\n Please enter the task number that you would like to delete.  \n" + (lines.createLine()));
                        }
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    } finally {
                        break;
                    }

                default:
                    try {
                        Task newTask = track(activity, arr);
                        taskList.add(newTask);
                        taskNum++;
                        System.out.println(lines.createLine());
                        System.out.println("Got it. I've added this task: " + "\n" + newTask.toString());
                        System.out.println("Now you have " + taskNum + " task(s) in the list." + "\n" + lines.createLine());
                        break;
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }  catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }


    private Task track(Activity activity, String[] arr) throws DukeException
    {
        try
        {
            switch (activity) {
                case TODO:
                    checkDescription(arr, "todo");
                    return new Todo(arr[1]);
                case DEADLINE:
                    checkDescription(arr, "deadline");
                    return new Deadline(arr[1]);
                default:
                    checkDescription(arr, "event");
                    return new Event(arr[1]);
            }
        }
        catch(DukeException e)
        {
            throw e;
        }
    }

    private void checkDescription (String[] arr, String taskType) throws DukeException
    {
        if (arr.length < 2)
        {
            throw new DukeException((lines.createLine()) + "\n The description of a " + taskType + " cannot be empty.\n" + (lines.createLine()));
        }
    }

    public String toString()
    {
        String output = lines.createLine() + "\n" + "Here are the tasks in your list:" + "\n";
        for (int idx = 0; idx < taskNum; idx ++)
        {
            Task task = taskList.get(idx);
            output += ((idx + 1) + "." + task.toString() + "\n");
        }
        return output + lines.createLine();
    }
}

