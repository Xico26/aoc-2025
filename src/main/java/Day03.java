import common.Utils;

import java.util.List;

public class Day03 {
    List<String> blocks;
    int joltageSum;

    public Day03(String inputFileName) {
        this.blocks = Utils.parseInput(inputFileName);
        joltageSum = 0;
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

        System.out.println(maxJolt);
        joltageSum += maxJolt;
    }
}
