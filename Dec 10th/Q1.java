import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

class Q1 {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        try {
            Scanner in = new Scanner(new File("input.txt"));

            while (in.hasNextInt())
                list.add(in.nextInt());
            
            list.sort(new Comparator<Integer>(){
                @Override
                public int compare(Integer o1, Integer o2) {
                    if (o1 == o2)
                        return 0;
                    else if (o1 < o2)
                        return -1;
                    else 
                        return 1;
                }
            });

            int diffOf1 = 0;
            int diffOf3 = 0;
            int prevJoltage = 0;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) - prevJoltage == 1) {
                    diffOf1++;
                } else if (list.get(i) - prevJoltage == 3) {
                    diffOf3++;
                }
                prevJoltage = list.get(i);
            }
            diffOf3++; //The device adapter is always 3 more than the highest adapter

            System.out.println(diffOf1 * diffOf3);
        } catch (FileNotFoundException e) {
            System.out.println("Input not found");
            e.printStackTrace();
        }
    }
}