public class BitwiseInteger {
    public static final int SIZE = 36;

    public boolean[] bits;

    public BitwiseInteger() {
        bits = new boolean[SIZE];
        for (int i = 0; i < SIZE; i++)
            bits[i] = false;
    }

    public BitwiseInteger(long num) {
        bits = new boolean[SIZE];
        for (int i = 0; i < SIZE; i++)
            bits[i] = false;
        fromLong(num);
    }

    public void fromLong(long num) {
        for (int i = SIZE - 1; i >= 0; i--) {
            bits[i] = num % 2 == 1;
            num /= 2;
        }
    }

    public void applyMask(String mask) {
        for (int i = SIZE - 1; i >= 0; i--) {
            if (mask.charAt(i) != 'X')
                bits[i] = mask.charAt(i) == '1';
        }
    }

    public long toLong() {
        long power = 1;
        long sum = 0;
        for (int i = 0; i < SIZE; i++) {
            sum += power * (bits[SIZE - 1 - i] ? 1 : 0);
            power *= 2;
        }
        return sum;
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < SIZE; i++)
            str += bits[i] ? 1 : 0;
        return str;
    }
}