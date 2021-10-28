package filewriter;

import datetime.DateTime;
import exception.DukeException;
import task.*;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;

public class Storage {
    String filePath;
    FileWriter fw;
    ArrayList<Task> taskList;
    boolean isAppend = true;

    public Storage(String filepath) throws DukeException{
        this.filePath = filepath;
        this.createWriter();
    }

    private void createWriter() throws DukeException{
        try{
            fw = new FileWriter(filePath, isAppend);
        } catch (IOException e) {
            throw new DukeException("\n Invalid filepath. Please check.  \n");
        }
    }

    public void closeWriter() throws DukeException{
        try {
            fw.close();
        } catch (IOException e) {
            throw new DukeException("\n Invalid filepath. Please check.  \n");
        }
    }

    public void writeToFile(String textToAdd) throws DukeException{
        try {
            fw.write(textToAdd + "\n");
            //System.out.println(textToAdd);
        } catch (IOException e) {
            throw new DukeException("\n Please check. Error message: " + e.getMessage() + "\n");
        }
    }

    public void editFile(TaskList schedule) throws DukeException{
        try {
            checkAppend(false);
            for (Task task: schedule.getList()) {
                fw.write(task.toString() + "\n");
            }
        } catch (IOException e) {
            throw new DukeException("failed to write to file");
        }
    }

    private void checkAppend(boolean toAppend) throws DukeException {
        closeWriter();
        isAppend = toAppend;
        createWriter();
    }

    private Task read(String line) throws DukeException {
        Task output;
        switch (line.charAt(line.indexOf("[") + 1)) {
            case 'T':
                output = new Todo(line.substring(7, line.length()));
                return output;
            case 'D':
                try {
                    int divider = line.indexOf("(by:");
                    String input = line.substring(7, divider);
                    input += "/by " + DateTime.readDeadLine(line.substring(divider + 5, line.length() - 1)).toString();
                    output = new Deadline(input);
                    return output;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    throw new DukeException("\n Deadline task not stored properly. \n ");
                }
            default:
                try {
                    int divider = line.indexOf("(at:");
                    String input = line.substring(7, divider);
                    input += "/at " + DateTime.readEventTime(line.substring(divider + 5, line.length() - 1)).toString();
                    output = new Event(input);
                    return output;
                } catch (Exception e) {
                    throw new DukeException("\n Event task not stored properly. \n ");
                }
        }
    }

    public Storage load() throws DukeException {
        try {
            taskList = new ArrayList<>();
            File f = new File(filePath); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNextLine()) {
                String line = s.nextLine();
                if (! line.equals("")) {
                    Task newTask = read(line.replace("\n", ""));
                    if (line.substring(4,5).equals("\u2713")){
                        newTask.markAsDone();
                    }
                    taskList.add(newTask);
                }
            }
            return this;
        } catch (FileNotFoundException e){
            throw new DukeException("\n File not found! \n ");
        } catch (Exception e){
            throw new DukeException("Unforeseen load errors: " + e.getMessage());
        }
    }

    public ArrayList<Task> getSchedule(){
        return taskList;
    }
}