import java.io.FileWriter;
import java.io.IOException;

public class FileWriterClass {
    public static void writeFile(String filePath, String addText)
    {
        Border lines = new Border();
        try
        {
            try
            {
                FileWriter fw = new FileWriter(filePath);
                System.out.println("Written in " + filePath);
                fw.write(addText);
                fw.close();
            } catch (IOException e)
            {
                throw new DukeException((lines.createLine()) + "\n Please check. Error message: " + e.getMessage() + "\n" + (lines.createLine()));
            }
        } catch (DukeException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
