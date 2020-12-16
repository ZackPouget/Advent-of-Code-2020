import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Q2 {
    public static void main(String[] args) {
        ArrayList<Range> ranges = new ArrayList<Range>();
        ArrayList<String> fields = new ArrayList<String>();
        ArrayList<int[]> validTickets = new ArrayList<int[]>();
        try {
            Scanner in = new Scanner(new File("input.txt"));

            //Get all of the fields and ranges for the fields
            while (in.hasNextLine()) {
                String line = in.nextLine();
                if (line.equals(""))
                    break;
                fields.add(line.substring(0, line.indexOf(":")));
                Pattern pattern = Pattern.compile("\\d+-\\d+");
                Matcher matcher = pattern.matcher(line);

                while (matcher.find())
                    ranges.add(new Range(matcher.group()));
            }

            //Get my ticket
            in.nextLine();
            String[] myTicket = in.nextLine().split(",");
            in.nextLine();
            in.nextLine();
            

            //Eliminate invalid tickets
            while (in.hasNextLine()) {
                String[] values = in.nextLine().split(",");

                boolean validTicket = true;
                for (int i = 0; i < values.length; i++) {
                    int value = Integer.parseInt(values[i]);
                    boolean validValue = false;
                    for (int j = 0; j < ranges.size(); j++) {
                        if (ranges.get(j).inRange(value)) {
                            validValue = true;
                            break;
                        }
                    }
                    if (!validValue) {
                        validTicket = false;
                        break;
                    }
                }
                if (validTicket) {
                    int[] ticket = new int[values.length];
                    for (int i = 0; i < values.length; i++)
                        ticket[i] = Integer.parseInt(values[i]);
                    validTickets.add(ticket);
                }
            }

            //Check every possible field, and see which positions the field could be in by eliminating positions
            //with values outside of the ranges for that field
            ArrayList<ArrayList<Integer>> possibleFieldPositions = new ArrayList<ArrayList<Integer>>();
            for (int i = 0; i < fields.size(); i++) {
                boolean[] possiblePositionFlags = new boolean[fields.size()];
                for (int j = 0; j < possiblePositionFlags.length; j++)
                    possiblePositionFlags[j] = true;

                for (int k = 0; k < validTickets.size(); k++)
                    for (int j = 0; j < possiblePositionFlags.length; j++)
                        if (!(ranges.get(i * 2).inRange(validTickets.get(k)[j]) || ranges.get(i * 2 + 1).inRange(validTickets.get(k)[j])))
                            possiblePositionFlags[j] = false;
                
                ArrayList<Integer> possiblePositions = new ArrayList<Integer>();
                for (int j = 0; j < possiblePositionFlags.length; j++)
                    if (possiblePositionFlags[j])
                        possiblePositions.add(j);
                
                possibleFieldPositions.add(possiblePositions);
            }

            //Reduce the number of possible field positions by looking for field that only have one possible position, and removing that
            //position from the other fields possible positions, until every field has only one possible position
            boolean eliminated = false;
            while (!eliminated) {
                eliminated = true;
                for (int i = 0; i < possibleFieldPositions.size(); i++) {
                    if (possibleFieldPositions.get(i).size() > 1) {
                        eliminated = false;
                    } else {
                        for (int j = 0; j < possibleFieldPositions.size(); j++) {
                            if (j == i)
                                continue;
                            else
                                possibleFieldPositions.get(j).remove((Object) possibleFieldPositions.get(i).get(0));
                        }
                    }
                }
            }

            Long product = (long) 1;
            for (int i = 0; i < fields.size(); i++) {
                if (fields.get(i).indexOf("departure") == 0) {
                    System.out.println(fields.get(i) + ": " + myTicket[possibleFieldPositions.get(i).get(0)]);
                    product *= Integer.parseInt(myTicket[possibleFieldPositions.get(i).get(0)]);
                }
            }
            System.out.println(product);
        } catch (FileNotFoundException e) {
            System.out.println("Input not found");
            e.printStackTrace();
        }
    }
}