import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

class Main {
    public static final int TURNS = 2020;

    public static void main(String[] args) {
        HashMap<Integer, Integer> seen = new HashMap<Integer, Integer>();
        try {
            Scanner in = new Scanner(new File("input.txt"));

            int turn = 0;
            int prevNum = 0;
            while (true) {
                prevNum = in.nextInt();
                turn++;
                if (!in.hasNextInt())
                    break;
                seen.put(prevNum, turn);
            }

            for (; turn < TURNS; turn++) {
                if (seen.containsKey(prevNum)) {
                    int turnDiff = turn - seen.get(prevNum);
                    seen.put(prevNum, turn);
                    prevNum = turnDiff;
                } else {
                    seen.put(prevNum, turn);
                    prevNum = 0;
                }
            }

            System.out.println(prevNum);
        } catch (FileNotFoundException e) {
            System.out.println("Input not found");
            e.printStackTrace();
        }
    }
}