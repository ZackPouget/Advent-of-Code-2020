import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Q1 {
    public static final int SIZE = 25;
    public static final int CYCLES = 6;

    public static void main(String[] args) {
        Space3d space = new Space3d(SIZE);
        ArrayList<ArrayList<Boolean>> plane = new ArrayList<ArrayList<Boolean>>();
        try {
            Scanner in = new Scanner(new File("input.txt"));
        
            while (in.hasNextLine()) {
                String line = in.nextLine();
                plane.add(new ArrayList<Boolean>());
                for (int i = 0; i < line.length(); i++) {
                    plane.get(plane.size() - 1).add(line.charAt(i) == '#');
                }
            }

            for (int i = 0; i < plane.size(); i++) {
                for (int j = 0; j < plane.get(0).size(); j++) {
                    space.setState(i - plane.size() / 2, j - plane.size() / 2, 0, plane.get(i).get(j));
                }
            }

            for (int i = 0; i < CYCLES; i++) {
                Space3d newSpace = new Space3d(SIZE);
                for (int x = -SIZE/2; x < SIZE/2; x++) {
                    for (int y = -SIZE/2; y < SIZE/2; y++) {
                        for (int z = -SIZE/2; z < SIZE/2; z++) {
                            int neighbors = space.getActiveNeighbors(x, y, z);
                            newSpace.setState(x, y, z, (space.getState(x, y, z) && neighbors == 2) || neighbors == 3);
                        }
                    }
                }
                space = newSpace;
            }

            System.out.println(space.getActiveCubes());
        } catch (FileNotFoundException e) {
            System.out.println("Input not found");
            e.printStackTrace();
        }
    }
}