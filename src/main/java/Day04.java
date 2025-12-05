import common.Utils;

import java.util.ArrayList;
import java.util.List;

public class Day04 {
    List<String> gridLines;
    int numAvaliable;

    public Day04(String inputFileName) {
        this.gridLines = new ArrayList<String>(Utils.parseInput(inputFileName));
        this.numAvaliable = 0;
    }

    public void run () {
        countAvailable();

        System.out.println("Removed: " + this.numAvaliable);

//        System.out.println("Final result: ");
//        for (String line : this.gridLines) {
//            System.out.println(line);
//        }
    }

    public void countAvailable () {
        int numLines = this.gridLines.size();
        boolean couldRemove = true;
        while (couldRemove) {
            // System.out.println("Going again");
            // System.out.println("Removed " + numAvaliable + " so far!");
            int numLinesRemoved = 0;
            for (int i = 0; i < numLines; i++) {
                String prev;
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
                if (countAvailableLine(prev, i, next)) {
                    numLinesRemoved++;
                }

//                System.out.println("Now looking like:");
//                for (String line : this.gridLines) {
//                    System.out.println(line);
//                }
            }

            if (numLinesRemoved <= 0) {
                couldRemove = false;
            }

        }
    }

    public boolean countAvailableLine (String prev, int currIndex, String next) {
        boolean couldRemove = false;

        for (int i = 0; i < this.gridLines.get(currIndex).length(); i++) {
            String curr = this.gridLines.get(currIndex);
            if (curr.charAt(i) == '@') {
                int numAdjacent = checkAdjacent(prev, curr, next, i);
                if (numAdjacent < 4) {
                    couldRemove = true;
                    StringBuilder sb = new StringBuilder(curr);
                    sb.setCharAt(i, '.');
                    this.gridLines.set(currIndex, sb.toString());
                    // System.out.println("Removed one at line " + currIndex + " position " + i);
                    // System.out.println(this.gridLines.get(currIndex));
                    numAvaliable++;
                }
            }
        }

        return couldRemove;
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
