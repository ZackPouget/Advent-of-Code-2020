import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

class Q2 {
    public static long evalString(String str) {
        //Add values to Queue
        LinkedList<Long> values = new LinkedList<Long>();
        LinkedList<Character> operators = new LinkedList<Character>();
        int value = 0;
        boolean onValue = false;
        
        for (int i = 0; i < str.length(); i++) {
            //Handle values
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                value = (10 * value) + (int) str.charAt(i) - '0';
                onValue = true;
            } else if (onValue == true) {
                values.add((long) value);
                value = 0;
                onValue = false;
            }

            //Handle operators
            if (str.charAt(i) == '+' || str.charAt(i) == '*')
                operators.add(str.charAt(i));

            //Simplify equation (remove parenthesis)
            if (str.charAt(i) == '(') {
                int parenthesis = 1;
                for (int j = i + 1; j < str.length(); j++) {
                    if (str.charAt(j) == '(')
                        parenthesis++;
                    else if (str.charAt(j) == ')')
                        parenthesis--;
                    if (parenthesis == 0) {
                        values.add(evalString(str.substring(i + 1, j)));
                        i = j;
                        break;
                    }
                }
            }
        }
        if (onValue)
            values.add((long) value);

        //Evaluate the expression by multiplying all of the individual summations
        long sum = 0;
        char operator = 'n';
        long product = 1;
        while (!operators.isEmpty()) {
            sum += values.remove();
            operator = operators.remove();
            if (operator == '*') {
                product *= sum;
                sum = 0;
            }
        }
        if (operator == 'n') {
            return values.remove();
        } else {
            sum += values.remove();
            product *= sum;
            return product;
        }
    }

    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(new File("input.txt"));

            long total = 0;
            while (in.hasNextLine())
                total += evalString(in.nextLine());

            System.out.println(total);
        } catch (FileNotFoundException e) {
            System.out.println("Input not found");
            e.printStackTrace();
        }
    }
}