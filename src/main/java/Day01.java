import common.Utils;

import java.util.List;

public class Day01 {
    List<String> inputsList;

    public Day01 (String inputFileName) {
        this.inputsList = Utils.parseInput(inputFileName);
    }

    public void run() {
        // printInputs();
        int p1 = part1();
        int p2 = part2();

        System.out.println("Part 1 output: " + p1);
        System.out.println("Part 2 output: " + p2);
    }

    private void printInputs() {
        for (String l: this.inputsList) {
            System.out.println(l);
        }


    }

    public int part1() {
        int value = 50;
        int numZeros = 0;

        for (String line : inputsList) {
            System.out.print(value + " + " + line + " = ");
            char direction =  line.charAt(0);
            String rotationStr = line.substring(1);
            int rotation = Integer.parseInt(rotationStr);

            value = performRotation(value, rotation, direction);

            System.out.print(value + "\n");

            if (value == 0) {
                numZeros++;
            }

            System.out.println("num zeros: " + numZeros);
        }

        return numZeros;
    }

    private int performRotation (int value, int rotation, char direction) {
        switch (direction) {
            case 'L':
                return Math.floorMod(value - rotation, 100);
            case 'R':
                return Math.floorMod(value + rotation, 100);
            default:
                throw new IllegalArgumentException("Invalid direction");
        }
    }

    private int countPasses (int oldValue, int newValue, int rotation, char direction) {
        if (direction == 'R') {
            return (oldValue + rotation) / 100;
        } else {
            return (rotation - oldValue + 99) / 100;
        }
    }

    private int turnLeft(int value) {
        if (value - 1 < 0) {
            return 99;
        }
        return value--;
    }

    private int turnRight (int value) {
        if (value + 1 > 99) {
            return 0;
        }
        return value++;
    }

    public int part2() {
        int value = 50;
        int numZeros = 0;

        for (String line : inputsList) {
            System.out.print(value + " + " + line + " = ");
            char direction =  line.charAt(0);
            String rotationStr = line.substring(1);
            int rotation = Integer.parseInt(rotationStr);

            for (int i = 0; i < rotation; i++) {
                if (direction == 'R') {
                    value++;
                    if (value > 99) {
                        value = 0;
                        if (i < rotation - 1) {
                            numZeros++;
                        }
                    }
                } else {
                    value--;
                    if (value < 0) {
                        value = 99;
                        if (i < rotation - 1) {
                            numZeros++;
                        }
                    }
                }
            }

            if (value == 0) {
                numZeros++;
            }

            System.out.print(value + "\n");

            System.out.println("num zeros: " + numZeros);
        }

        return numZeros;
    }
}
