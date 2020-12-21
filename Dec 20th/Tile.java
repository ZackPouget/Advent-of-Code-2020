import java.util.ArrayList;

public class Tile {
    private int id;
    private String[] borders = new String[4];
    private char[][] image;

    public Tile(ArrayList<String> tileList, int id) {
        this.id = id;
        String leftBorderString = "";
        String rightBorderString = "";

        for (int i = 0; i < tileList.size(); i++) {
            leftBorderString += tileList.get(i).charAt(0);
            rightBorderString += tileList.get(i).charAt(tileList.get(i).length() - 1);
        }

        borders[0] = tileList.get(0); //Top
        borders[1] = rightBorderString; //Right
        borders[2] = reverseString(tileList.get(tileList.size() - 1)); //Bottom
        borders[3] = reverseString(leftBorderString); //Left

        image = new char[tileList.size() - 2][tileList.size() - 2];
        for (int i = 1; i < tileList.size() - 1; i++) {
            image[i - 1] = tileList.get(i).substring(1, tileList.get(i).length() - 1).toCharArray();
        }
    }

    public int getId() {
        return id;
    }

    private boolean palindromicEquals(String str1, String str2) {
        if (str1.equals(str2)) {
            return true;
        } else if (str1.length() != str2.length()) {
            return false;
        } else {
            for (int i = 0; i < str1.length(); i++)
                if (str1.charAt(i) != str2.charAt(str2.length() - 1 - i)) return false;
            return true;
        }
    }

    private String reverseString(String str) {
        String revString = "";
        for (int i = str.length() - 1; i >= 0; i--)
            revString += str.charAt(i);
        return revString;
    }

    public boolean doesConnect(Tile tile) {
        for (int i = 0; i < this.borders.length; i++) {
            for (int j = 0; j < tile.borders.length; j++) {
                if (palindromicEquals(this.borders[i], tile.borders[j])) return true;
            }
        }
        return false;
    }

    public boolean forceAlign(Tile tile) {
        for (int i = 0; i < this.borders.length; i++) {
            for (int j = 0; j < tile.borders.length; j++) {
                if (palindromicEquals(this.borders[i], tile.borders[j])) {
                    
                };
            }
        }
        return false;
    }

    private void rotate(int turns) {
        char[][] newImage = new char[image.length][image.length];
        switch (turns) {
            case 0:
            break;
            case 1:
            for (int i = 0; i < image.length; i++) {
                for (int j = 0; j < image.length; j++) {
                    newImage[j][image.length - 1 - i] = image[i][j];
                }
            }
            image = newImage;
            break;
            case 2:
            for (int i = 0; i < image.length; i++) {
                for (int j = 0; j < image.length; j++) {
                    newImage[image.length - 1 - i][image.length - 1 - j] = image[i][j];
                }
            }
            image = newImage;
            break;
            case 3:
            for (int i = 0; i < image.length; i++) {
                for (int j = 0; j < image.length; j++) {
                    newImage[image.length - 1 - j][i] = image[i][j];
                }
            }
            image = newImage;
            break;
        }
    }



    @Override
    public String toString() {
        return id + ":\n" + borders[0] + "\n" + borders[1] + "\n" + borders[2] + "\n" + borders[3];
    }

    @Override
    public int hashCode() {
        return getId();
    }
}
