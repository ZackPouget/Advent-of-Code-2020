import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Q2 {
    public static void specifyContents(String line) {
        Pattern pattern = Pattern.compile("\\d*\\s?\\w+\\s\\w+\\sbag");
        Matcher matcher = pattern.matcher(line);
        matcher.find();
        String container = matcher.group();
        Contents.contentsOfBag.put(container, new Contents());
        while (matcher.find()) {
            if (matcher.group().equals(" no other bag")) {
                Contents.contentsOfBag.get(container).isEmpty = true;
            } else {
                int split = matcher.group().indexOf(" ");
                Contents.contentsOfBag.get(container).counts.add(Integer.parseInt(matcher.group().substring(0, split)));
                Contents.contentsOfBag.get(container).bags.add(matcher.group().substring(split + 1));
            }
        }
    }
    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(new File("input.txt"));
            
            while (in.hasNextLine())
                specifyContents(in.nextLine());
            System.out.println(Contents.contentsOfBag.get("shiny gold bag").getContents());
        } catch (FileNotFoundException e) {
            System.out.println("Input not found");
            e.printStackTrace();
        }
    }
}