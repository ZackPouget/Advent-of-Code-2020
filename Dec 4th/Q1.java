import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Q1 {
    final static String[] required = {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"};

    public static void main(String[] args) {
        boolean[] foundFields = new boolean[required.length];
        try {
            Scanner in = new Scanner(new File("input.txt"));
            int fieldCount = 0;
            while (in.hasNextLine()) {
                String line = in.nextLine();
                if (line.equals("")) {
                    boolean valid = true;
                    for (int i = 0; i < foundFields.length; i++) {
                        if (!foundFields[i])
                            valid = false;
                        foundFields[i] = false;
                    }
                    if (valid)
                        fieldCount++;
                } else {
                    for (int i = 0; i < required.length; i++) {
                        if (line.contains(required[i]))
                            foundFields[i] = true;
                    }
                }
            }
            System.out.println(fieldCount);
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("Input not found");
            e.printStackTrace();
        }
    }
}
