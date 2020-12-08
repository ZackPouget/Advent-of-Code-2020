import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Q1 {
    public static void main(String[] args) {
        ArrayList<String> instructions = new ArrayList<String>();
        HashSet<Integer> ranLines = new HashSet<Integer>();

        try {
            Scanner in = new Scanner(new File("input.txt"));

            while (in.hasNextLine())
                instructions.add(in.nextLine());
            
            int index = 0;
            int accumulator = 0;
            while (!ranLines.contains(index)) {
                ranLines.add(index);
                if (instructions.get(index).substring(0, 3).equals("acc")) {
                    accumulator += Integer.parseInt(instructions.get(index).substring(4));
                } else if (instructions.get(index).substring(0, 3).equals("jmp")) {
                    index += Integer.parseInt(instructions.get(index).substring(4)) - 1;
                } 
                index++;
            }

            System.out.println(accumulator);
        } catch (FileNotFoundException e) {
            System.out.println("Input not found");
            e.printStackTrace();
        }
    }
}