package b.b.m.g;

import android.view.View;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: ClickChecker.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile long f2170a = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static ConcurrentHashMap<Integer, Long> f2171b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static b f2172c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Object f2173d = new Object();

    /* JADX INFO: compiled from: ClickChecker.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f2174a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public long f2175b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f2176c;

        public b() {
        }
    }

    public static boolean a(long j) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        synchronized (f2173d) {
            if (Math.abs(jCurrentTimeMillis - f2170a) <= j) {
                return true;
            }
            f2170a = jCurrentTimeMillis;
            return false;
        }
    }

    public static boolean b(View view, long j, int i) {
        if (view == null) {
            b.b.m.c.a.a("ClickChecker", "checkViewClickedTimes true");
            return true;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        b bVar = f2172c;
        if (bVar == null || bVar.f2174a != view.getId()) {
            b bVar2 = new b();
            f2172c = bVar2;
            bVar2.f2175b = jCurrentTimeMillis;
            bVar2.f2176c = 1;
            bVar2.f2174a = view.getId();
            b.b.m.c.a.a("ClickChecker", "checkViewClickedTimes false");
            return false;
        }
        b bVar3 = f2172c;
        int i2 = bVar3.f2176c + 1;
        bVar3.f2176c = i2;
        if (i2 < i) {
            b.b.m.c.a.a("ClickChecker", "checkViewClickedTimes false");
            return false;
        }
        if (Math.abs(jCurrentTimeMillis - bVar3.f2175b) <= j) {
            f2172c = null;
            b.b.m.c.a.a("ClickChecker", "checkViewClickedTimes true");
            return true;
        }
        b bVar4 = f2172c;
        bVar4.f2175b = jCurrentTimeMillis;
        bVar4.f2176c = 1;
        b.b.m.c.a.a("ClickChecker", "checkViewClickedTimes false");
        return false;
    }

    public static boolean c(View view, long j) {
        if (view == null) {
            b.b.m.c.a.c("ClickChecker", "checkViewMutiClick----v==null");
            return true;
        }
        if (f2171b == null) {
            f2171b = new ConcurrentHashMap<>();
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        Long l = f2171b.get(Integer.valueOf(view.getId()));
        if (l == null) {
            f2171b.put(Integer.valueOf(view.getId()), Long.valueOf(jCurrentTimeMillis));
            return false;
        }
        if (Math.abs(jCurrentTimeMillis - l.longValue()) > j) {
            f2171b.put(Integer.valueOf(view.getId()), Long.valueOf(jCurrentTimeMillis));
            return false;
        }
        b.b.m.c.a.c("ClickChecker", "checkViewMutiClick----return true --> not response");
        return true;
    }
}
