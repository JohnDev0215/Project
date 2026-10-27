package io.sentry.transport;

import io.sentry.EnumC1469h;
import io.sentry.F1;
import io.sentry.android.replay.ReplayIntegration;
import java.io.Closeable;
import java.util.Date;
import java.util.Timer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class m implements Closeable {
    public final d l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final F1 f16907m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final ConcurrentHashMap f16908n;
    public final CopyOnWriteArrayList o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Timer f16909p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final Object f16910q;

    public m(F1 f12) {
        d dVar = d.f16897a;
        this.f16908n = new ConcurrentHashMap();
        this.o = new CopyOnWriteArrayList();
        this.f16909p = null;
        this.f16910q = new Object();
        this.l = dVar;
        this.f16907m = f12;
    }

    public final void b(EnumC1469h enumC1469h, Date date) {
        Date date2 = (Date) this.f16908n.get(enumC1469h);
        if (date2 == null || date.after(date2)) {
            this.f16908n.put(enumC1469h, date);
            i();
            synchronized (this.f16910q) {
                try {
                    if (this.f16909p == null) {
                        this.f16909p = new Timer(true);
                    }
                    this.f16909p.schedule(new H5.f(this, 4), date);
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        synchronized (this.f16910q) {
            try {
                Timer timer = this.f16909p;
                if (timer != null) {
                    timer.cancel();
                    this.f16909p = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        this.o.clear();
    }

    public final boolean g(EnumC1469h enumC1469h) {
        Date date;
        this.l.getClass();
        Date date2 = new Date(System.currentTimeMillis());
        ConcurrentHashMap concurrentHashMap = this.f16908n;
        Date date3 = (Date) concurrentHashMap.get(EnumC1469h.All);
        if (date3 != null && !date2.after(date3)) {
            return true;
        }
        if (EnumC1469h.Unknown.equals(enumC1469h) || (date = (Date) concurrentHashMap.get(enumC1469h)) == null) {
            return false;
        }
        return !date2.after(date);
    }

    public final void i() {
        for (ReplayIntegration replayIntegration : this.o) {
            replayIntegration.getClass();
            if (replayIntegration.f16324v instanceof io.sentry.android.replay.capture.o) {
                if (g(EnumC1469h.All) || g(EnumC1469h.Replay)) {
                    replayIntegration.b();
                } else {
                    replayIntegration.j();
                }
            }
        }
    }
}
