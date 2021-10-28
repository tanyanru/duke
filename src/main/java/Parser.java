public class Parser {
    TaskList schedule;

    public static Command parse(String instruction) throws DukeException {
        String[] arr = instruction.split(" ", 2);
        FullCommand command = FullCommand.getByAction(arr[0]);
        try {
            switch (command) {
                case LIST:
                    return new ListCommand();
                case BYE:
                    return new ExitCommand();
                case DONE:
                    return new EditCommand(Integer.parseInt(arr[1]) - 1);
                case DELETE:
                    return new DeleteCommand(Integer.parseInt(arr[1]) - 1);
                default:
                    if (arr[1].equals("")){
                        throw new DukeException("");
                    }
                    return new AddCommand(command, arr[1]);
            }
    } catch (IndexOutOfBoundsException | DukeException e) {
        throw new DukeException("Please provide commands in the format: (command type) (task details)");
    }
}
}
