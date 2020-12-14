import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Q2 {
    public static long modInverse(long a, long m) { 
        long m0 = m; 
        long y = 0, x = 1; 
  
        if (m == 1) 
            return 0; 
  
        while (a > 1) { 
            // q is quotient 
            long q = a / m; 
  
            long t = m; 
  
            // m is remainder now, process 
            // same as Euclid's algo 
            m = a % m; 
            a = t; 
            t = y; 
  
            // Update x and y 
            y = x - q * y; 
            x = t; 
        } 
  
        // Make x positive 
        if (x < 0) 
            x += m0; 
  
        return x; 
    } 

    public static void main(String[] args) {
        ArrayList<Integer> buses = new ArrayList<Integer>();
        ArrayList<Integer> offsets = new ArrayList<Integer>();
        try {
            Scanner in = new Scanner(new File("input.txt"));

            in.nextInt(); //Gets rid of timestamp
            String line = in.next();

            int prevIndex = -1;
            int offset = 0;
            int nextComma = line.indexOf(",");
            while (nextComma != -1) {
                if (!line.substring(prevIndex + 1, nextComma).equals("x")) {
                    buses.add(Integer.parseInt(line.substring(prevIndex + 1, nextComma)));
                    offsets.add(offset);
                }
                offset++;
                prevIndex = nextComma;
                nextComma = line.indexOf(",", nextComma + 1);
            }
            if (!line.substring(prevIndex + 1, line.length()).equals("x")) {
                buses.add(Integer.parseInt(line.substring(prevIndex + 1, line.length())));
                offsets.add(offset);
            }

            long n = 1;
            for (int i = 0; i < buses.size(); i++) {
                n *= buses.get(i);
            }

            long t = 0;
            for (int i = 0; i < buses.size(); i++) {
                long ni = n / buses.get(i);
                long inverse = modInverse(ni, buses.get(i));
                t += ni * inverse * (buses.get(i) - offsets.get(i));
            }
            t %= n;

            System.out.println(t);
        } catch (FileNotFoundException e) {
            System.out.println("Input not found");
            e.printStackTrace();
        }
    }
}
