package io.sentry;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: renamed from: io.sentry.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1478k implements d2 {
    public final ArrayList o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final ArrayList f16591p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final boolean f16592q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final F1 f16593r;
    public final Object l = new Object();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public volatile Timer f16589m = null;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final ConcurrentHashMap f16590n = new ConcurrentHashMap();

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final AtomicBoolean f16594s = new AtomicBoolean(false);

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public long f16595t = 0;

    public C1478k(F1 f12) {
        boolean z6 = false;
        h6.e.q(f12, "The options object is required.");
        this.f16593r = f12;
        this.o = new ArrayList();
        this.f16591p = new ArrayList();
        for (N n10 : f12.getPerformanceCollectors()) {
            if (n10 instanceof P) {
                this.o.add((P) n10);
            }
            if (n10 instanceof O) {
                this.f16591p.add((O) n10);
            }
        }
        if (this.o.isEmpty() && this.f16591p.isEmpty()) {
            z6 = true;
        }
        this.f16592q = z6;
    }

    @Override // io.sentry.d2
    public final void a(S1 s12) {
        Iterator it = this.f16591p.iterator();
        while (it.hasNext()) {
            ((io.sentry.android.core.a0) ((O) it.next())).f(s12);
        }
    }

    @Override // io.sentry.d2
    public final void c(P1 p12) {
        if (this.f16592q) {
            this.f16593r.getLogger().k(EnumC1495p1.INFO, "No collector found. Performance stats will not be captured during transactions.", new Object[0]);
            return;
        }
        Iterator it = this.f16591p.iterator();
        while (it.hasNext()) {
            ((io.sentry.android.core.a0) ((O) it.next())).f(p12);
        }
        if (!this.f16590n.containsKey(p12.f15910a.toString())) {
            this.f16590n.put(p12.f15910a.toString(), new ArrayList());
            try {
                this.f16593r.getExecutorService().r(new A2.m(this, 7, p12), 30000L);
            } catch (RejectedExecutionException e10) {
                this.f16593r.getLogger().s(EnumC1495p1.ERROR, "Failed to call the executor. Performance collector will not be automatically finished. Did you call Sentry.close()?", e10);
            }
        }
        if (this.f16594s.getAndSet(true)) {
            return;
        }
        synchronized (this.l) {
            try {
                if (this.f16589m == null) {
                    this.f16589m = new Timer(true);
                }
                this.f16589m.schedule(new C1475j(this, 0), 0L);
                this.f16589m.scheduleAtFixedRate(new C1475j(this, 1), 100L, 100L);
            } finally {
            }
        }
    }

    @Override // io.sentry.d2
    public final void close() {
        this.f16593r.getLogger().k(EnumC1495p1.DEBUG, "stop collecting all performance info for transactions", new Object[0]);
        this.f16590n.clear();
        Iterator it = this.f16591p.iterator();
        while (it.hasNext()) {
            ((io.sentry.android.core.a0) ((O) it.next())).d();
        }
        if (this.f16594s.getAndSet(false)) {
            synchronized (this.l) {
                try {
                    if (this.f16589m != null) {
                        this.f16589m.cancel();
                        this.f16589m = null;
                    }
                } finally {
                }
            }
        }
    }

    @Override // io.sentry.d2
    public final void h(S1 s12) {
        Iterator it = this.f16591p.iterator();
        while (it.hasNext()) {
            ((io.sentry.android.core.a0) ((O) it.next())).e(s12);
        }
    }

    @Override // io.sentry.d2
    public final List i(V v10) {
        this.f16593r.getLogger().k(EnumC1495p1.DEBUG, "stop collecting performance info for transactions %s (%s)", v10.getName(), v10.p().l.toString());
        ConcurrentHashMap concurrentHashMap = this.f16590n;
        List list = (List) concurrentHashMap.remove(v10.h().toString());
        Iterator it = this.f16591p.iterator();
        while (it.hasNext()) {
            ((io.sentry.android.core.a0) ((O) it.next())).e(v10);
        }
        if (concurrentHashMap.isEmpty()) {
            close();
        }
        return list;
    }
}
