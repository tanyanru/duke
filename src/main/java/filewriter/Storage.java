package filewriter;

import datetime.DateTime;
import exception.DukeException;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class of storage.
 * Stores and reads from text file.
 */
public class Storage {
    String filePath;
    FileWriter fw;
    ArrayList<Task> taskList;
    boolean isAppend = true;

    /**
     * Constructor of Storage object.
     * @param filepath filepath to text file to be read from and edited.
     * @throws DukeException When CreateWriter throws DukeException.
     */
    public Storage(String filepath) throws DukeException {
        this.filePath = filepath;
        this.createWriter();
    }

    /**
     * Creates FileWriter member fw.
     * @throws DukeException when invalid filepath.
     */
    private void createWriter() throws DukeException {
        try {
            fw = new FileWriter(filePath, isAppend);
        } catch (IOException e) {
            throw new DukeException("\n Invalid filepath. Please check.");
        }
    }

    /**
     * Wrapper to close FileWriter fw.
     * Uses in Execute method of ExitCommand object.
     * @throws DukeException when invalid filepath.
     */
    public void closeWriter() throws DukeException {
        try {
            fw.close();
        } catch (IOException e) {
            throw new DukeException("\n Invalid filepath. Please check.");
        }
    }

    /**
     * Adds Task to TaskList data stored in txt file.
     * Calls during execute method of addCommand object.
     * @param textToAdd (Command Object).to String();
     * @throws DukeException Throws when IOException is caught.
     */
    public void writeToFile(String textToAdd) throws DukeException {
        try {
            fw.write(textToAdd + "\n");
            //System.out.println(textToAdd);
        } catch (IOException e) {
            throw new DukeException("\n Please check. Error message: " + e.getMessage());
        }
    }

    /**
     * Rewrites data in txt file with editions made.
     * Calls when Execute command of DeleteCommand or EditCommand object modifies TaskList.
     * @param schedule Modifies contents of TaskList.
     * @throws DukeException Throws when IOException is caught.
     */
    public void editFile(TaskList schedule) throws DukeException {
        try {
            checkAppend(false);
            for (Task task: schedule.getList()) {
                fw.write(task.toString() + "\n");
            }
        } catch (IOException e) {
            throw new DukeException("failed to write to file");
        }
    }

    /**
     * Toggles willAppend boolean, which determines if FileWriter fw will append to txt file or overwrite it.
     * @param toAppend New boolean value of toAppend.
     * @throws DukeException Throws when createWriter throws DukeException.
     */
    private void checkAppend(boolean toAppend) throws DukeException {
        closeWriter();
        isAppend = toAppend;
        createWriter();
    }

    /**
     * Interprets each line in txt file and add corresponding Task to TaskList.
     * @param line every line in the txt file represents a task.
     * @return Task object with type, task name, date and timing as described in String.
     * @throws DukeException Throws when Task is not stored in the correct format in txt file.
     */
    private Task read(String line) throws DukeException {
        Task output;
        switch (line.charAt(line.indexOf("[") + 1)) {
        case 'T':
            output = new Todo(line.substring(7, line.length()));
            updateStatus(output, line);
            return output;
        case 'D':
            try {
                int divider = line.indexOf("(by:");
                String input = line.substring(7, divider);
                input += "/by " + DateTime.readDeadLine(line.substring(divider + 5, line.length() - 1)).toString();
                output = new Deadline(input);
                updateStatus(output, line);
                return output;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                throw new DukeException("\n Deadline task not stored properly.");
            }
        default:
            try {
                int divider = line.indexOf("(at:");
                String input = line.substring(7, divider);
                input += "/at " + DateTime.readEventTime(line.substring(divider + 5, line.length() - 1)).toString();
                output = new Event(input);
                updateStatus(output, line);
                return output;
            } catch (Exception e) {
                throw new DukeException("\n Event task not stored properly.");
            }
        }
    }

    private void updateStatus(Task task, String line) {
        if (line.substring(2,4).equals("+")) {
            task.markAsDone();
        }
    }

    /**
     * Reads txt file and updates TaskList accordingly.
     * @return Storage object with updated Task taskList used in construction of TaskList object.
     * @throws DukeException when read throws DukeException.
     */
    public Storage load() throws DukeException {
        try {
            taskList = new ArrayList<>();
            File f = new File(filePath); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            Stream<String> stream = Files.lines(Paths.get(filePath));
            taskList = new ArrayList(stream.filter(line -> !line.equals(""))
                    .map(line -> {
                        try {
                            return read(line);
                        } catch (DukeException e) {
                            throw new RuntimeException();
                        }
                    }).collect(Collectors.toList()));
            return this;
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found! \n ");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            throw new DukeException("Unexpected Error: " + e.getMessage());
        }
    }

    /**
     * Calls during construction method of TaskList.
     * @return ArrayList of Task
     */
    public ArrayList<Task> getSchedule() {
        return taskList;
    }
}