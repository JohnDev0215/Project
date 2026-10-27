package androidx.leanback.util;

/* JADX INFO: loaded from: classes3.dex */
public final class MathUtil {
    private MathUtil() {
    }

    public static int safeLongToInt(long numLong) {
        if (((int) numLong) != numLong) {
            throw new ArithmeticException("Input overflows int.\n");
        }
        return (int) numLong;
    }
}
