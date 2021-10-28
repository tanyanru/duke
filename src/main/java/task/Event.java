package task;

import datetime.DateTime;
import exception.DukeException;

public class Event extends Task
{
        DateTime dateTime;
        public Event(String description) throws DukeException {
                super(description);
                int divider = description.indexOf("/at");
                if (divider == -1 || (divider == description.length() - 3))
                {
                        throw new DukeException("\n Incorrect event format. \n Please key in event (details) /at d/mm/yyyy (start time)HHmm-(end time)HHmm \n");
                }
                dateTime = DateTime.setEventTime(description.substring(divider + 4, description.length()));
                super.taskDesc = super.taskDesc.substring(0,divider);
        }

        @Override
        public String toString() {
                String output = "[E][" + super.getStatus() + "]" + " " + super.taskDesc + "(at: " + dateTime.toString() + ")";
                return output;
        }
}
