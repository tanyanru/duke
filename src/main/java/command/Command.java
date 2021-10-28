package command;

import exception.DukeException;
import filewriter.Storage;
import task.*;
import ui.Ui;

public abstract class Command {
    FullCommand type;

    public boolean isExit(){
        return type.getActivityName().equals("bye");
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}