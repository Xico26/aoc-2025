package org.togetherjava.aoc.solutions.y2025;

import org.togetherjava.aoc.internal.puzzle.AdventDay;
import org.togetherjava.aoc.internal.puzzle.PuzzleInput;
import org.togetherjava.aoc.internal.puzzle.PuzzleSolution;

import java.util.ArrayList;
import java.util.List;

@AdventDay(day=1)
public class Day01 implements PuzzleSolution {
    List<String> inputsList;
    int[] numbers;
    int numZeros;

    public Day01 () {
        this.inputsList = new ArrayList<>();
        this.numbers = new int[100];
        this.numZeros = 0;

        for (int i = 0; i < 100; i++) {
            this.numbers[i] = i;
        }
    }

    @Override
    public Object part1(PuzzleInput input) {
        this.inputsList = input.getLines();

        int value = 50;

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
                if (value - rotation >= 0) {
                    return value - rotation;
                } else {
                    int i = value - 1;
                    if (i < 0) {
                        i = 99;
                    }
                    for (int n = 1; n <= rotation; n++) {
                        value = this.numbers[i];
                        if (i - 1 < 0) {
                            i = 99;
                        } else {
                            i--;
                        }
                    }
                    return value;
                }
            case 'R':
                if (value + rotation <= 99) {
                    return value + rotation;
                } else {
                    int i = value + 1;
                    if (i > 99) {
                        i = 0;
                    }
                    for (int n = 1; n <= rotation; n++) {
                        value = this.numbers[i];
                        if (i + 1 > 99) {
                            i = 0;
                        } else {
                            i++;
                        }
                    }
                    return value;
                }
            default:
                System.out.println("Invalid direction");
        }
        return value;
    }

    @Override
    public Object part2(PuzzleInput input) {
        int value = 50;

        for (String line : inputsList) {
            char direction =  line.charAt(0);
            String rotationStr = line.substring(1);
            int rotation = Integer.parseInt(rotationStr);

            System.out.println("num zeros before " + value + " + " + direction + rotation + ": " + numZeros);
            switch (direction) {
                case 'L':
                    int i = value - 1;
                    if (i < 0) {
                        i = 99;
                    }
                    for (int n = 1; n <= rotation; n++) {
                        i--;
                        if (i < 0) {
                            i = 99;
                            numZeros++;
                        }
                    }
                    value = performRotation(value, rotation, direction);
                    break;
                case 'R':
                    int j = value + 1;
                    if (j > 99) {
                        j = 0;
                    }
                    for (int n = 1; n <= rotation; n++) {
                        j++;
                        if (j > 99) {
                            j = 0;
                            numZeros++;
                        }
                    }
                    value = performRotation(value, rotation, direction);
                    break;
            }
            System.out.println("after: " + numZeros + " (new value = " + value + ")");
        }

        return numZeros;
    }
}
