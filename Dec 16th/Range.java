public class Range {
    public int min;
    public int max;

    public Range(String str) {
        rangeFromString(str);
    }

    public void rangeFromString(String str) {
        min = Integer.parseInt(str.substring(0, str.indexOf("-")));
        max = Integer.parseInt(str.substring(str.indexOf("-") + 1));
    }

    public boolean inRange(int value) {
        return min <= value && max >= value;
    }

    @Override
    public String toString() {
        return min + "-" + max;
    }
}
