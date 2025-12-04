import common.Utils;

import java.util.ArrayList;
import java.util.List;

public class Day03 {
    List<String> blocks;
    Long joltageSum;

    public Day03(String inputFileName) {
        this.blocks = Utils.parseInput(inputFileName);
        joltageSum = 0L;
    }

    public void run() {
        processLines();
    }

    public void processLines() {
        for (String block: this.blocks) {
            processBlock(block);
        }
        System.out.println("total: " + joltageSum);
    }

    public void processBlock(String block) {
        /* Part 1
        int maxJolt = 0;


        for (int i = 0; i < block.length(); i++) {
            char c = block.charAt(i);
            for (int j = i + 1; j < block.length(); j++) {
                char c2 = block.charAt(j);
                int jolt = 0;
                jolt += Integer.parseInt(c + "") * 10;
                jolt += Integer.parseInt(c2 + "");

                if (jolt > maxJolt) {
                    maxJolt = jolt;
                }
            }
        }
         */

        int numDigits = 12; // swap to 2 for part 1
        List<Integer> batteryJoltages = new ArrayList<>();
        int lastIndex = -1;
        for (int i = 0; i < numDigits; i++) {
            int windowStart = lastIndex + 1;
            int windowEnd = block.length() - (numDigits - i - 1);

            System.out.println(i + " " + windowStart + " " + windowEnd);
            if (windowStart < windowEnd) {
                String substr =  block.substring(windowStart, windowEnd);
                System.out.println("Now evaluating " + substr);

                int max = 0;
                int bestIndex = -1;
                for (int j = 0; j < substr.length(); j++) {
                    int elem = Integer.parseInt(substr.charAt(j) + "");
                    if (elem > max) {
                        max = elem;
                        bestIndex = j;
                    }
                }
                lastIndex = bestIndex + windowStart;

                batteryJoltages.add(max);
            } else {
                break;
            }
        }

        Long jolt = 0L;
        for (Integer batteryJoltage: batteryJoltages) {
            jolt = jolt * 10 +  batteryJoltage;
        }

        System.out.println(jolt);
        joltageSum += jolt;
    }
}
