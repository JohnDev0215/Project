package k2;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: compiled from: LruCache.java */
/* JADX INFO: loaded from: classes.dex */
public class g<T, Y> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Map<T, Y> f7250a = new LinkedHashMap(100, 0.75f, true);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final long f7251b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private long f7252c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private long f7253d;

    public g(long j7) {
        this.f7251b = j7;
        this.f7252c = j7;
    }

    private void f() {
        m(this.f7252c);
    }

    public void b() {
        m(0L);
    }

    public synchronized Y g(T t6) {
        return this.f7250a.get(t6);
    }

    public synchronized long h() {
        return this.f7252c;
    }

    protected int i(Y y6) {
        return 1;
    }

    protected void j(T t6, Y y6) {
    }

    public synchronized Y k(T t6, Y y6) {
        long jI = i(y6);
        if (jI >= this.f7252c) {
            j(t6, y6);
            return null;
        }
        if (y6 != null) {
            this.f7253d += jI;
        }
        Y yPut = this.f7250a.put(t6, y6);
        if (yPut != null) {
            this.f7253d -= (long) i(yPut);
            if (!yPut.equals(y6)) {
                j(t6, yPut);
            }
        }
        f();
        return yPut;
    }

    public synchronized Y l(T t6) {
        Y yRemove;
        yRemove = this.f7250a.remove(t6);
        if (yRemove != null) {
            this.f7253d -= (long) i(yRemove);
        }
        return yRemove;
    }

    protected synchronized void m(long j7) {
        while (this.f7253d > j7) {
            Iterator<Map.Entry<T, Y>> it = this.f7250a.entrySet().iterator();
            Map.Entry<T, Y> next = it.next();
            Y value = next.getValue();
            this.f7253d -= (long) i(value);
            T key = next.getKey();
            it.remove();
            j(key, value);
        }
    }
}
