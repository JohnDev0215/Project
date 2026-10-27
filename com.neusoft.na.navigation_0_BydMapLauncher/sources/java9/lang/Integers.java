package java9.lang;

/* JADX INFO: loaded from: classes3.dex */
public final class Integers {
    public static int compare(int i, int i2) {
        if (i < i2) {
            return -1;
        }
        return i == i2 ? 0 : 1;
    }

    public static int hashCode(int i) {
        return i;
    }

    public static int sum(int i, int i2) {
        return i + i2;
    }

    public static long toUnsignedLong(int i) {
        return ((long) i) & 4294967295L;
    }

    public static int divideUnsigned(int i, int i2) {
        return (int) (toUnsignedLong(i) / toUnsignedLong(i2));
    }

    public static int remainderUnsigned(int i, int i2) {
        return (int) (toUnsignedLong(i) % toUnsignedLong(i2));
    }

    public static int compareUnsigned(int i, int i2) {
        return compare(i - Integer.MIN_VALUE, i2 - Integer.MIN_VALUE);
    }

    public static int max(int i, int i2) {
        return Math.max(i, i2);
    }

    public static int min(int i, int i2) {
        return Math.min(i, i2);
    }

    private Integers() {
    }
}
