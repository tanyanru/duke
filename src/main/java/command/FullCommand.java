package command;

import exception.DukeException;

public enum FullCommand {
    LIST("list"),
    TODO ("todo"),
    EVENT ("event"),
    DEADLINE ("deadline"),
    DONE ("done"),
    DELETE ("delete"),
    BYE("bye"),
    FIND("find");

    private String activityName;

    FullCommand (String activityName) {
        this.activityName = activityName;
    }

    public String getActivityName() {
        return activityName;
    }

    public static FullCommand getByAction (String keyword) throws DukeException
    {
        for (FullCommand activity : values()) {
            if (activity.getActivityName().equals(keyword)) {
                return activity;
            }
        }
        throw new DukeException("\nCommand does not exist.");
    }
}
