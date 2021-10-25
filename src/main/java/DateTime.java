import java.time.format.DateTimeFormatter;
import java.time.LocalTime;
import java.time.LocalDateTime;

public class DateTime {
    private LocalTime endDateTime;
    private LocalDateTime startDateTime;
    private String displayDateTime;
    private static DateTimeFormatter getDay = DateTimeFormatter.ofPattern("dd");
    private static DateTimeFormatter getTime = DateTimeFormatter.ofPattern("HH:mm");
    private static DateTimeFormatter getMonth= DateTimeFormatter.ofPattern("MMM");

    public static DateTime setDeadline(String dateTime) {
        DateTime deadlineDateTime = new DateTime();
        DateTimeFormatter toFormat = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        deadlineDateTime.startDateTime = LocalDateTime.parse(dateTime, toFormat);
        deadlineDateTime.displayDateTime = getDayOfMonth(deadlineDateTime.startDateTime.format(getDay)) + " of " + deadlineDateTime.startDateTime.format(getMonth)
                + ", " + deadlineDateTime.startDateTime.format(getTime);

        return deadlineDateTime;
    }

    public static DateTime setEventTime (String dateTime) throws DukeException {
        Border lines = new Border();
        DateTime eventDateTime = new DateTime();
        int divider = dateTime.indexOf("-");
        if (divider == -1 || (divider == dateTime.length() - 1)) {
            throw new DukeException((lines.createLine()) + "\n Need to have end time. \n" + (lines.createLine()));
        }

        String end = dateTime.substring(divider + 1, dateTime.length());
        DateTimeFormatter toFormat = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        eventDateTime.startDateTime = LocalDateTime.parse(dateTime, toFormat);
        eventDateTime.endDateTime = LocalTime.parse(end, DateTimeFormatter.ofPattern("HHmm"));
        eventDateTime.displayDateTime = getDayOfMonth(eventDateTime.startDateTime.format(getDay)) + " " + eventDateTime.startDateTime.format(getMonth)
                + " " + eventDateTime.startDateTime.format(getTime) + "-" + eventDateTime.endDateTime.format(getTime);
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
