package datetime;

import java.time.format.DateTimeFormatter;
import java.time.LocalTime;
import java.time.LocalDateTime;
import exception.DukeException;
import java.time.format.DateTimeParseException;

public class DateTime {
    private LocalTime endDateTime;
    private LocalDateTime startDateTime;
    private String displayDateTime;
    private static DateTimeFormatter getTime = DateTimeFormatter.ofPattern("HHmm");
    private static DateTimeFormatter getStartDateTime = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");

    public static DateTime setDeadline(String dateTime) throws DukeException {
        try {
            DateTime deadlineDateTime = new DateTime();
            deadlineDateTime.startDateTime = LocalDateTime.parse(dateTime, getStartDateTime);
            deadlineDateTime.displayDateTime = deadlineDateTime.startDateTime.format(getStartDateTime);
            return deadlineDateTime;
        } catch (DateTimeParseException e) {
            throw new DukeException("Incorrect deadline datetime format. \nPlease key in deadline (task) /by d/mm/yyyy HHmm");
        }
    }

    public static DateTime readDeadLine(String dateTime){
        DateTime deadlineDateTime = new DateTime();
        deadlineDateTime.startDateTime = LocalDateTime.parse(dateTime, getStartDateTime);
        deadlineDateTime.displayDateTime = deadlineDateTime.startDateTime.format(getStartDateTime);
        return deadlineDateTime;
    }

    public static DateTime setEventTime (String dateTime) throws DukeException {
        try {
            DateTime eventDateTime = new DateTime();
            int divider = dateTime.indexOf("-");
            if (divider == -1 || (divider == dateTime.length() - 1)) {
                throw new DukeException("Please key in the end time.");
            }
            String end = dateTime.substring(divider + 1, dateTime.length());
            eventDateTime.startDateTime = LocalDateTime.parse(dateTime.substring(0, divider), getStartDateTime);
            eventDateTime.endDateTime = LocalTime.parse(end, DateTimeFormatter.ofPattern("HHmm"));
            eventDateTime.displayDateTime = eventDateTime.startDateTime.format(getStartDateTime) + "-" + eventDateTime.endDateTime.format(getTime);
            return eventDateTime;
        } catch (DateTimeParseException e) {
            throw new DukeException("Incorrect event format. \n Please key in event (details) /at d/mm/yyyy (start time)HHmm-(end time)HHmm");
        }
    }

    public static DateTime readEventTime (String dateTime) throws DukeException {
        DateTime eventDateTime = new DateTime();
        int divider = dateTime.indexOf("-");
        if (divider == -1 || (divider == dateTime.length() - 1) || dateTime.substring(divider + 1).replace(" ", "").equals("")) {
            throw new DukeException("Please key in the end time.");
        }
        String end = dateTime.substring(divider + 1, dateTime.length());
        eventDateTime.startDateTime = LocalDateTime.parse(dateTime.substring(0,divider), getStartDateTime);
        eventDateTime.endDateTime = LocalTime.parse(end, DateTimeFormatter.ofPattern("HHmm"));
        eventDateTime.displayDateTime = eventDateTime.startDateTime.format(getStartDateTime) + "-" + eventDateTime.endDateTime.format(getTime);
        return eventDateTime;
    }

        private static String getDayOfMonth(String dd) {
            if (dd.substring(0,1).equals("0")){
                dd = dd.substring(1,dd.length());
            }
            int day = Integer.parseInt(dd);
            if (day >= 11 && day <= 31) {
                return day + "th";
            }
            switch (day & 10) {
                case 1: return day + "st";
                case 2: return day + "nd";
                case 3: return day + "rd";
                default: return day + "th";
            }
        }

        public String toString() {
            return displayDateTime;
        }
}
