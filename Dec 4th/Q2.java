import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Q2 {
    final static String[] required = {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"};
    final static String[] validEcls = {"amb", "blu", "brn", "gry", "grn", "hzl", "oth"};

    //Note: when working with strings, remember that regex is a thing

    static boolean validateByr(String str) {
        int index = str.indexOf("byr");
        if (index == -1 || index + 8 > str.length() || (index + 8 != str.length() && str.charAt(index + 8) != ' '))
            return false;
        else {
            try {
                int year = Integer.parseInt(str, index + 4, index + 8, 10);
                return year >= 1920 && year <= 2002;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }

    static boolean validateIyr(String str) {
        int index = str.indexOf("iyr");
        if (index == -1 || index + 8 > str.length() || (index + 8 != str.length() && str.charAt(index + 8) != ' '))
            return false;
        else {
            try {
                int year = Integer.parseInt(str, index + 4, index + 8, 10);
                return year >= 2010 && year <= 2020;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }

    static boolean validateEyr(String str) {
        int index = str.indexOf("eyr");
        if (index == -1 || index + 8 > str.length() || (index + 8 != str.length() && str.charAt(index + 8) != ' '))
            return false;
        else {
            try {
                int year = Integer.parseInt(str, index + 4, index + 8, 10);
                return year >= 2020 && year <= 2030;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }

    static boolean validateHgt(String str) {
        int index = str.indexOf("hgt");
        boolean isCm = str.indexOf("cm", index) - index == 7;
        boolean isIn = str.indexOf("in", index) - index == 6;
        if (index == -1 || !(isCm || isIn))
            return false;
        else {
            try {
                if (isCm) {
                    int num = Integer.parseInt(str, index + 4, index + 7, 10);
                    return num >= 150 && num <= 193;
                } else {
                    int num = Integer.parseInt(str, index + 4, index + 6, 10);
                    return num >= 59 && num <= 76;
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }

    static boolean validateHcl(String str) {
        int index = str.indexOf("hcl:#");
        if (index == -1 || index + 11 > str.length() || (index + 11 != str.length() && str.charAt(index + 11) != ' '))
            return false;
        else {
            for (int i = index + 5; i < index + 11; i++) {
                if (!(str.charAt(i) >= '0' && str.charAt(i) <= '9') && !(str.charAt(i) >= 'a' && str.charAt(i) <= 'f')) {
                    return false;
                }
            }
            return true;
        }
    }

    static boolean validateEcl(String str) {
        int index = str.indexOf("ecl:");
        if (index == -1 || index + 7 > str.length() || (index + 7 != str.length() && str.charAt(index + 7) != ' '))
            return false;
        else {
            for (int i = 0; i < validEcls.length; i++) {
                if (str.substring(index + 4, index + 7).equals(validEcls[i])) {
                    return true;
                }
            }
            return false;
        }
    }

    static boolean validatePid(String str) {
        int index = str.indexOf("pid:");
        if (index == -1 || index + 13 > str.length() || (index + 13 != str.length() && str.charAt(index + 13) != ' '))
            return false;
        else {
            for (int i = index + 5; i < index + 11; i++) {
                if (!(str.charAt(i) >= '0' && str.charAt(i) <= '9')) {
                    return false;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        boolean[] validFields = new boolean[required.length];
        for (int i = 0; i < validFields.length; i++)
            validFields[i] = false;
        try {
            Scanner in = new Scanner(new File("input.txt"));
            int fieldCount = 0;
            while (in.hasNextLine()) {
                String line = in.nextLine();
                if (line.equals("")) {
                    boolean valid = true;
                    for (int i = 0; i < validFields.length; i++) {
                        if (!validFields[i])
                            valid = false;
                        validFields[i] = false;
                    }
                    if (valid)
                        fieldCount++;
                } else {
                    validFields[0] = validFields[0] || validateByr(line);
                    validFields[1] = validFields[1] || validateIyr(line);
                    validFields[2] = validFields[2] || validateEyr(line);
                    validFields[3] = validFields[3] || validateHgt(line);
                    validFields[4] = validFields[4] || validateHcl(line);
                    validFields[5] = validFields[5] || validateEcl(line);
                    validFields[6] = validFields[6] || validatePid(line);
                }
            }
            System.out.println(fieldCount);
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("Input not found");
            e.printStackTrace();
        }
    }
}