import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Q1 {
    public static Graph graph = new Graph();

    public static void addToGraph(String line) {
        Pattern pattern = Pattern.compile("\\w+\\s\\w+\\sbag");
        Matcher matcher = pattern.matcher(line);
        matcher.find();
        String container = matcher.group();
        while (matcher.find()) {
            graph.addEdge(matcher.group(), container);
        }
    }

    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(new File("input.txt"));
            
            while (in.hasNextLine())
                addToGraph(in.nextLine());
            graph.explore("shiny gold bag");
            System.out.println(graph.explored.size());
        } catch (FileNotFoundException e) {
            System.out.println("Input not found");
            e.printStackTrace();
        }
    }
}