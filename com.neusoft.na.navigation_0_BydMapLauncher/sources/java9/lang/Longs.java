package java9.lang;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes3.dex */
public final class Longs {
    public static int compare(long j, long j2) {
        if (j < j2) {
            return -1;
        }
        return j == j2 ? 0 : 1;
    }

    public static int hashCode(long j) {
        return (int) (j ^ (j >>> 32));
    }

    public static long sum(long j, long j2) {
        return j + j2;
    }

    public static long divideUnsigned(long j, long j2) {
        if (j2 < 0) {
            return compareUnsigned(j, j2) < 0 ? 0L : 1L;
        }
        if (j > 0) {
            return j / j2;
        }
        return toUnsignedBigInteger(j).divide(toUnsignedBigInteger(j2)).longValue();
    }

    private static BigInteger toUnsignedBigInteger(long j) {
        if (j >= 0) {
            return BigInteger.valueOf(j);
        }
        return BigInteger.valueOf(Integers.toUnsignedLong((int) (j >>> 32))).shiftLeft(32).add(BigInteger.valueOf(Integers.toUnsignedLong((int) j)));
    }

    public static int compareUnsigned(long j, long j2) {
        return compare(j - Long.MIN_VALUE, j2 - Long.MIN_VALUE);
    }

    public static long remainderUnsigned(long j, long j2) {
        if (j <= 0 || j2 <= 0) {
            return compareUnsigned(j, j2) < 0 ? j : toUnsignedBigInteger(j).remainder(toUnsignedBigInteger(j2)).longValue();
        }
        return j % j2;
    }

    public static long max(long j, long j2) {
        return Math.max(j, j2);
    }

    public static long min(long j, long j2) {
        return Math.min(j, j2);
    }

    private Longs() {
    }
}
