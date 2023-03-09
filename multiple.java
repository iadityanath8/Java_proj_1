import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.crypto.interfaces.DHKey;

/**
 * multiple
 */
import DB_beta.Mbdb_first_beta_0;

public class multiple {

    static void newdb(String path, int offset) throws IOException {
        FileWriter writer = new FileWriter(path);

        for (int i = 0; i < offset; i++) {
            writer.append(" ");
            writer.append("\n");
        }
        writer.append("Meow");
        writer.close();
    }

}
// Multiple db
// Pk settler 
// class pacakage 
// refactoring