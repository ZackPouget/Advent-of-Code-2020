import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

class Q2 {
    public static HashMap<Integer, Long> pathsTo = new HashMap<Integer, Long>();
    public static HashSet<Integer> nodes = new HashSet<Integer>();

    public static long countPathsTo(int node) {
        if (node == 0) {
            return 1;
        } else if (pathsTo.containsKey(node)) {
            return pathsTo.get(node);
        } else if (!nodes.contains(node)) {
            return 0;
        } else {
            long pathCount = 0;
            for (int i = 1; i <= 3; i++) {
                pathCount += countPathsTo(node - i);
            }
            pathsTo.put(node, pathCount);
            return pathCount;
        }
    }

    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(new File("input.txt"));

            int maxNode = -1;
            while (in.hasNextInt()) {
                int node = in.nextInt();
                if (node > maxNode)
                    maxNode = node;
                nodes.add(node);
            }

            System.out.println(countPathsTo(maxNode));
        } catch (FileNotFoundException e) {
            System.out.println("Input not found");
            e.printStackTrace();
        }
    }
}