package parser;

import command.*;
import exception.DukeException;

/**
 * Parses String user input/ text file to get corresponding Command object to execute.
 */
public class Parser {
    /**
     * Processes user input/ line from txt file and gets appropriate command.
     * @param instruction String from user input/ text file
     * @return Corresponding Task to be executed in run method of Duke class.
     * @throws DukeException When there are no matching command. i.e. user input or line from txt file is invalid.
     */
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
            case FIND:
                return new FindCommand(arr[1]);
            default:
                if (arr[1].equals("")) {
                    throw new DukeException("");
                }
                return new AddCommand(command, arr[1]);
            }
    } catch (IndexOutOfBoundsException | DukeException e) {
        throw new DukeException("Please provide commands in the format: (command type) (task details)");
    }
}
}
