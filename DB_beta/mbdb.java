package DB_beta;

import java.io.*;
import java.util.ArrayList;

// import DB_beta.*;

public class mbdb {
    public void printer(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String row;
        while ((row = reader.readLine()) != null) {
            String data[] = row.split(",");
            for (String valio : data) {
                System.out.print(valio);
            }
            System.out.println();
        }
        reader.close();
    }

    public int indexer(String path, String field_name) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String row;
        int flag = -1;
        while ((row = reader.readLine()) != null) {
            String data[] = row.split(",");
            for (int i = 0; i < data.length; i++) {
                if (data[i].equals(field_name)) {
                    flag = i;
                }
            }
        }
        reader.close();
        return flag;
    }

    public ArrayList<Integer> indexer2(String path, String name, String field_name) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("db.csv"));
        String row;
        ArrayList<Integer> flag = new ArrayList<Integer>();
        int vir_count = 0;
        int i = indexer(path, name);
        while ((row = reader.readLine()) != null) {
            String[] data = row.split(",");
            if (data.length != 1) {
                if (data[i].equals(field_name)) {
                    flag.add(vir_count);
                    vir_count++;
                } else {
                    vir_count++;
                }
            } else {
                vir_count++;
            }
        }
        reader.close();
        if (flag.isEmpty()) {
            System.err.println("Cannot Get The attribute " + field_name);
            return flag;
        } else {
            return flag;
        }
    }

    public ArrayList<String> finder_unstable(String path, String name, String field_name, String clstr)
            throws IOException {
        int op = 0;
        String row;
        BufferedReader reader = new BufferedReader(new FileReader(path));

        ArrayList<String> ret_val = new ArrayList<String>();
        ArrayList<Integer> inde = indexer2(path, name, field_name);

        int i = indexer(path, clstr);
        int indeee = 0;
        while ((row = reader.readLine()) != null) {
            String[] data = row.split(",");
            if (indeee >= inde.size()) {
                break;
            }
            if (op == inde.get(indeee)) {
                ret_val.add(data[i]);
                indeee++;
                op++;
            } else {
                op++;
            }
        }
        reader.close();
        return ret_val;
    }

    public ArrayList<ArrayList<String>> Selecter(String path, String[] attr, String[] feild_name, String[] what)
            throws IOException {
        // {"name","class"},{"Aditya Nath","1"},{"class","Roll_no"}
        ArrayList<ArrayList<String>> arr = new ArrayList<ArrayList<String>>();
        for (int i = 0; i < what.length; i++) {
            arr.add(finder_unstable(path, attr[i], feild_name[i], what[i]));
        }
        return arr;
    }
}