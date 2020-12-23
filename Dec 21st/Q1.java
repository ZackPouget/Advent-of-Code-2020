import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

class Q1 {
    public static <T> int indexOf(T[] arr, T search) {
        for (int i = 0; i < arr.length; i++)
            if (arr[i].equals(search)) return i;
        return -1;
    }

    public static void main(String[] args) {
        HashMap<String, HashSet<String>> map = new HashMap<String, HashSet<String>>();
        HashMap<String, Integer> ingredientsFreq = new HashMap<String, Integer>();
        try {
            Scanner in = new Scanner(new File("input.txt"));

            while(in.hasNextLine()) {
                String[] food = in.nextLine().split(" ");

                int indexOfAllergens = indexOf(food, "(contains");

                for (int i = 0; i < indexOfAllergens; i++) {
                    if (ingredientsFreq.containsKey(food[i])) {
                        ingredientsFreq.put(food[i], ingredientsFreq.get(food[i]) + 1);
                    } else {
                        ingredientsFreq.put(food[i], 1);
                    }
                }

                for (int i = indexOfAllergens + 1; i < food.length; i++) {
                    String allergen = food[i].substring(0, food[i].length() - 1);
                    HashSet<String> newSet = new HashSet<String>();
                    if (!map.containsKey(allergen)) {
                        for (int j = 0; j < indexOfAllergens; j++) {
                            newSet.add(food[j]);
                        }
                    } else {
                        for (int j = 0; j < indexOfAllergens; j++) {
                            if (map.get(allergen).contains(food[j])) newSet.add(food[j]);
                        }
                    }
                    map.put(allergen, newSet);
                }
            }

            Iterator<HashSet<String>> iterator = map.values().iterator();

            while (iterator.hasNext()) {
                Iterator<String> maybeAllergens = iterator.next().iterator();

                while(maybeAllergens.hasNext())
                    ingredientsFreq.remove(maybeAllergens.next());
            }
            
            Iterator<Integer> frequencies = ingredientsFreq.values().iterator();
            int total = 0;

            while (frequencies.hasNext())
                total += frequencies.next();
            
            System.out.println("Frequency of safe ingredients: " + total);

            HashMap<String, String> allergenToIngredient = new HashMap<String, String>();
            ArrayList<String> allergenList = new ArrayList<String>(map.keySet());
            for (int i = 0; !allergenList.isEmpty(); i = i + 1 >= allergenList.size() ? 0 : i + 1) {
                HashSet<String> set = map.get(allergenList.get(i));
                if (set.size() == 1) {
                    String ingredient = set.toArray(new String[1])[0];
                    for (int j = 0; j < allergenList.size(); j++) {
                        if (i != j) {
                            map.get(allergenList.get(j)).remove(ingredient);
                        }
                    }
                    allergenToIngredient.put(allergenList.get(i), ingredient);
                    allergenList.remove(i);
                }
            }

            allergenList = new ArrayList<String>(map.keySet());
            allergenList.sort(new Comparator<String>(){
                @Override
                public int compare(String o1, String o2) {
                    return o1.compareTo(o2);
                }
            });
            
            for (String allergen : allergenList)
                System.out.print(allergenToIngredient.get(allergen) + ",");
        } catch (FileNotFoundException e) {
            System.out.println("Input not found");
        }
    }
}