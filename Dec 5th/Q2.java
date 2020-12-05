import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Q2 {
    public static int row(String pass) {
        int low = 0;
        int high = 127;
        for (int i = 0; i < 7; i++) {
            int mid = (low + high) / 2;
            if (pass.charAt(i) == 'F') {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    public static int column(String pass) {
        int low = 0;
        int high = 7;
        for (int i = 7; i < 10; i++) {
            int mid = (low + high) / 2;
            if (pass.charAt(i) == 'L') {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        boolean[] fullSeats = new boolean[1024];
        try {
            Scanner in = new Scanner(new File("input.txt"));

            while (in.hasNext()) {
                String pass = in.next();
                fullSeats[row(pass) * 8 + column(pass)] = true;
            }
            
            for (int i = 1; i < 1023; i++) {
                if (fullSeats[i - 1] && !fullSeats[i] && fullSeats[i + 1]) {
                    System.out.println(i);
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Input could not be found");
            e.printStackTrace();
        }
    }
}
