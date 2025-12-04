import common.Utils;

import java.util.List;

public class Day04 {
    List<String> gridLines;
    int numAvaliable;

    public Day04(String inputFileName) {
        this.gridLines = Utils.parseInput(inputFileName);
        this.numAvaliable = 0;
    }

    public void run () {
        countAvailable();

        System.out.println("Available: " + this.numAvaliable);
    }

    public void countAvailable () {
        int numLines = this.gridLines.size();
        for (int i = 0; i < numLines; i++) {
            String prev;
            String curr = this.gridLines.get(i);
            String next;
            if (i - 1 < 0) {
                prev = null;
            } else {
                prev = this.gridLines.get(i - 1);
            }

            if (i + 1 >= this.gridLines.size()) {
                next = null;
            } else {
                next = this.gridLines.get(i + 1);
            }
            countAvailableLine(prev, curr, next);
        }
    }

    public void countAvailableLine (String prev, String curr, String next) {
        for (int i = 0; i < curr.length(); i++) {
            if (curr.charAt(i) == '@') {
                int numAdjacent = checkAdjacent(prev, curr, next, i);
                if (numAdjacent < 4) {
                    numAvaliable++;
                }
            }
        }

    }

    public int checkAdjacent (String prev, String curr, String next, int pos) {
        int numAdjacent = 0;
        if (prev != null) {
            if (pos > 0 && prev.charAt(pos-1) == '@') {
                numAdjacent++;
            }
            if (prev.charAt(pos) == '@') {
                numAdjacent++;
            }
            if (pos < prev.length() - 1 && prev.charAt(pos + 1) == '@') {
                numAdjacent++;
            }
        }

        if (next != null) {
            if (pos > 0 && next.charAt(pos-1) == '@') {
                numAdjacent++;
            }
            if (next.charAt(pos) == '@') {
                numAdjacent++;
            }
            if (pos < next.length() - 1 && next.charAt(pos + 1) == '@') {
                numAdjacent++;
            }
        }

        if (pos > 0 && curr.charAt(pos-1) == '@') {
            numAdjacent++;
        }
        if (pos < curr.length() - 1 && curr.charAt(pos + 1) == '@') {
            numAdjacent++;
        }

        return numAdjacent;
    }
}
