import common.Utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day02 {
    String rangesStr;
    List<String> ranges;
    List<Long> invalidIDs;

    public Day02(String inputFileName) {
        this.rangesStr = parseInput(inputFileName);
        this.ranges = Arrays.stream(this.rangesStr.split(",")).toList();
        this.invalidIDs = new ArrayList<>();
    }

    /*
    read input a-b,c-d,e-f,g-h...
    split at ,
    split at -
     */
    public String parseInput (String inputFileName) {
        try (InputStream is = Utils.class.getResourceAsStream(inputFileName)) {
            if (is == null) {
                throw new FileNotFoundException(inputFileName);
            }
            return new BufferedReader(new InputStreamReader(is)).readLine();
        } catch (IOException e) {
            System.err.println("File not found: " + inputFileName);
        }
        return null;
    }

    public void run () {
        processLines();
        for (Long id : this.invalidIDs) {
            System.out.println(id);
        }

        System.out.println("Sum of invalids: " + (Long) this.invalidIDs.stream().mapToLong(Long::longValue).sum());
    }

    public void processLine(String l) {
        System.out.println("Processing line: " + l);
        String[] split = l.split("-");

        String start = split[0];
        String end = split[1];

        System.out.println("From " + start + " to " + end);

        long startNum = Long.parseLong(start);
        long endNum = Long.parseLong(end);
        long num = startNum;

        while (num <= endNum) {
            // not even number of digits -> can continue
            String numStr = Long.toString(num);
            int len = numStr.length();

            /*
            if ((len % 2) == 1) {
                num++;
                continue;
            }

            String[] parts = { numStr.substring(0, len / 2), numStr.substring(len / 2) };
            if (parts[0].equals(parts[1])) {
                this.invalidIDs.add(num);
            }
             */


            /*
            Split string into N parts
            check if the N parts are equal
             */

            for (int i = 1; i <= len / 2; i++) {
                if (len % i != 0) {
                    // not divisible by i
                    continue;
                }

                String part = numStr.substring(0, i);
                boolean allEqual = true;

                for (int j = i; j < len; j += i) {
                    if (!numStr.substring(j, j + i).equals(part)) {
                        allEqual = false;
                        break;
                    }
                }

                if (allEqual) {
                    if (!invalidIDs.contains(num)) {
                        invalidIDs.add(num);
                    }
                    break;
                }
            }

            num++;
        }
    }

    public void processLines () {
        for (String range : this.ranges) {
            processLine(range);
        }
    }
}
