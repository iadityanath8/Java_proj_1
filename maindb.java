import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
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

    static String find_indexer(String path, int i, int j) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String row;
        int normal = 0;
        String re_val = " ";
        while ((row = reader.readLine()) != null) {
            String data[] = row.split(",");
            if (normal == i) {
                if(j < data.length){
                    re_val = data[j];
                    break;
                }
            }else{
                normal++;
            }
        }
        if (re_val.isEmpty()) {
            return "failed";
        }else{
            return re_val;
        }
    }

    static void change_cond_primary_key(String path, String field_name, String val, String sec_name,
            String new_opt)
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
            if (i == a.get(0)) {
                data[index] = data[index].replace(find_indexer(path,i,index), new_opt);
                ad = Arrays.asList(data);
                op.add(ad);
                i++;
            } else {
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
        change_cond_primary_key("db.csv", "Roll_no", "3", "Roll_no", "2");
    }
}