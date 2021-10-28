package task;

import exception.DukeException;
import task.Task;

public class Todo extends Task
{
    public Todo(String description) throws DukeException {
        super(description);
    }

    @Override
    public String toString() {
        String output = "[T][" + super.getStatus()+ "]" + " " + super.taskDesc;
        return output;
    }
}