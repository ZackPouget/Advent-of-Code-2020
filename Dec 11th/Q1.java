import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Q1 {
    public static ArrayList<char[]> grid = new ArrayList<char[]>();
    public static ArrayList<char[]> newGrid = new ArrayList<char[]>();

    public static void applyRule(int x, int y) {
        if (newGrid.size() <= y)
            newGrid.add(new char[grid.get(0).length]);

        int occupiedCount = adajacentOccupiedSeats(x, y);
        if (occupiedCount == 0 && getChar(x, y) == 'L')
            newGrid.get(y)[x] = '#';
        else if (occupiedCount >= 4 && getChar(x, y) == '#')
            newGrid.get(y)[x] = 'L';
        else
            newGrid.get(y)[x] = getChar(x, y);
            
    }

    public static char getChar(int x, int y) {
        if (y >= grid.size() || y < 0 || x >= grid.get(0).length || x < 0)
            return '.';
        else
            return grid.get(y)[x];
    }

    public static int adajacentOccupiedSeats(int x, int y) {
        int occupiedCount = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0)
                    continue;
                else if (getChar(x + i, y + j) == '#')
                    occupiedCount++;
            }
        }
        return occupiedCount;
    }

    public static boolean equals() {
        boolean isEqual = true;
        for (int i = 0; i < grid.get(0).length; i++) {
            for (int j = 0; j < grid.size(); j++) {
                if (grid.get(j)[i] != newGrid.get(j)[i]) {
                    isEqual = false;
                    break;
                }
            }
            if (!isEqual)
                break;
        }
        return isEqual;
    }

    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(new File("input.txt"));

            while (in.hasNextLine()) {
                grid.add(in.nextLine().toCharArray());
            }

            while (true) {
                for (int i = 0; i < grid.get(0).length; i++) {
                    for (int j = 0; j < grid.size(); j++) {
                        applyRule(i, j);
                    }
                }
                if (equals()) break;
    
                grid = newGrid;
                newGrid = new ArrayList<char[]>();
            }

            int occupiedCount = 0;
            for (int i = 0; i < grid.get(0).length; i++) {
                for (int j = 0; j < grid.size(); j++) {
                    if (getChar(i, j) == '#')
                        occupiedCount++;
                }
            }
            System.out.println(occupiedCount);
        } catch (FileNotFoundException e) {
            System.out.println("Input not found");
            e.printStackTrace();
        }
    }
}