package br.com.ongadocao.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVUtil {

    public static List<String[]> readAll(String path) {
        List<String[]> rows = new ArrayList<>();
        File f = new File(path);
        if (!f.exists()) return rows;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                // split simples por vírgula (sem suporte a campos com vírgula)
                String[] parts = line.split(",", -1);
                rows.add(parts);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rows;
    }

    public static void writeAll(String path, List<String> lines) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (String l : lines) {
                bw.write(l);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
