package io.sentry;

import java.io.Closeable;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: renamed from: io.sentry.s0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class RunnableC1509s0 implements L, Runnable, Closeable {
    public final K l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final B8.t f16879m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final InterfaceC1456c1 f16880n;
    public volatile C1524x0 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public volatile boolean f16881p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final ConcurrentSkipListMap f16882q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final AtomicInteger f16883r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final int f16884s;

    static {
        Charset.forName("UTF-8");
    }

    public RunnableC1509s0(F1 f12, B8.t tVar) {
        K logger = f12.getLogger();
        InterfaceC1456c1 dateProvider = f12.getDateProvider();
        f12.getBeforeEmitMetricCallback();
        C1524x0 c1524x0 = C1524x0.o;
        this.f16881p = false;
        this.f16882q = new ConcurrentSkipListMap();
        this.f16883r = new AtomicInteger();
        this.f16879m = tVar;
        this.l = logger;
        this.f16880n = dateProvider;
        this.f16884s = 100000;
        this.o = c1524x0;
    }

    public final void b(boolean z6) {
        Set<Long> setKeySet;
        if (!z6) {
            if (this.f16883r.get() + this.f16882q.size() >= this.f16884s) {
                this.l.k(EnumC1495p1.INFO, "Metrics: total weight exceeded, flushing all buckets", new Object[0]);
                z6 = true;
            }
        }
        ConcurrentSkipListMap concurrentSkipListMap = this.f16882q;
        if (z6) {
            setKeySet = concurrentSkipListMap.keySet();
        } else {
            long millis = (TimeUnit.NANOSECONDS.toMillis(this.f16880n.a().d()) - 10000) - io.sentry.metrics.c.f16618a;
            long j3 = ((millis / 1000) / 10) * 10;
            if (millis < 0) {
                j3--;
            }
            setKeySet = concurrentSkipListMap.headMap((Object) Long.valueOf(j3), true).keySet();
        }
        if (setKeySet.isEmpty()) {
            this.l.k(EnumC1495p1.DEBUG, "Metrics: nothing to flush", new Object[0]);
            return;
        }
        this.l.k(EnumC1495p1.DEBUG, "Metrics: flushing " + setKeySet.size() + " buckets", new Object[0]);
        HashMap map = new HashMap();
        int size = 0;
        for (Long l : setKeySet) {
            l.getClass();
            Map map2 = (Map) this.f16882q.remove(l);
            if (map2 != null) {
                synchronized (map2) {
                    try {
                        Iterator it = map2.values().iterator();
                        if (it.hasNext()) {
                            if (it.next() != null) {
                                throw new ClassCastException();
                            }
                            throw null;
                        }
                        this.f16883r.addAndGet(0);
                        size += map2.size();
                        map.put(l, map2);
                    } finally {
                    }
                }
            }
        }
        if (size == 0) {
            this.l.k(EnumC1495p1.DEBUG, "Metrics: only empty buckets found", new Object[0]);
            return;
        }
        this.l.k(EnumC1495p1.DEBUG, "Metrics: capturing metrics", new Object[0]);
        B8.t tVar = this.f16879m;
        io.sentry.metrics.a aVar = new io.sentry.metrics.a(map);
        tVar.getClass();
        Charset charset = C1474i1.d;
        io.sentry.internal.debugmeta.c cVar = new io.sentry.internal.debugmeta.c(new CallableC1529z(aVar, 1), 24);
        tVar.D(new C1489n1(new C1459d1(new io.sentry.protocol.t((UUID) null), ((F1) tVar.f808n).getSdkVersion(), null), Collections.singleton(new C1474i1(new C1477j1(EnumC1492o1.Statsd, new CallableC1465f1(cVar, 10), "application/octet-stream", (String) null, (String) null), new CallableC1465f1(cVar, 11)))), null);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        synchronized (this) {
            this.f16881p = true;
            this.o.getClass();
        }
        b(true);
    }

    @Override // java.lang.Runnable
    public final void run() {
        b(false);
        synchronized (this) {
            try {
                if (!this.f16881p && !this.f16882q.isEmpty()) {
                    this.o.r(this, 5000L);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
