package java9.lang;

/* JADX INFO: loaded from: classes3.dex */
public final class Doubles {
    public static double sum(double d, double d2) {
        return d + d2;
    }

    public static int hashCode(double d) {
        long jDoubleToLongBits = Double.doubleToLongBits(d);
        return (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
    }

    public static double max(double d, double d2) {
        return Math.max(d, d2);
    }

    public static double min(double d, double d2) {
        return Math.min(d, d2);
    }

    public static boolean isFinite(double d) {
        return Math.abs(d) <= Double.MAX_VALUE;
    }

    private Doubles() {
    }
}
