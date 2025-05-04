package com.companyz.app.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CSVUtil {
    public static void write(String file, List<String[]> rows) throws IOException {
        try (PrintWriter pw = new PrintWriter(file)) {
            for (String[] r : rows) pw.println(String.join(",", r));
        }
    }
}
