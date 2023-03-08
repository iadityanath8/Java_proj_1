import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import DB_beta.*;

public class maindb {

    public static void replace_stable(String path, String field_name, String old_opt, String new_opt)
            throws IOException {
        mbdb obj = new mbdb();
        int index = obj.indexer(path, field_name);
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String row;
        List<List<String>> op = new ArrayList<List<String>>();
        List<String> ad = new ArrayList<String>();
        while ((row = reader.readLine()) != null) {
            String[] data = row.split(",");
            if (data[index].equals(old_opt)) {
                data[index] = data[index].replace(old_opt, new_opt);
                ad = Arrays.asList(data);
                op.add(ad);
            } else {
                ad = Arrays.asList(data);
                op.add(ad);
            }
        }
        for (List<String> sa : op) {
            System.out.println(
                    sa);
        }
    }

    static void change_cond_primary_key(String path, String field_name, String val,String sec_name, String old_opt, String new_opt)
            throws IOException {
        mbdb obj = new mbdb();
        ArrayList<Integer> a = obj.indexer2(path, field_name, val);
        int index = obj.indexer(path, sec_name);
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String row;
        List<List<String>> op = new ArrayList<List<String>>();
        List<String> ad = new ArrayList<String>();
        int i = 0;
        while ((row = reader.readLine()) != null) {
            String[] data = row.split(",");
            if(i == a.get(0)){
                data[index] = data[index].replace(old_opt, new_opt);
                ad = Arrays.asList(data);
                op.add(ad);i++;
            }else{
                ad = Arrays.asList(data);
                op.add(ad);
                i++;
            }
        }
        reader.close();
        Mbdb_first_beta_0 cl1 = new Mbdb_first_beta_0();
        cl1.write(path, op);
    }

    public static void main(String[] args) throws IOException {
        // replace_stable("db.csv", "name", "Meee", "qw");e
        replace_stable("db.csv", "name", "Mai", "Meow");
    }
}