import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class Graph {
    private HashMap<String, HashSet<String>> nodes = new HashMap<String, HashSet<String>>();
    public HashSet<String> explored = new HashSet<String>();

    public void addEdge(String node, String connection) {
        if (!nodes.containsKey(node))
            nodes.put(node, new HashSet<String>());
        nodes.get(node).add(connection);
    }

    public Iterator<String> getEdges(String node) {
        return nodes.get(node).iterator();
    }

    public void explore(String node) {
        int total = 0;
        try {
            Iterator<String> iterator = getEdges(node);
            while (iterator.hasNext()) {
                String nextNode = iterator.next();
                if (!explored.contains(nextNode)) {
                    explored.add(nextNode);
                    explore(nextNode);
                }
            }
        } catch (NullPointerException e) {
            return;
        }
    }
}
