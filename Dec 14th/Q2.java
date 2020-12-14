import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

class Q2 {
    public static long[] possibleVals(String mask, long val) {
        long vals[] = new long[1];
        boolean bits[] = (new BitwiseInteger(val)).bits;
        long power = (long) Math.pow(2, bits.length - 1);

        for (int i = 0; i < mask.length(); i++) {
            if (mask.charAt(i) == '1') {
                for (int j = 0; j < vals.length; j++) {
                    vals[j] += power;
                }
            } else if (mask.charAt(i) == 'X') {
                long newVals[] = new long[vals.length * 2];
                for (int j = 0; j < newVals.length; j++) {
                    newVals[j] = vals[j % vals.length];
                    if (j >= vals.length) {
                        newVals[j] += power;
                    }
                }
                vals = newVals;
            } else {
                for (int j = 0; j < vals.length; j++) {
                    vals[j] += power * (bits[i] ? 1 : 0);
                }
            }
            power /= 2;
        }
        return vals;
    }

    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(new File("input.txt"));
            HashMap<Long, Long> memory = new HashMap<Long, Long>();

            String mask = "";
            while (in.hasNextLine()) {
                String line = in.nextLine();
                if (line.substring(0, 4).equals("mask")) {
                    mask = line.substring(7);
                } else {
                    long mem = Long.parseLong(line.substring(4, line.indexOf("]")));
                    long val = Long.parseLong(line.substring(line.indexOf("=") + 2));
                    long[] mems = possibleVals(mask, mem);
                    for (int i = 0; i < mems.length; i++) {
                        memory.put(mems[i], val);
                    }
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