package dm;


// left circular shift
public class Bits {

    public static byte leftCircularShift(byte n, int m) {
        m %= 8;
        return (byte)(((n & (0xff << (8 - m))) >>> (8 - m)) | (n << m));
    }

    public static short leftCircularShift(short n, int m) {
        m %= 16;
        return (short)(((n & (0xffff << (16 - m))) >>> (16 - m)) | (n << m));
    }

    public static int leftCircularShift(int n, int m) {
        m %= 32;
        // Use mask to harvest left bits shifted out, then stick them to right.
        return ((n & (0xffffffff << (32 - m))) >>> (32 - m)) | (n << m);
    }

    public static long leftCircularShift(long n, int m) {
        m %= 64;
        return ((n & (0xffffffffffffffffL << (64 - m))) >>> (64 - m)) | (n << m);
    }

    public static byte rightCircularShift(byte n, int m) {
        return leftCircularShift(n,8-m%8);
    }

    public static short rightCircularShift(short n, int m) {
        return leftCircularShift(n,16-m%16);
    }

    public static int rightCircularShift(int n, int m) {
        return leftCircularShift(n,32-m%32);
    }

    public static long rightCircularShift(long n, int m) {
        return leftCircularShift(n,64-m%64);
    }

    public static void main(String[] args) {
        final long n = 1;
        for (int i = 0; i <= 64; i++) {

            System.out.println(i + ":" + leftCircularShift(n,i) + ", " + rightCircularShift(n,i));
        }
    }
}

