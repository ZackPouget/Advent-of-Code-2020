import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Q2 {
    public static boolean loops(ArrayList<String> instructions) {
        HashSet<Integer> ranLines = new HashSet<Integer>();
        int index = 0;
        while (!ranLines.contains(index)) {
            if (index >= instructions.size())
                return false;
            ranLines.add(index);
            if (instructions.get(index).substring(0, 3).equals("jmp")) {
                index += Integer.parseInt(instructions.get(index).substring(4)) - 1;
            } 
            index++;
        }
        return true;
    }

    public static void main(String[] args) {
        ArrayList<String> instructions = new ArrayList<String>();

        try {
            Scanner in = new Scanner(new File("input.txt"));

            while (in.hasNextLine())
                instructions.add(in.nextLine());

            int testIndex = -1;
            
            while (true) {
                testIndex++;
                if (instructions.get(testIndex).substring(0, 3).equals("acc")) {
                    continue;
                } else if (instructions.get(testIndex).substring(0, 3).equals("jmp")) {
                    instructions.set(testIndex, "nop" + instructions.get(testIndex).substring(3));
                    if (loops(instructions))
                        instructions.set(testIndex, "jmp" + instructions.get(testIndex).substring(3));
                    else
                        break;
                } else {
                    instructions.set(testIndex, "jmp" + instructions.get(testIndex).substring(3));
                    if (loops(instructions))
                        instructions.set(testIndex, "nop" + instructions.get(testIndex).substring(3));
                    else
                        break;
                }
            }

            int accumulator = 0;
            int index = 0;
            while (index < instructions.size()) {
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