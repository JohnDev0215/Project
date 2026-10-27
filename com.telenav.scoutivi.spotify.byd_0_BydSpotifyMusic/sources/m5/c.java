package m5;

/* JADX INFO: compiled from: progressionUtil.kt */
/* JADX INFO: loaded from: classes.dex */
public final class c {
    private static final int a(int i7, int i8, int i9) {
        return c(c(i7, i9) - c(i8, i9), i9);
    }

    public static final int b(int i7, int i8, int i9) {
        if (i9 > 0) {
            return i7 >= i8 ? i8 : i8 - a(i8, i7, i9);
        }
        if (i9 < 0) {
            return i7 <= i8 ? i8 : i8 + a(i7, i8, -i9);
        }
        throw new IllegalArgumentException("Step is zero.");
    }

    private static final int c(int i7, int i8) {
        int i9 = i7 % i8;
        return i9 >= 0 ? i9 : i9 + i8;
    }
}
