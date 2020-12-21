import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

class Q1 {
    public static HashMap<Integer, ArrayList<String>> possibilityCache = new HashMap<Integer, ArrayList<String>>();

    public static ArrayList<String> getPossibilities(ArrayList<String> ruleList, int index) {
        String ruleString = ruleList.get(index).substring(ruleList.get(index).indexOf(":") + 2);
        ArrayList<String> possibilities = new ArrayList<String>();

        //Handle base cases
        if (ruleString.equals("\"a\"") || ruleString.equals("\"b\"")) {
            possibilities.add(Character.toString(ruleString.charAt(1)));
        } else {
            //Handle pipes
            int startOfPipe;
            int endOfPipe = -3;
            do {
                startOfPipe = endOfPipe + 3;
                endOfPipe = ruleString.indexOf('|', startOfPipe);
                endOfPipe = endOfPipe == -1 ? ruleString.length() : endOfPipe - 1;

                ArrayList<String> newPossibilities = new ArrayList<String>();
                newPossibilities.add("");
                String pipe = ruleString.substring(startOfPipe, endOfPipe);

                int startOfInt;
                int endOfInt = -1;
                do {
                    startOfInt = endOfInt + 1;
                    endOfInt = pipe.indexOf(" ", startOfInt);
                    endOfInt = endOfInt == -1 ? pipe.length() : endOfInt;

                    int subRuleIndex = Integer.parseInt(pipe.substring(startOfInt, endOfInt));
                    ArrayList<String> subRulePossibilities;
                    if (possibilityCache.containsKey(subRuleIndex)) {
                        subRulePossibilities = possibilityCache.get(subRuleIndex);
                    } else {
                        subRulePossibilities = getPossibilities(ruleList, subRuleIndex);
                    }
                    ArrayList<String> modifiedPossbilities = new ArrayList<String>();

                    for (int i = 0; i < newPossibilities.size(); i++) {
                        for (int j = 0; j < subRulePossibilities.size(); j++) {
                            modifiedPossbilities.add(newPossibilities.get(i) + subRulePossibilities.get(j));
                        }
                    }

                    newPossibilities = modifiedPossbilities;
                } while (endOfInt != pipe.length());

                for (String possibility : newPossibilities)
                    possibilities.add(possibility);
            } while (endOfPipe != ruleString.length());
        }

        possibilityCache.put(index, possibilities);
        return possibilities;
    }

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();
        try {
            Scanner in = new Scanner(new File("input.txt"));

            String str = in.nextLine();
            while (!str.equals("")) {
                list.add(str);
                str = in.nextLine();
            }

            for (int i = 0; i < list.size(); i++) {
                int index = Integer.parseInt(list.get(i).substring(0, list.get(i).indexOf(":")));
                while (index != i) {
                    str = list.get(index);
                    list.set(index, list.get(i));
                    list.set(i, str);
                    index = Integer.parseInt(list.get(i).substring(0, list.get(i).indexOf(":")));
                }
            }

            ArrayList<String> validMessages = getPossibilities(list, 0);
            HashSet<String> messageChecker = new HashSet<String>();

            for (String message : validMessages)
                messageChecker.add(message);
            
            int validMessageCount = 0;
            while (in.hasNextLine()) {
                if (messageChecker.contains(in.nextLine())) {
                    validMessageCount++;
                }
            }
            System.out.println(validMessageCount);
        } catch (FileNotFoundException e) {
            System.out.println("Input not found");
            e.printStackTrace();
        }
    }
}