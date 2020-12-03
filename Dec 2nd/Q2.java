import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Q2 {
    public static int index = 0;

    public static int getInt(String line) {
        int num = 0;
        for (; line.charAt(index) >= '0' && line.charAt(index) <= '9'; index++) {
            num *= 10;
            num += line.charAt(index) - '0';
        }
        return num;
    }

    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(new File("input.txt"));

            int validCount = 0;
            while (in.hasNextLine()) {
                //Parse the input
                String line = in.nextLine();
                int pos1 = getInt(line);
                index++;
                int pos2 = getInt(line);
                char ch = line.charAt(++index);
                index += 3;
                String password = line.substring(index);
                index = 0;

                if (pos1 > password.length() || pos2 > password.length())
                    continue;

                boolean pos1Valid = password.charAt(pos1 - 1) == ch;
                boolean pos2Valid = password.charAt(pos2 - 1) == ch;

                if ((!pos1Valid && pos2Valid) || (pos1Valid && !pos2Valid))
                    validCount++;
            }
            System.out.println("Valid passwords: " + validCount);
            in.close();
        } catch (FileNotFoundException exception) {
            System.out.println("Input not found");
            exception.printStackTrace();
        }
    }
}