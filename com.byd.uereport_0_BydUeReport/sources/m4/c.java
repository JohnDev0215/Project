package m4;

import android.view.View;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: ClickChecker.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile long f6369a = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static ConcurrentHashMap<Integer, Long> f6370b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static b f6371c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Object f6372d = new Object();

    /* JADX INFO: compiled from: ClickChecker.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f6373a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public long f6374b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f6375c;

        public b() {
        }
    }

    public static boolean a(long j8) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        synchronized (f6372d) {
            if (Math.abs(jCurrentTimeMillis - f6369a) <= j8) {
                return true;
            }
            f6369a = jCurrentTimeMillis;
            return false;
        }
    }

    public static boolean b(View view, long j8, int i8) {
        if (view == null) {
            x3.a.a("ClickChecker", "checkViewClickedTimes true");
            return true;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        b bVar = f6371c;
        if (bVar == null || bVar.f6373a != view.getId()) {
            b bVar2 = new b();
            f6371c = bVar2;
            bVar2.f6374b = jCurrentTimeMillis;
            bVar2.f6375c = 1;
            bVar2.f6373a = view.getId();
            x3.a.a("ClickChecker", "checkViewClickedTimes false");
            return false;
        }
        b bVar3 = f6371c;
        int i9 = bVar3.f6375c + 1;
        bVar3.f6375c = i9;
        if (i9 < i8) {
            x3.a.a("ClickChecker", "checkViewClickedTimes false");
            return false;
        }
        if (Math.abs(jCurrentTimeMillis - bVar3.f6374b) <= j8) {
            f6371c = null;
            x3.a.a("ClickChecker", "checkViewClickedTimes true");
            return true;
        }
        b bVar4 = f6371c;
        bVar4.f6374b = jCurrentTimeMillis;
        bVar4.f6375c = 1;
        x3.a.a("ClickChecker", "checkViewClickedTimes false");
        return false;
    }

    public static boolean c(View view, long j8) {
        if (view == null) {
            x3.a.b("ClickChecker", "checkViewMutiClick----v==null");
            return true;
        }
        if (f6370b == null) {
            f6370b = new ConcurrentHashMap<>();
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        Long l8 = f6370b.get(Integer.valueOf(view.getId()));
        if (l8 == null) {
            f6370b.put(Integer.valueOf(view.getId()), Long.valueOf(jCurrentTimeMillis));
            return false;
        }
        if (Math.abs(jCurrentTimeMillis - l8.longValue()) > j8) {
            f6370b.put(Integer.valueOf(view.getId()), Long.valueOf(jCurrentTimeMillis));
            return false;
        }
        x3.a.b("ClickChecker", "checkViewMutiClick----return true --> not response");
        return true;
    }
}
