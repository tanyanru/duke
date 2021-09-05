public class TaskList {
    //Assume there will be no more than 100 tasks
    Task [] taskList = new Task[100];
    int taskNum = 0;
    Border lines = new Border();
    public void addTask(String task)
    {
        /*if (!task.equals("list"))
        {
            taskList[taskNum++] = task;
            System.out.println(lines.createLine());
            System.out.println(String.format("%45s","added: " + task) + "\n" + lines.createLine());
        }*/
        String [] arr = task.split(" ");
        switch(arr[0])
        {
            case "list":
                System.out.println(showTaskList());
                break;

            case "done":
                taskList[Integer.parseInt(arr[1]) - 1].markAsDone();
                System.out.println(lines.createLine());
                System.out.println(String.format("%45s","Nice! I've marked this task as done:"));
                System.out.println(String.format("%45s",taskList[Integer.parseInt(arr[1]) - 1].toString()));
                System.out.println(lines.createLine());
                break;

            default:
                taskList[taskNum++] = new Task(task);
                System.out.println(lines.createLine());
                System.out.println(String.format("%45s","added: " + task) + "\n" + lines.createLine());
                break;
        }
    }

    public String showTaskList()
    {
        //String output = "";
        String output = lines.createLine() + "\n" + String.format("%45s","Here are the tasks in your list:" + "\n");
        for (int idx = 0; idx <= taskNum; idx ++)
        {
            Task task = taskList[idx];
            if (task==null)
            {
                break;
            }
            else
            {
                //output += String.format("%45s",((idx+1) + ". " + taskList[idx]) + "\n");
                output += String.format("%45s",((idx+1) + ". " + task.toString()) + "\n");
            }
        }
        //System.out.println(String.format("%44s","Your Task List"));
        return output + lines.createLine();
    }
}
