import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Q1 {

    public static int hitCheck(double slopeRight, double slopeDown) throws FileNotFoundException {
        int down = 0;
        int hitCount = 0;
        int right = 0;
        Scanner in = new Scanner(new File("input.txt"));
        while (in.hasNextLine()) {
            String line = in.nextLine();
            if (down % slopeDown == 0) {
                if (line.charAt((int) right) == '#')
                    hitCount++;
                right += slopeRight;
                right %= line.length();
            }
            down++;
        }
        in.close();
        return hitCount;
    }

    public static void main(String[] args) {
        try {
            int[] rightSlopes = {1, 3, 5, 7, 1};
            int[] downSlopes = {1, 1, 1, 1, 2};
            long product = 1;
            for (int i = 0; i < rightSlopes.length; i++) {
                int hitCount = hitCheck(rightSlopes[i], downSlopes[i]);
                product *= hitCount;
                System.out.println(rightSlopes[i] + " / " + downSlopes[i] + ": " + hitCount);
            }
            System.out.println(product);
        } catch (FileNotFoundException e) {
            System.out.println("Input not Found");
            e.printStackTrace();
        }
    }
}