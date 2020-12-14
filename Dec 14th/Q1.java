import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

class Q1 {
    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(new File("input.txt"));
            HashMap<Long, Long> memory = new HashMap<Long, Long>();

            String mask = "";
            BitwiseInteger val = new BitwiseInteger();
            while (in.hasNextLine()) {
                String line = in.nextLine();
                if (line.substring(0, 4).equals("mask")) {
                    mask = line.substring(7);
                } else {
                    long mem = Long.parseLong(line.substring(4, line.indexOf("]")));
                    val.fromLong(Long.parseLong(line.substring(line.indexOf("=") + 2)));
                    val.applyMask(mask);
                    memory.put(mem, val.toLong());
                }
            }

            Iterator<Long> iterator = memory.values().iterator();
            long sum = 0;
            while (iterator.hasNext()) {
                sum += iterator.next();   
            }
            
            System.out.println(sum);
        } catch (FileNotFoundException e) {
            System.out.println("Input not found");
            e.printStackTrace();
        }
    }
}