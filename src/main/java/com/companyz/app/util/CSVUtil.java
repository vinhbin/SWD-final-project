package com.companyz.app.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CSVUtil {

    /** Writes rows to CSV file; each String[] is one record. */
    public static void write(String fileName, List<String[]> rows) throws IOException {
        try (PrintWriter pw = new PrintWriter(new File(fileName))) {
            for (String[] r : rows)
                pw.println(String.join(",", r));
        }
    }
}
