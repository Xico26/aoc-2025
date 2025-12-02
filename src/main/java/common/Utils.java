package common;

import java.io.*;
import java.util.List;

public class Utils {
    public static List<String> parseInput (String inputFileName) {
        try (InputStream is = Utils.class.getResourceAsStream(inputFileName)) {
            if (is == null) {
                throw new FileNotFoundException(inputFileName);
            }
            return new BufferedReader(new InputStreamReader(is)).lines().toList();
        } catch (IOException e) {
            System.err.println("File not found: " + inputFileName);
        }
        return null;
    }
}
