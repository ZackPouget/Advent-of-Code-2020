import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Q1 {
    public static void main(String[] args) {
        ArrayList<Range> ranges = new ArrayList<Range>();
        try {
            Scanner in = new Scanner(new File("input.txt"));

            while (in.hasNextLine()) {
                String line = in.nextLine();
                if (line.equals(""))
                    break;
                Pattern pattern = Pattern.compile("\\d+-\\d+");
                Matcher matcher = pattern.matcher(line);

                while (matcher.find())
                    ranges.add(new Range(matcher.group()));
            }

            //Get rid of the line for your ticket
            for (int i = 0; i < 4; i++)
                in.nextLine();

            int total = 0;
            while (in.hasNextLine()) {
                String[] values = in.nextLine().split(",");

                for (int i = 0; i < values.length; i++) {
                    int value = Integer.parseInt(values[i]);
                    boolean valid = false;
                    for (int j = 0; j < ranges.size(); j++) {
                        if (ranges.get(j).inRange(value)) {
                            valid = true;
                            break;
                        }
                    }
                    if (!valid) {
                        total += value;
                    }
                }
            }

            System.out.println(total);
        } catch (FileNotFoundException e) {
            System.out.println("Input not found");
            e.printStackTrace();
        }
    }
}