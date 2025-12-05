import common.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day05 {
    private List<String> validIds;
    private List<String> ingredientIds;
    private List<Long> freshIngredients;
    long numberOfValidIngredients;

    public Day05 (String inputFileName) {
        List<String> inputsList = Utils.parseInput(inputFileName);
        this.validIds = new ArrayList<>();
        this.ingredientIds = new ArrayList<>();
        this.freshIngredients = new ArrayList<>();
        this.numberOfValidIngredients = 0;

        boolean hasFinishedIds = false;
        for (String l: inputsList) {
            if (l.isEmpty()) {
                hasFinishedIds = true;
                continue;
            };
            if (!hasFinishedIds) {
                this.validIds.add(l);
            } else {
                this.ingredientIds.add(l);
            }
        }
    }

    private void printLists () {
        System.out.println("VALID IDS");
        for (String l: validIds) {
            System.out.println(l);
        }

        System.out.println();

        System.out.println("INGREDIENT IDS");
        for (String l2: ingredientIds) {
            System.out.println(l2);
        }
    }

    public void run () {
        // printLists();

        // PART 1
        // validateIngredients();

        // PART 2
        checkAllRanges();
    }

    public void validateIngredients () {
        for (String l: ingredientIds) {
            long id = Long.parseLong(l);
            boolean isFresh = checkIngredient(id);
            if (isFresh) {
                this.freshIngredients.add(id);
            }
        }

        System.out.println("FRESH INGREDIENTS");
        for (Long i: freshIngredients) {
            System.out.print(i + " ");
        }
        System.out.println();

        int count = this.freshIngredients.size();
        System.out.println("Number of fresh ingredients: " + count);
    }

    public boolean checkIngredient (long id) {
        for (String range: this.validIds) {
            String[] split = range.split("-");

            String start = split[0];
            String end = split[1];

            long startVal =  Long.parseLong(start);
            long endVal = Long.parseLong(end);

            if (id >= startVal && id <= endVal) {
                return true;
            }
        }

        return false;
    }

    public List<String> mergeRanges () {
        List<String> newRanges = new ArrayList<>(this.validIds);
        boolean hasChanged = true;
        int i = 0;
        while (hasChanged && i < newRanges.size()) {
            hasChanged = mergeRange(newRanges.get(i), newRanges);
            if (!hasChanged) {
                hasChanged = true;
                i++;
            }
        }

        return newRanges;
    }

    private boolean mergeRange (String line, List<String> newRanges) {
        String[] split = line.split("-");

        String start = split[0];
        String end = split[1];

        long startVal = Long.parseLong(start);
        long endVal = Long.parseLong(end);

        int i = 0;
        while (i < newRanges.size()) {
            String range = newRanges.get(i);
            if (range.equals(line)) {
                i++;
                continue;
            }
            // System.out.println("Can I merge " + line + " with " + range);
            String[] split2 = range.split("-");

            String start2 = split2[0];
            String end2 = split2[1];

            long startVal2 = Long.parseLong(start2);
            long endVal2 = Long.parseLong(end2);

            // System.out.println("start " + start + "; end " + end + "; start2 " + start2 + "; end2 " + end2);

            if (startVal2 <= endVal + 1 && endVal2 >= startVal) {
                // System.out.println("Merging " + line + " with " + range);
                StringBuilder sb = new StringBuilder();
                sb.append(Math.min(startVal, startVal2));
                sb.append("-");
                sb.append(Math.max(endVal, endVal2));
                newRanges.remove(line);
                newRanges.remove(range);
                newRanges.add(sb.toString());
                return true;
            }

            i++;
        }

        return false;
    }

    public void checkAllRanges () {
        List<String> newRanges = mergeRanges();
        System.out.println("new ranges");
        for (String l: newRanges) {
            // getAllValidIds(l);
            System.out.println(l);
            String[] split = l.split("-");

            String start = split[0];
            String end = split[1];
            this.numberOfValidIngredients += ((Long.parseLong(end) - Long.parseLong(start)) + 1);
        }

        System.out.println("Number of valid ingredients: " + this.numberOfValidIngredients);
    }
}
