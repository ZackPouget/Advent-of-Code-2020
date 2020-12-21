import java.util.ArrayList;
import java.util.HashSet;

public class Image {
    private ArrayList<ArrayList<Tile>> tileGrid = new ArrayList<ArrayList<Tile>>();
    private ArrayList<Tile> spareTiles = new ArrayList<Tile>();
    HashSet<Tile> corners = new HashSet<Tile>();
    private Tile currPiece;
    private int stage = 0;

    public void assemble(Tile newTile) {
        if (stage == 0) {
            //Stage 0: Gather all of the corner pieces

            currPiece = newTile;
            corners.add(newTile);
            if (corners.size() == 4) {
                stage++;
                tileGrid.add(new ArrayList<Tile>());
                tileGrid.get(0).add(currPiece);
            }
        } else if (stage == 1) {
            //Stage 1: Build a line from one corner piece to another
        }
    }
}
