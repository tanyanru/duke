public class Event extends Task
{
        DateTime dateTime;
        Border lines = new Border();

        public Event(String description) throws DukeException
        {
                super(description);
                int divider = description.indexOf("/at");
                if (divider == -1 || (divider == description.length() - 3))
                {
                        throw new DukeException((lines.createLine()) + "\n Incorrect deadline format. \n Please key in deadline (task) /by d/mm/yyyy HHmm \n " + (lines.createLine()));
                }
                dateTime = DateTime.setEventTime(description.substring(divider + 4, description.length()));
                super.taskDesc = super.taskDesc.substring(0,divider);
        }

        @Override
        public String toString()
        {
                String output = "[E][" + super.getStatus() + "]" + " " + super.taskDesc + "(at: " + dateTime.toString() + ")";
                return output;
        }
}
