public class TaskList
{
    //Assume there will be no more than 100 tasks
    Task[] taskList = new Task[100];
    int taskNum = 0;
    Border lines = new Border();

    public void addTask(String task)
    {
        String[] arr = task.split(" ", 2);
        switch (arr[0])
        {
            case "list":
                System.out.println(showTaskList());
                break;

            case "done":
                try
                {
                    try
                    {
                        if (arr.length < 2)
                            throw new DukeException((lines.createLine()) + "\n Which tasks have u completed? \n" + (lines.createLine()));
                        taskList[Integer.parseInt(arr[1]) - 1].markAsDone();
                        System.out.println(lines.createLine());
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(taskList[Integer.parseInt(arr[1]) - 1].toString());
                        System.out.println(lines.createLine());
                    } catch (NullPointerException | IndexOutOfBoundsException e)
                    {
                        throw new DukeException((lines.createLine()) + "\n Which tasks have u completed? \n" + (lines.createLine()));
                    }
                } catch (DukeException e)
                {
                    System.out.println(e.getMessage());
                } finally
                {
                    break;
                }

            default:
                try {
                    Task newTask = track(arr);
                    taskList[taskNum++] = newTask;
                    System.out.println(lines.createLine());
                    System.out.println("Got it. I've added this task: " + "\n" + newTask.toString());
                    System.out.println("Now you have " + taskNum + " task(s) in the list." + "\n" + lines.createLine());
                    break;
                    }
                catch (DukeException e)
                {
                    System.out.println(e.getMessage());
                }
        }
    }

    private Task track(String[] arr) throws DukeException
    {
        try
        {
            if (arr[0].equals("todo"))
            {
                checkDescription(arr, "todo");
                return new Todo(arr[1]);
            } else if (arr[0].equals("deadline"))
            {
                checkDescription(arr, "deadline");
                return new Deadline(arr[1]);
            } else if (arr[0].equals("event"))
            {
                checkDescription(arr, "event");
                return new Event(arr[1]);
            } else
            {
                throw new DukeException((lines.createLine()) + "\nI'm sorry, but I don't know what that means.\n" + (lines.createLine()), new IllegalArgumentException());
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

    public String showTaskList()
    {
        String output = lines.createLine() + "\n" + "Here are the tasks in your list:" + "\n";
        for (int idx = 0; idx <= taskNum; idx ++)
        {
            Task task = taskList[idx];
            if (task==null)
            {
                break;
            }
            else
            {
                output += ((idx + 1) + "." + task.toString() + "\n");
            }
        }
        return output + lines.createLine();
    }

    public String toString()
    {
        String output = lines.createLine() + "\n" + "Here are the tasks in your list: \n";
        for (int index = 0; index <= taskNum; index ++)
        {
            Task task = taskList[index];
            if (task == null)
            {
                break;
            } else
            {
                output += ((index + 1) + "." + task.toString() + "\n");
            }
        }
        return output + lines.createLine() + "\n";
    }
}

