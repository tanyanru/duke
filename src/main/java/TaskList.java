public class TaskList {
    //Assume there will be no more than 100 tasks
    String [] taskList = new String[100];
    int taskNum = 0;
    Border lines = new Border();
    public void addTask(String task)
    {
        if (!task.equals("list"))
        {
            taskList[taskNum++] = task;
            System.out.println(lines.createLine());
            System.out.println(String.format("%45s","added: " + task) + "\n" + lines.createLine());
        }
    }

    public String showTaskList()
    {
        String output = "";
        for (int idx = 0; idx <= taskNum; idx ++)
        {
            String task = taskList[idx];
            if (task==null)
            {
                break;
            }
            else
            {
                output += String.format("%45s",((idx+1) + ". " + taskList[idx]) + "\n");
            }
        }
        System.out.println(String.format("%44s","Your Task List"));
        return output + lines.createLine();
    }
}
