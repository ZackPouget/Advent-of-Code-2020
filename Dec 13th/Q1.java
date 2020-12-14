import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Q1 {
        public static void main(String[] args) {
        ArrayList<Integer> buses = new ArrayList<Integer>();
        try {
            Scanner in = new Scanner(new File("input.txt"));

            int timestamp = in.nextInt();
            String line = in.next();

            int prevIndex = -1;
            int nextComma = line.indexOf(",");
            while (nextComma != -1) {
                if (!line.substring(prevIndex + 1, nextComma).equals("x"))
                    buses.add(Integer.parseInt(line.substring(prevIndex + 1, nextComma)));
                prevIndex = nextComma;
                nextComma = line.indexOf(",", nextComma + 1);
            }
            if (!line.substring(prevIndex + 1, line.length()).equals("x"))
                buses.add(Integer.parseInt(line.substring(prevIndex + 1, line.length())));

            int min = Integer.MAX_VALUE;
            int minBus = 0;
            for (int i = 0; i < buses.size(); i++) {
                int timeToArrival = buses.get(i) - (timestamp % buses.get(i));
                if (min > timeToArrival) {
                    min = timeToArrival;
                    minBus = buses.get(i);
                }
            }

            System.out.println(min * minBus);
        } catch (FileNotFoundException e) {
            System.out.println("Input not found");
            e.printStackTrace();
        }
    }
}