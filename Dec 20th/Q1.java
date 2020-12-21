import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Q1 {
    public static void main(String[] args) {
        ArrayList<Tile> tiles = new ArrayList<Tile>();
        try {
            Scanner in = new Scanner(new File("input.txt"));

            while (in.hasNextLine()) {
                String tileIDString = in.nextLine();
                int tileID = Integer.parseInt(tileIDString.substring(tileIDString.indexOf(' ') + 1, tileIDString.length() - 1));

                ArrayList<String> tileList = new ArrayList<String>();

                String tileSection = in.nextLine();;
                while (!tileSection.equals("")) {
                    tileList.add(tileSection);
                    tileSection = in.nextLine();
                }

                tiles.add(new Tile(tileList, tileID));
            }

            Image image = new Image();

            long product = 1;
            for (int i = 0; i < tiles.size(); i++) {
                int connections = 0;
                for (int j = 0; j < tiles.size(); j++) {
                    if (j == i) {
                        continue;
                    } else if (tiles.get(i).doesConnect(tiles.get(j))) {
                        connections++;
                    }
                }
                if (connections == 2) {
                    image.assemble(tiles.get(i));
                    product *= tiles.get(i).getId();
                }
            }
            System.out.println("Corner product: " + product);

            
        } catch (FileNotFoundException e) {
            System.out.println("Input not found");
            e.printStackTrace();
        }
    }
}