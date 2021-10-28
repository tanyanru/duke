package command;

import exception.DukeException;
import filewriter.Storage;
import task.TaskList;
import ui.Ui;

public class ListCommand extends Command {
    int index;

    public ListCommand() {
        super.type = FullCommand.LIST;
    }

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            if (index == -1) {
                throw new IndexOutOfBoundsException();
            }
            ui.readList(tasks);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new DukeException("Index out of bounds.");
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter the task number that you would like to delete.");
        }
    };
}