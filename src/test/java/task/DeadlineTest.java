package task;

import exception.DukeException;
import org.junit.jupiter.api.Test;

import exception.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class DeadlineTest {

    @Test
    void constructor_noDate_exceptionThrown() {
        try {
            Deadline deadline = new Deadline("Generate Exception /by");
            fail();
        } catch (DukeException e) {
            String expectedOutput = "Incorrect deadline format.\nPlease key in deadline (task) /by d/mm/yyyy HHmm";
            assertEquals(expectedOutput, e.getMessage());
        }
    }
}