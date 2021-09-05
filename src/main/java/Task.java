public class Task {
    protected String taskDesc;
    protected boolean isDone;

    public Task(String taskDesc)
    {
        this.taskDesc = taskDesc;
        this.isDone = false;
    }

    public String getStatus()
    {
        //tick if done, else cross
        return (isDone ? "\u2713" : "\u2718");
    }

    public void markAsDone()
    {
        this.isDone = true;
    }

    public String toString()
    {
        String results = "[" + this.getStatus() + "]" + " " + this.taskDesc;
        return results;
    }
}
