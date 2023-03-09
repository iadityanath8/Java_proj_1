import java.io.IOException;
import java.security.PublicKey;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import DB_beta.*;

public class maindb extends Mbdb_first_beta_0 {

    public void replace_stable(String path, String field_name, String old_opt, String new_opt)
            throws IOException {
        mbdb obj = new mbdb();
        int index = obj.indexer(path, field_name);
        int flag = -1;
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String row;
        List<List<String>> op = new ArrayList<List<String>>();
        List<String> ad = new ArrayList<String>();
        while ((row = reader.readLine()) != null) {
            String[] data = row.split(",");
            if (index == -1) {
                break;
            }
            if (data[index].equals(old_opt)) {
                data[index] = data[index].replace(old_opt, new_opt);
                ad = Arrays.asList(data);
                op.add(ad);
                flag = 1;
            } else {
                ad = Arrays.asList(data);
                op.add(ad);
            }
        }
        if (index == -1) {
            System.err.println("cannot find " + field_name);
        }
        if (flag == -1) {
            System.err.println("cannot find " + old_opt);
        }
        write(path, op, false);
        reader.close();
    }

    static String find_indexer(String path, int i, int j) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String row;
        int normal = 0;
        String re_val = " ";
        while ((row = reader.readLine()) != null) {
            String data[] = row.split(",");
            if (normal == i) {
                if (j < data.length) {
                    re_val = data[j];
                    break;
                }
            } else {
                normal++;
            }
        }
        reader.close();
        if (re_val.isEmpty()) {
            return "failed";
        } else {
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
                data[index] = data[index].replace(find_indexer(path, i, index), new_opt);
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
        cl1.write(path, op, false);
    }

    public static int Last_indexer(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        reader.readLine().split(",");
        String[] data = reader.readLine().split(",");
        reader.close();
        return data.length - 1;
    }

    public static int pk_check(String path) throws IOException {
        String row;
        BufferedReader reader = new BufferedReader(new FileReader(path));
        row = reader.readLine();
        return Integer.parseInt(row);
    }

    public void insert_into_tb(String path, String... args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(path));
        String checker = args[pk_check(path)];
        int var = 0;
        String row;
        while ((row = reader.readLine()) != null) {
            String data[] = row.split(",");
            if (data.length != 1) {
                if (data[pk_check(path)].equals(checker)) {
                    System.err.println("Primary key violation");
                    var = 90;
                }
            }
        }

        if (var == 0) {
            if (args.length - 1 == Last_indexer(path)) {
                List<List<String>> __l = new ArrayList<List<String>>();
                __l.add(Arrays.asList(args));
                write(path, __l, true);
            } else {
                System.err.println("data inserted is quite larger than the attribute");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        maindb a=  new maindb();
        a.insert_into_tb("db.csv", "MM","12","4");
    }
}