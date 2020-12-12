import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Q1 {
    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(new File("input.txt"));
            Ship ferry = new Ship();

            while (in.hasNextLine()) {
                String line = in.nextLine();
                char action = line.charAt(0);
                int units = Integer.parseInt(line.substring(1));

                switch(action) {
                    case 'N':
                        ferry.moveNorth(units);
                        break;
                    case 'S':
                        ferry.moveNorth(-units);
                        break;
                    case 'E':
                        ferry.moveEast(units);
                        break;
                    case 'W':
                        ferry.moveEast(-units);
                        break;
                    case 'L':
                        ferry.turnRight(-units);
                        break;
                    case 'R':
                        ferry.turnRight(units);
                        break;
                    case 'F':
                        ferry.moveForward(units);
                        break;
                }
            }

            System.out.println(ferry.getManhattanDistance());
        } catch (FileNotFoundException e) {
            System.out.println("Input not found");
            e.printStackTrace();
        }
    }
}