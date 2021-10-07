public enum Activity {
    LIST("list"), TODO ("todo"), EVENT ("event"), DEADLINE ("deadline"), DONE ("done"), DELETE ("delete");

    private String activityName;

    Activity (String activityName)
    {
        this.activityName = activityName;
    }

    public String getActivityName()
    {
        return activityName;
    }

    public static Activity getByAction (String keyword) throws DukeException
    {
        for (Activity activity : values())
        {
            if (activity.getActivityName().equals(keyword))
            {
                return activity;
            }
        }
        Border lines = new Border();
        throw new DukeException((lines.createLine()) + "\nI'm sorry, but I don't know what that means.\n" + (lines.createLine()));
    }
}
