package b.a.d.f;

/* JADX INFO: compiled from: ClickChecker.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile long f358a = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Object f359b = new Object();

    public static boolean a(long j) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        synchronized (f359b) {
            if (Math.abs(jCurrentTimeMillis - f358a) <= j) {
                return true;
            }
            f358a = jCurrentTimeMillis;
            return false;
        }
    }
}
