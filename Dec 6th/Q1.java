import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class Q1 {
    public static void main(String[] args) {
        long total = 0;
        HashSet<Character> set = new HashSet<Character>();

        try {
            Scanner in = new Scanner(new File("input.txt"));

            while (in.hasNextLine()) {
                String line = in.nextLine();
                if (line.equals("")) {
                    total += set.size();
                    set.clear();
                } else {
                    for (int i = 0; i < line.length(); i++) {
                        set.add(line.charAt(i));
                    }
                }
            }
            total += set.size();
            System.out.println(total);
        } catch (FileNotFoundException e) {
            System.out.println("Input not found");
            e.printStackTrace();
        }
    }
}