package command;

import exception.DukeException;
import filewriter.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Removes Task from Task List.
 */
public class DeleteCommand extends Command {
    int index;

    /**
     * Constructor for DeleteCommand.
     * @param index of the task to delete in the Task List.
     */
    public DeleteCommand(int index){
        this.index = index;
        super.type = FullCommand.DELETE;
    }

    /**
     * Checks if command is an ExitCommand.
     * @return false as command is a DeleteCommand.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Removes the current (index)th task from the task list.
     * @param tasks Current TaskList object used in this instance of Duke. Removes specified task from tasks.schedule.
     * @param ui Instance of user interface to print feedback to user.
     * @param storage Updates data record of TaskList in storage.filepath.
     * @throws DukeException Throws when index equals to 0, or when index is larger than number of Task(s) in TaskList.
     */
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