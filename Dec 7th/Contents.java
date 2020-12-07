import java.util.ArrayList;
import java.util.HashMap;

public class Contents {
    public static HashMap<String, Contents> contentsOfBag = new HashMap<String, Contents>();

    public ArrayList<Integer> counts = new ArrayList<Integer>();
    public ArrayList<String> bags = new ArrayList<String>();
    public boolean isEmpty = false;

    public long getContents() {
        if (isEmpty)
            return 0;
        else {
            long total = 0;
            for (int i = 0; i < counts.size(); i++) {
                total += counts.get(i) * contentsOfBag.get(bags.get(i)).getContents() + counts.get(i);
            }
            return total;
        }
    }
}
