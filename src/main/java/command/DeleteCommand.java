package command;

import exception.DukeException;
import filewriter.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

public class DeleteCommand extends Command {
    int index;

    public DeleteCommand(int index){
        this.index = index;
        super.type = FullCommand.DELETE;
    }

    public boolean isExit(){
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task removeTask = tasks.remove(index);
            ui.readDelete(removeTask, tasks.taskNum);
            storage.editFile(tasks);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new DukeException("Index out of bounds.");
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter the task number that you would like to delete.");
        }
    }
}