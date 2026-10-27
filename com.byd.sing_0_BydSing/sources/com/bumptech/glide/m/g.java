package com.bumptech.glide.m;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: compiled from: LruCache.java */
/* JADX INFO: loaded from: classes.dex */
public class g<T, Y> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Map<T, Y> f3179a = new LinkedHashMap(100, 0.75f, true);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final long f3180b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private long f3181c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private long f3182d;

    public g(long j) {
        this.f3180b = j;
        this.f3181c = j;
    }

    private void f() {
        m(this.f3181c);
    }

    public void b() {
        m(0L);
    }

    public synchronized Y g(T t) {
        return this.f3179a.get(t);
    }

    public synchronized long h() {
        return this.f3181c;
    }

    protected int i(Y y) {
        return 1;
    }

    protected void j(T t, Y y) {
    }

    public synchronized Y k(T t, Y y) {
        long jI = i(y);
        if (jI >= this.f3181c) {
            j(t, y);
            return null;
        }
        if (y != null) {
            this.f3182d += jI;
        }
        Y yPut = this.f3179a.put(t, y);
        if (yPut != null) {
            this.f3182d -= (long) i(yPut);
            if (!yPut.equals(y)) {
                j(t, yPut);
            }
        }
        f();
        return yPut;
    }

    public synchronized Y l(T t) {
        Y yRemove;
        yRemove = this.f3179a.remove(t);
        if (yRemove != null) {
            this.f3182d -= (long) i(yRemove);
        }
        return yRemove;
    }

    protected synchronized void m(long j) {
        while (this.f3182d > j) {
            Iterator<Map.Entry<T, Y>> it = this.f3179a.entrySet().iterator();
            Map.Entry<T, Y> next = it.next();
            Y value = next.getValue();
            this.f3182d -= (long) i(value);
            T key = next.getKey();
            it.remove();
            j(key, value);
        }
    }
}
