public class Deadline extends Task
{
    DateTime dateTime;
    Border lines = new Border();

        public Deadline(String description) throws DukeException
        {
            super(description);
            int divider = description.indexOf("/by");
            if (divider == -1 || (divider == description.length() -3))
            {
                throw new DukeException((lines.createLine()) + "\n Incorrect deadline format. \n Please key in deadline (task) /by d/mm/yyyy HHmm \n " + (lines.createLine()));
            }
            dateTime = DateTime.setDeadline(description.substring(divider + 4, description.length()));
            super.taskDesc = super.taskDesc.substring(0,divider);
        }

    @Override
    public String toString()
    {
        String output = "[D][" + super.getStatus() + "]" + " " + super.taskDesc + "(by: " + dateTime.toString() + ")";
        return output;
    }
}
