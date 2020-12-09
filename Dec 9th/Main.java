import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

class Main {
    final static int PREAMBLE = 25;

    public static void main(String[] args) {
        ArrayList<Integer> file = new ArrayList<Integer>();

        int index = 0;
        HashSet<Integer> set = new HashSet<Integer>();
        try {
            Scanner in = new Scanner(new File("input.txt"));
            while (in.hasNextInt())
                file.add(in.nextInt());
            
            for (int i = 0; i < PREAMBLE; i++) 
                set.add(file.get(i));
            
            int num = -1;
            while (index + PREAMBLE < file.size()) {
                num = file.get(index + PREAMBLE);
                boolean found = false;
                for (int i = index; i < index + PREAMBLE; i++) {
                    if (set.contains(num - file.get(i))) {
                        found = true;
                        set.remove(file.get(index++));
                        set.add(num);
                        break;
                    }
                }
                if (!found)
                    break;
            }
            System.out.println("Invalid number: " + num);

            int low = 0, high = 1, sum = file.get(0);
            while (sum != num) {
                if (sum < num) {
                    sum += file.get(high++);
                } else {
                    sum -= file.get(low++);
                }
            }

            int min = num, max = 0;
            for (int i = low; i < high; i++) {
                if (file.get(i) > max) {
                    max = file.get(i);
                }
                if (file.get(i) < min) {
                    min = file.get(i);
                }
            }
            System.out.println("Sum of min and max: " + (min + max));
        } catch (FileNotFoundException e) {
            System.out.println("Input not found");
            e.printStackTrace();
        }
    }
}