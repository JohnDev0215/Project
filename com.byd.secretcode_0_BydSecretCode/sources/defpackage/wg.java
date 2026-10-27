package defpackage;

/* JADX INFO: compiled from: ClickChecker.java */
/* JADX INFO: loaded from: classes.dex */
public final class wg {
    public static volatile long a = -1;
    public static final Object b = new Object();

    public static boolean a(long j) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        synchronized (b) {
            if (Math.abs(jCurrentTimeMillis - a) <= j) {
                return true;
            }
            a = jCurrentTimeMillis;
            return false;
        }
    }
}
