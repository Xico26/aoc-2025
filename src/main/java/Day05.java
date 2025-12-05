import common.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day05 {
    private List<String> validIds;
    private List<String> ingredientIds;
    private List<Long> freshIngredients;

    public Day05 (String inputFileName) {
        List<String> inputsList = Utils.parseInput(inputFileName);
        this.validIds = new ArrayList<>();
        this.ingredientIds = new ArrayList<>();
        this.freshIngredients = new ArrayList<>();

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

            long startInt =  Long.parseLong(start);
            long endInt = Long.parseLong(end);

            if (id >= startInt && id <= endInt) {
                return true;
            }
        }

        return false;
    }

}
