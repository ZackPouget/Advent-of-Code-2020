import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    static final int TOTAL = 2020;
    static final int NUMSCOUNT = 3;
    static ArrayList<Integer> list = new ArrayList<Integer>();

    //Returns the index of the search parameter if it is in the list, otherwise returns -1;
    static int binarySearch(int search, int start, int end) {
        if (start > end) {
            return -1;
        }
        int mid = (start + end) / 2;
        if (list.get(mid) == search) {
            return mid;
        } else if (list.get(mid) < search) {
            return binarySearch(search, mid + 1, end);
        } else {
            return binarySearch(search, start, mid - 1);
        }
    }

    //Searches list for numbers that when added together produce sum, and puts the indices into arr
    static void findAddends(int[] arr, int index, int sum) {
        if (index == arr.length - 1) {
            arr[index] = binarySearch(sum, 0, list.size() - 1);
        } else {
            for (int i = 0; i < list.size(); i++) {
                findAddends(arr, index + 1, sum - list.get(i));
                if (arr[index + 1] != -1) {
                    arr[index] = i;
                    return;
                }
            }
            arr[index] = -1;
        }
    }

    public static void main(String[] args) {
        try {
            //Add the input to the list and sort it
            Scanner in = new Scanner(new File("input.txt"));
            while (in.hasNextInt()) {
                list.add(in.nextInt());
            }
            list.sort(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
            });

            //Find the indices of the addends and put them in an array 
            int[] arr = new int[NUMSCOUNT];
            findAddends(arr, 0, TOTAL);
            if (arr[0] != -1) {
                int product = 1;
                for (int i = 0; i < arr.length; i++) {
                    System.out.println("Addend " + (i + 1) + ": " + list.get(arr[i]));
                    product *= list.get(arr[i]);
                }
                System.out.println("Product: " + product);
            } else {
                System.out.println("Addends not found");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Input not found");
            e.printStackTrace();
        }
    }
}