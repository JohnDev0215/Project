package kotlin;

/* JADX INFO: compiled from: UnsignedUtils.kt */
/* JADX INFO: loaded from: classes.dex */
public final class m {
    public static final int a(double d2) {
        if (Double.isNaN(d2) || d2 <= b(0)) {
            return 0;
        }
        if (d2 >= b(-1)) {
            return -1;
        }
        double d3 = Integer.MAX_VALUE;
        return d2 <= d3 ? i.a((int) d2) : i.a(i.a((int) (d2 - d3)) + i.a(Integer.MAX_VALUE));
    }

    public static final double b(int i) {
        return ((double) (Integer.MAX_VALUE & i)) + (((double) ((i >>> 31) << 30)) * ((double) 2));
    }
}
