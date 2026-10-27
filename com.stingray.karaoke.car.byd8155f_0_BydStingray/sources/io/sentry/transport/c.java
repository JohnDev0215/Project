package io.sentry.transport;

import h8.AbstractC1387d;
import io.sentry.A;
import io.sentry.AbstractC1453b1;
import io.sentry.C1459d1;
import io.sentry.C1474i1;
import io.sentry.C1484m;
import io.sentry.C1489n1;
import io.sentry.C1523x;
import io.sentry.EnumC1469h;
import io.sentry.EnumC1495p1;
import io.sentry.F1;
import io.sentry.InterfaceC1456c1;
import io.sentry.K;
import io.sentry.e2;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class c implements f {
    public final l l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final io.sentry.cache.d f16892m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final F1 f16893n;
    public final m o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final g f16894p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final e f16895q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public volatile b f16896r;

    /* JADX WARN: Type inference failed for: r3v0, types: [io.sentry.transport.a] */
    public c(F1 f12, m mVar, g gVar, io.sentry.internal.debugmeta.c cVar) {
        int maxQueueSize = f12.getMaxQueueSize();
        final io.sentry.cache.d envelopeDiskCache = f12.getEnvelopeDiskCache();
        final K logger = f12.getLogger();
        InterfaceC1456c1 dateProvider = f12.getDateProvider();
        l lVar = new l(maxQueueSize, new A(6), new RejectedExecutionHandler() { // from class: io.sentry.transport.a
            @Override // java.util.concurrent.RejectedExecutionHandler
            public final void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
                if (runnable instanceof b) {
                    b bVar = (b) runnable;
                    boolean zH = AbstractC1387d.h(bVar.f16889m, io.sentry.hints.d.class);
                    C1523x c1523x = bVar.f16889m;
                    if (!zH) {
                        envelopeDiskCache.g(bVar.l, c1523x);
                    }
                    Object objG = AbstractC1387d.g(c1523x);
                    if (io.sentry.hints.j.class.isInstance(AbstractC1387d.g(c1523x)) && objG != null) {
                        ((io.sentry.hints.j) objG).b(false);
                    }
                    Object objG2 = AbstractC1387d.g(c1523x);
                    if (io.sentry.hints.g.class.isInstance(AbstractC1387d.g(c1523x)) && objG2 != null) {
                        ((io.sentry.hints.g) objG2).e(true);
                    }
                    logger.k(EnumC1495p1.WARNING, "Envelope rejected", new Object[0]);
                }
            }
        }, logger, dateProvider);
        e eVar = new e(f12, cVar, mVar);
        this.f16896r = null;
        this.l = lVar;
        io.sentry.cache.d envelopeDiskCache2 = f12.getEnvelopeDiskCache();
        h6.e.q(envelopeDiskCache2, "envelopeCache is required");
        this.f16892m = envelopeDiskCache2;
        this.f16893n = f12;
        this.o = mVar;
        h6.e.q(gVar, "transportGate is required");
        this.f16894p = gVar;
        this.f16895q = eVar;
    }

    @Override // io.sentry.transport.f
    public final void a(boolean z6) {
        long flushTimeoutMillis;
        this.o.close();
        this.l.shutdown();
        this.f16893n.getLogger().k(EnumC1495p1.DEBUG, "Shutting down", new Object[0]);
        if (z6) {
            flushTimeoutMillis = 0;
        } else {
            try {
                flushTimeoutMillis = this.f16893n.getFlushTimeoutMillis();
            } catch (InterruptedException unused) {
                this.f16893n.getLogger().k(EnumC1495p1.DEBUG, "Thread interrupted while closing the connection.", new Object[0]);
                Thread.currentThread().interrupt();
                return;
            }
        }
        if (this.l.awaitTermination(flushTimeoutMillis, TimeUnit.MILLISECONDS)) {
            return;
        }
        this.f16893n.getLogger().k(EnumC1495p1.WARNING, "Failed to shutdown the async connection async sender  within " + flushTimeoutMillis + " ms. Trying to force it now.", new Object[0]);
        this.l.shutdownNow();
        if (this.f16896r != null) {
            this.l.getRejectedExecutionHandler().rejectedExecution(this.f16896r, this.l);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        a(false);
    }

    @Override // io.sentry.transport.f
    public final m d() {
        return this.o;
    }

    @Override // io.sentry.transport.f
    public final boolean f() {
        boolean z6;
        m mVar = this.o;
        mVar.getClass();
        mVar.l.getClass();
        Date date = new Date(System.currentTimeMillis());
        ConcurrentHashMap concurrentHashMap = mVar.f16908n;
        Iterator it = concurrentHashMap.keySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                z6 = false;
                break;
            }
            Date date2 = (Date) concurrentHashMap.get((EnumC1469h) it.next());
            if (date2 != null && !date.after(date2)) {
                z6 = true;
                break;
            }
        }
        l lVar = this.l;
        AbstractC1453b1 abstractC1453b1 = lVar.f16904m;
        return (z6 || (abstractC1453b1 != null && (lVar.o.a().b(abstractC1453b1) > 2000000000L ? 1 : (lVar.o.a().b(abstractC1453b1) == 2000000000L ? 0 : -1)) < 0)) ? false : true;
    }

    @Override // io.sentry.transport.f
    public final void h(long j3) {
        l lVar = this.l;
        lVar.getClass();
        try {
            e7.f fVar = lVar.f16906p;
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            fVar.getClass();
            ((n) fVar.f14480m).tryAcquireSharedNanos(1, timeUnit.toNanos(j3));
        } catch (InterruptedException e10) {
            lVar.f16905n.s(EnumC1495p1.ERROR, "Failed to wait till idle", e10);
            Thread.currentThread().interrupt();
        }
    }

    @Override // io.sentry.transport.f
    public final void n(C1489n1 c1489n1, C1523x c1523x) {
        boolean z6;
        io.sentry.cache.d dVar;
        EnumC1469h enumC1469h;
        boolean zIsInstance = io.sentry.hints.d.class.isInstance(AbstractC1387d.g(c1523x));
        F1 f12 = this.f16893n;
        io.sentry.cache.d dVar2 = this.f16892m;
        if (zIsInstance) {
            dVar = h.l;
            f12.getLogger().k(EnumC1495p1.DEBUG, "Captured Envelope is already cached", new Object[0]);
            z6 = true;
        } else {
            z6 = false;
            dVar = dVar2;
        }
        m mVar = this.o;
        mVar.getClass();
        Collection<C1474i1> collection = (Collection) c1489n1.f16625n;
        Iterator it = collection.iterator();
        C1489n1 c1489n1F = null;
        ArrayList arrayList = null;
        while (true) {
            boolean zHasNext = it.hasNext();
            F1 f13 = mVar.f16907m;
            if (!zHasNext) {
                if (arrayList != null) {
                    f13.getLogger().k(EnumC1495p1.INFO, "%d items will be dropped due rate limiting.", Integer.valueOf(arrayList.size()));
                    ArrayList arrayList2 = new ArrayList();
                    for (C1474i1 c1474i1 : collection) {
                        if (!arrayList.contains(c1474i1)) {
                            arrayList2.add(c1474i1);
                        }
                    }
                    if (arrayList2.isEmpty()) {
                        f13.getLogger().k(EnumC1495p1.INFO, "Envelope discarded due all items rate limited.", new Object[0]);
                        Object objG = AbstractC1387d.g(c1523x);
                        if (io.sentry.hints.j.class.isInstance(AbstractC1387d.g(c1523x)) && objG != null) {
                            ((io.sentry.hints.j) objG).b(false);
                        }
                        Object objG2 = AbstractC1387d.g(c1523x);
                        if (io.sentry.hints.g.class.isInstance(AbstractC1387d.g(c1523x)) && objG2 != null) {
                            ((io.sentry.hints.g) objG2).e(false);
                        }
                    } else {
                        c1489n1F = new C1489n1((C1459d1) c1489n1.f16624m, arrayList2);
                    }
                } else {
                    c1489n1F = c1489n1;
                }
                if (c1489n1F == null) {
                    if (z6) {
                        dVar2.p(c1489n1);
                        return;
                    }
                    return;
                }
                if (e2.class.isInstance(AbstractC1387d.g(c1523x))) {
                    c1489n1F = f12.getClientReportRecorder().f(c1489n1F);
                }
                Future futureSubmit = this.l.submit(new b(this, c1489n1F, c1523x, dVar));
                if (futureSubmit != null && futureSubmit.isCancelled()) {
                    f12.getClientReportRecorder().A(io.sentry.clientreport.d.QUEUE_OVERFLOW, c1489n1F);
                    return;
                }
                Object objG3 = AbstractC1387d.g(c1523x);
                if (!C1484m.class.isInstance(AbstractC1387d.g(c1523x)) || objG3 == null) {
                    return;
                }
                C1484m c1484m = (C1484m) objG3;
                c1484m.f16613r.add(c1484m.f16612q);
                f12.getLogger().k(EnumC1495p1.DEBUG, "Envelope enqueued", new Object[0]);
                return;
            }
            C1474i1 c1474i12 = (C1474i1) it.next();
            String itemType = c1474i12.f16555a.f16585n.getItemType();
            itemType.getClass();
            switch (itemType) {
                case "attachment":
                    enumC1469h = EnumC1469h.Attachment;
                    break;
                case "replay_video":
                    enumC1469h = EnumC1469h.Replay;
                    break;
                case "statsd":
                    enumC1469h = EnumC1469h.MetricBucket;
                    break;
                case "profile":
                    enumC1469h = EnumC1469h.Profile;
                    break;
                case "event":
                    enumC1469h = EnumC1469h.Error;
                    break;
                case "check_in":
                    enumC1469h = EnumC1469h.Monitor;
                    break;
                case "session":
                    enumC1469h = EnumC1469h.Session;
                    break;
                case "transaction":
                    enumC1469h = EnumC1469h.Transaction;
                    break;
                default:
                    enumC1469h = EnumC1469h.Unknown;
                    break;
            }
            if (mVar.g(enumC1469h)) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(c1474i12);
                f13.getClientReportRecorder().o(io.sentry.clientreport.d.RATELIMIT_BACKOFF, c1474i12);
            }
        }
    }
}
