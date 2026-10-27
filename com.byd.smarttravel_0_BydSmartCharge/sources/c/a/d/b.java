package c.a.d;

/* JADX INFO: compiled from: ClickChecker.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static long f1591a = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static long f1592b = -1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static int f1593c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Object f1594d = new Object();

    public static boolean a() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j = jCurrentTimeMillis - f1592b;
        if (Math.abs(j) > 500) {
            f1592b = jCurrentTimeMillis;
            f1593c = 1;
            return false;
        }
        int i = f1593c + 1;
        f1593c = i;
        return i == 2 && Math.abs(j) < 500;
    }

    public static boolean b(long j) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        synchronized (f1594d) {
            if (Math.abs(jCurrentTimeMillis - f1591a) <= j) {
                return true;
            }
            f1591a = jCurrentTimeMillis;
            return false;
        }
    }
}
