import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class Q2 {
    public static void main(String[] args) {
        long total = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        int people = 0;

        try {
            Scanner in = new Scanner(new File("input.txt"));

            while (in.hasNextLine()) {
                String line = in.nextLine();
                if (line.equals("")) {
                    Iterator<Character> keyIterator = map.keySet().iterator();
                    while (keyIterator.hasNext()) {
                        if (map.get(keyIterator.next()) == people)
                            total++;
                    }
                    map.clear();
                    people = 0;
                } else {
                    for (int i = 0; i < line.length(); i++) {
                        map.put(line.charAt(i), map.getOrDefault(line.charAt(i), 0) + 1);
                    }
                    people++;
                }
            }
            Iterator<Character> keyIterator = map.keySet().iterator();
            while (keyIterator.hasNext()) {
                if (map.get(keyIterator.next()) == people)
                    total++;
            }
            people = 0;
            System.out.println(total);
        } catch (FileNotFoundException e) {
            System.out.println("Input not found");
            e.printStackTrace();
        }
    }
}