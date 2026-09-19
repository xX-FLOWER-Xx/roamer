package fact.it.roamer.platformertesting;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ReadWriteController {

    private final File levels_folder;

    public ReadWriteController() {

        levels_folder = new File(System.getenv("LOCALAPPDATA") + "\\Roamer\\levels");
        levels_folder.mkdirs();

    }

    public void write_objects(ArrayList<Object> objects) {

        try (FileWriter writer = new FileWriter(levels_folder.getAbsoluteFile() + "\\test_level", false)) {
            for (Object object : objects) {
                writer.write(object.toString() + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to save objects", e);
        }

    }

}
