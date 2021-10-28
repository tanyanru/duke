package task;

import datetime.DateTime;
import exception.DukeException;

public class Deadline extends Task {
    DateTime dateTime;
        public Deadline(String description) throws DukeException {
            super(description);
            int divider = description.indexOf("/by");
            if (divider == -1 || (divider == description.length() -3))
            {
                throw new DukeException("\nIncorrect deadline format. \nPlease key in deadline (task) /by d/mm/yyyy HHmm");
            }
            dateTime = DateTime.setDeadline(description.substring(divider + 4, description.length()));
            super.taskDesc = super.taskDesc.substring(0,divider);
        }

    public Deadline(String description, DateTime dateTime){
        super(description);
        System.out.println(dateTime);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        String output = "[D][" + super.getStatus() + "]" + " " + super.taskDesc + "(by: " + dateTime.toString() + ")";
        return output;
    }
}
