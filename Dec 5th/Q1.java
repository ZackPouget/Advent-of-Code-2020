import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Q1 {
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
        try {
            Scanner in = new Scanner(new File("input.txt"));

            int maxID = -1;
            while (in.hasNext()) {
                String pass = in.next();
                int currID = row(pass) * 8 + column(pass);
                if (currID > maxID) maxID = currID;
            }
            System.out.println(maxID);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}