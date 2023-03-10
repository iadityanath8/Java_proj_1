package DB_beta;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Mbdb_first_beta_0 {
   public void write_Create_unstable_db(String path, String pk, String... args) throws IOException {
      FileWriter writer = new FileWriter(path);
      List<String> op = Arrays.asList(args);

      int len = args.length;
      int i = 0;
      String val = "";
      for (int j = 0; j < args.length; j++) {
         if (args[j].equals(pk)) {
            val = Integer.toString(j);
         }
      }
      writer.append(val);
      writer.append("\n");
      while (i < len - 1) {
         writer.append(args[i]);
         writer.append(",");
         i++;
      }
      writer.append(args[i]);

      writer.append("\n");

      writer.flush();
      writer.close();
   }

   public void write(String path, List<List<String>> op, Boolean selecter) throws IOException {
      FileWriter writer = new FileWriter(path, selecter);
      for (List<String> rowdata : op) {
         writer.append(String.join(",", rowdata));
         writer.append("\n");
      }
      writer.flush();
      writer.close();
   }

   public void replace_unstable(String path, String old_opt, String new_opt) throws IOException {
      BufferedReader read = new BufferedReader(new FileReader(path));
      String row;
      List<List<String>> op = new ArrayList<List<String>>();
      while ((row = read.readLine()) != null) {
         row = row.replaceAll(old_opt, new_opt);
         String[] data = row.split(",");
         List<String> a = Arrays.asList(data);
         op.add(a);
      }
      write(path, op, false);
      read.close();
   }
}

// List<List<String>> rows = Arrays.asList(
// Arrays.asList("Aditya Nath", "12", "1"),
// Arrays.asList("Nemesis", "1", "3"),
// Arrays.asList("corporate", "12", "4"));
// writer.append("name");
// writer.append(",");
// writer.append("class");
// writer.append(",");
// writer.append("Roll_no");
// writer.append("\n");