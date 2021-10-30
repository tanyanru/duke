package command;

import exception.DukeException;
import filewriter.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Displays the full list of recorded tasks to user.
 */
public class ListCommand extends Command {
    int index;

    /**
     * Constructor of ListCommand.
     */
    public ListCommand() {
        super.type = FullCommand.LIST;
    }

    /**
     * Checks if command is an ExitCommand.
     * @return ConstructorCommand is not ExitCommand.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Passes TaskList as an argument for ui.readList to display to user.
     * @param tasks Current TaskList object used in this instance of Duke.
     * @param ui Instance of user interface to print feedback to user.
     * @param storage Updates data record of TaskList in storage.filepath if needed.
     * @throws DukeException
     */
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