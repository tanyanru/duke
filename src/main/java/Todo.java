public class Todo extends Task
{
    public Todo(String description)
    {
        super(description);
    }

    @Override
    public String toString()
    {
        String output = "[T][" + super.getStatus()+ "]" + " " + super.taskDesc;
        return output;
    }
}