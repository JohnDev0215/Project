package io.sentry;

import h3.C1350a;
import h8.AbstractC1387d;
import io.sentry.protocol.C1498c;
import java.io.Closeable;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import n7.AbstractC1985B;

/* JADX INFO: loaded from: classes.dex */
public final class D implements J {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final F1 f15825a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public volatile boolean f15826b;
    public final C1489n1 c;
    public final L1 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Map f15827e = Collections.synchronizedMap(new WeakHashMap());

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final d2 f15828f;

    public D(F1 f12, C1489n1 c1489n1) {
        h6.e.q(f12, "SentryOptions is required.");
        if (f12.getDsn() == null || f12.getDsn().isEmpty()) {
            throw new IllegalArgumentException("Hub requires a DSN to be instantiated. Considering using the NoOpHub if no DSN is available.");
        }
        this.f15825a = f12;
        this.d = new L1(f12, 1);
        this.c = c1489n1;
        io.sentry.protocol.t tVar = io.sentry.protocol.t.f16774m;
        this.f15828f = f12.getTransactionPerformanceCollector();
        this.f15826b = true;
    }

    @Override // io.sentry.J
    public final void a(boolean z6) {
        if (!this.f15826b) {
            this.f15825a.getLogger().k(EnumC1495p1.WARNING, "Instance is disabled and this 'close' call is a no-op.", new Object[0]);
            return;
        }
        try {
            for (Z z10 : this.f15825a.getIntegrations()) {
                if (z10 instanceof Closeable) {
                    try {
                        ((Closeable) z10).close();
                    } catch (IOException e10) {
                        this.f15825a.getLogger().k(EnumC1495p1.WARNING, "Failed to close the integration {}.", z10, e10);
                    }
                }
            }
            l(new C1350a(6));
            this.f15825a.getTransactionProfiler().close();
            this.f15825a.getTransactionPerformanceCollector().close();
            S executorService = this.f15825a.getExecutorService();
            if (z6) {
                executorService.submit(new A2.m(this, 8, executorService));
            } else {
                executorService.l(this.f15825a.getShutdownTimeoutMillis());
            }
            this.c.C().f15992b.J(z6);
        } catch (Throwable th) {
            this.f15825a.getLogger().s(EnumC1495p1.ERROR, "Error while closing the Hub.", th);
        }
        this.f15826b = false;
    }

    @Override // io.sentry.J
    public final U b() {
        S1 s1C;
        if (this.f15826b) {
            V v10 = this.c.C().c.f15893a;
            return (v10 == null || (s1C = v10.c()) == null) ? v10 : s1C;
        }
        this.f15825a.getLogger().k(EnumC1495p1.WARNING, "Instance is disabled and this 'getSpan' call is a no-op.", new Object[0]);
        return null;
    }

    public final void c(C1480k1 c1480k1) {
        String str;
        U u4;
        if (this.f15825a.isTracingEnabled()) {
            Throwable cause = c1480k1.f15985u;
            if ((cause instanceof io.sentry.exception.a ? ((io.sentry.exception.a) cause).f16529m : cause) != null) {
                if (cause instanceof io.sentry.exception.a) {
                    cause = ((io.sentry.exception.a) cause).f16529m;
                }
                h6.e.q(cause, "throwable cannot be null");
                while (cause.getCause() != null && cause.getCause() != cause) {
                    cause = cause.getCause();
                }
                io.sentry.util.e eVar = (io.sentry.util.e) this.f15827e.get(cause);
                if (eVar != null) {
                    WeakReference weakReference = eVar.f16919a;
                    C1498c c1498c = c1480k1.f15978m;
                    if (c1498c.b() == null && (u4 = (U) weakReference.get()) != null) {
                        c1498c.e(u4.p());
                    }
                    if (c1480k1.f16602G != null || (str = eVar.f16920b) == null) {
                        return;
                    }
                    c1480k1.f16602G = str;
                }
            }
        }
    }

    @Override // io.sentry.J
    public final io.sentry.transport.m d() {
        return ((io.sentry.transport.f) this.c.C().f15992b.o).d();
    }

    @Override // io.sentry.J
    public final void e(C1457d c1457d) {
        k(c1457d, new C1523x());
    }

    @Override // io.sentry.J
    public final boolean f() {
        return ((io.sentry.transport.f) this.c.C().f15992b.o).f();
    }

    @Override // io.sentry.J
    public final void h(long j3) {
        if (!this.f15826b) {
            this.f15825a.getLogger().k(EnumC1495p1.WARNING, "Instance is disabled and this 'flush' call is a no-op.", new Object[0]);
            return;
        }
        try {
            ((io.sentry.transport.f) this.c.C().f15992b.o).h(j3);
        } catch (Throwable th) {
            this.f15825a.getLogger().s(EnumC1495p1.ERROR, "Error in the 'client.flush'.", th);
        }
    }

    @Override // io.sentry.J
    public final V i() {
        if (this.f15826b) {
            return this.c.C().c.f15893a;
        }
        this.f15825a.getLogger().k(EnumC1495p1.WARNING, "Instance is disabled and this 'getTransaction' call is a no-op.", new Object[0]);
        return null;
    }

    @Override // io.sentry.J
    public final boolean isEnabled() {
        return this.f15826b;
    }

    @Override // io.sentry.J
    public final V j(b2 b2Var, c2 c2Var) {
        V v10;
        boolean z6 = this.f15826b;
        A0 a02 = A0.f15812a;
        if (!z6) {
            this.f15825a.getLogger().k(EnumC1495p1.WARNING, "Instance is disabled and this 'startTransaction' returns a no-op.", new Object[0]);
            v10 = a02;
        } else if (!this.f15825a.getInstrumenter().equals(b2Var.f16480z)) {
            this.f15825a.getLogger().k(EnumC1495p1.DEBUG, "Returning no-op for instrumenter %s as the SDK has been configured to use instrumenter %s", b2Var.f16480z, this.f15825a.getInstrumenter());
            v10 = a02;
        } else if (this.f15825a.isTracingEnabled()) {
            B8.t tVarC = this.d.c(new e7.f(b2Var, 11));
            b2Var.o = tVarC;
            P1 p12 = new P1(b2Var, this, c2Var, this.f15828f);
            v10 = p12;
            if (((Boolean) tVarC.f808n).booleanValue()) {
                v10 = p12;
                if (((Boolean) tVarC.f809p).booleanValue()) {
                    W transactionProfiler = this.f15825a.getTransactionProfiler();
                    if (transactionProfiler.isRunning()) {
                        v10 = p12;
                        if (c2Var.f16484n) {
                            transactionProfiler.d(p12);
                            v10 = p12;
                        }
                    } else {
                        transactionProfiler.start();
                        transactionProfiler.d(p12);
                        v10 = p12;
                    }
                }
            }
        } else {
            this.f15825a.getLogger().k(EnumC1495p1.INFO, "Tracing is disabled and this 'startTransaction' returns a no-op.", new Object[0]);
            v10 = a02;
        }
        return v10;
    }

    @Override // io.sentry.J
    public final void k(C1457d c1457d, C1523x c1523x) {
        if (!this.f15826b) {
            this.f15825a.getLogger().k(EnumC1495p1.WARNING, "Instance is disabled and this 'addBreadcrumb' call is a no-op.", new Object[0]);
            return;
        }
        if (c1457d == null) {
            this.f15825a.getLogger().k(EnumC1495p1.WARNING, "addBreadcrumb called with null parameter.", new Object[0]);
            return;
        }
        N0 n02 = this.c.C().c;
        n02.getClass();
        F1 f12 = n02.f15900j;
        f12.getBeforeBreadcrumb();
        Y1 y12 = n02.f15896f;
        y12.add(c1457d);
        for (Q q4 : f12.getScopeObservers()) {
            q4.e(c1457d);
            q4.h(y12);
        }
    }

    @Override // io.sentry.J
    public final void l(O0 o02) {
        if (!this.f15826b) {
            this.f15825a.getLogger().k(EnumC1495p1.WARNING, "Instance is disabled and this 'configureScope' call is a no-op.", new Object[0]);
            return;
        }
        try {
            o02.i(this.c.C().c);
        } catch (Throwable th) {
            this.f15825a.getLogger().s(EnumC1495p1.ERROR, "Error in the 'configureScope' callback.", th);
        }
    }

    @Override // io.sentry.J
    public final io.sentry.protocol.t m(io.sentry.protocol.A a10, a2 a2Var, C1523x c1523x, G0 g02) {
        io.sentry.protocol.t tVar = io.sentry.protocol.t.f16774m;
        if (!this.f15826b) {
            this.f15825a.getLogger().k(EnumC1495p1.WARNING, "Instance is disabled and this 'captureTransaction' call is a no-op.", new Object[0]);
            return tVar;
        }
        if (a10.f16648C == null) {
            this.f15825a.getLogger().k(EnumC1495p1.WARNING, "Transaction: %s is not finished and this 'captureTransaction' call is a no-op.", a10.l);
            return tVar;
        }
        Boolean bool = Boolean.TRUE;
        T1 t1B = a10.f15978m.b();
        B8.t tVar2 = t1B == null ? null : t1B.o;
        if (bool.equals(Boolean.valueOf(tVar2 != null ? ((Boolean) tVar2.f808n).booleanValue() : false))) {
            try {
                X1 x1C = this.c.C();
                return x1C.f15992b.H(a10, a2Var, x1C.c, c1523x, g02);
            } catch (Throwable th) {
                this.f15825a.getLogger().s(EnumC1495p1.ERROR, "Error while capturing transaction with id: " + a10.l, th);
                return tVar;
            }
        }
        this.f15825a.getLogger().k(EnumC1495p1.DEBUG, "Transaction %s was dropped due to sampling decision.", a10.l);
        if (this.f15825a.getBackpressureMonitor().a() > 0) {
            io.sentry.clientreport.f clientReportRecorder = this.f15825a.getClientReportRecorder();
            io.sentry.clientreport.d dVar = io.sentry.clientreport.d.BACKPRESSURE;
            clientReportRecorder.g(dVar, EnumC1469h.Transaction);
            this.f15825a.getClientReportRecorder().h(dVar, EnumC1469h.Span, a10.f16649D.size() + 1);
            return tVar;
        }
        io.sentry.clientreport.f clientReportRecorder2 = this.f15825a.getClientReportRecorder();
        io.sentry.clientreport.d dVar2 = io.sentry.clientreport.d.SAMPLE_RATE;
        clientReportRecorder2.g(dVar2, EnumC1469h.Transaction);
        this.f15825a.getClientReportRecorder().h(dVar2, EnumC1469h.Span, a10.f16649D.size() + 1);
        return tVar;
    }

    @Override // io.sentry.J
    public final io.sentry.protocol.t n(H1 h12, C1523x c1523x) {
        io.sentry.protocol.t tVar = io.sentry.protocol.t.f16774m;
        if (!this.f15826b) {
            this.f15825a.getLogger().k(EnumC1495p1.WARNING, "Instance is disabled and this 'captureReplay' call is a no-op.", new Object[0]);
            return tVar;
        }
        try {
            X1 x1C = this.c.C();
            return x1C.f15992b.F(h12, x1C.c, c1523x);
        } catch (Throwable th) {
            this.f15825a.getLogger().s(EnumC1495p1.ERROR, "Error while capturing replay", th);
            return tVar;
        }
    }

    @Override // io.sentry.J
    public final void o() {
        R1 r12;
        if (!this.f15826b) {
            this.f15825a.getLogger().k(EnumC1495p1.WARNING, "Instance is disabled and this 'endSession' call is a no-op.", new Object[0]);
            return;
        }
        X1 x1C = this.c.C();
        N0 n02 = x1C.c;
        synchronized (n02.l) {
            try {
                r12 = null;
                if (n02.f15901k != null) {
                    R1 r13 = n02.f15901k;
                    r13.getClass();
                    r13.b(AbstractC1985B.f());
                    R1 r1Clone = n02.f15901k.clone();
                    n02.f15901k = null;
                    r12 = r1Clone;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        if (r12 != null) {
            x1C.f15992b.G(r12, AbstractC1387d.c(new io.sentry.hints.i(24)));
        }
    }

    @Override // io.sentry.J
    public final void p() {
        M.t tVar;
        if (!this.f15826b) {
            this.f15825a.getLogger().k(EnumC1495p1.WARNING, "Instance is disabled and this 'startSession' call is a no-op.", new Object[0]);
            return;
        }
        X1 x1C = this.c.C();
        N0 n02 = x1C.c;
        synchronized (n02.l) {
            try {
                if (n02.f15901k != null) {
                    R1 r12 = n02.f15901k;
                    r12.getClass();
                    r12.b(AbstractC1985B.f());
                }
                R1 r13 = n02.f15901k;
                tVar = null;
                if (n02.f15900j.getRelease() != null) {
                    String distinctId = n02.f15900j.getDistinctId();
                    io.sentry.protocol.E e10 = n02.f15894b;
                    n02.f15901k = new R1(Q1.Ok, AbstractC1985B.f(), AbstractC1985B.f(), 0, distinctId, UUID.randomUUID(), Boolean.TRUE, null, null, e10 != null ? e10.f16658p : null, null, n02.f15900j.getEnvironment(), n02.f15900j.getRelease(), null);
                    tVar = new M.t(n02.f15901k.clone(), r13 != null ? r13.clone() : null, false);
                } else {
                    n02.f15900j.getLogger().k(EnumC1495p1.WARNING, "Release is not set on SentryOptions. Session could not be started", new Object[0]);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        if (tVar == null) {
            this.f15825a.getLogger().k(EnumC1495p1.WARNING, "Session could not be started.", new Object[0]);
            return;
        }
        if (((R1) tVar.l) != null) {
            x1C.f15992b.G((R1) tVar.l, AbstractC1387d.c(new io.sentry.hints.i(24)));
        }
        x1C.f15992b.G((R1) tVar.f4151m, AbstractC1387d.c(new io.sentry.hints.i(0)));
    }

    @Override // io.sentry.J
    public final F1 q() {
        return this.c.C().f15991a;
    }

    @Override // io.sentry.J
    public final io.sentry.protocol.t r(C1489n1 c1489n1, C1523x c1523x) {
        io.sentry.protocol.t tVar = io.sentry.protocol.t.f16774m;
        if (!this.f15826b) {
            this.f15825a.getLogger().k(EnumC1495p1.WARNING, "Instance is disabled and this 'captureEnvelope' call is a no-op.", new Object[0]);
            return tVar;
        }
        try {
            io.sentry.protocol.t tVarD = this.c.C().f15992b.D(c1489n1, c1523x);
            return tVarD != null ? tVarD : tVar;
        } catch (Throwable th) {
            this.f15825a.getLogger().s(EnumC1495p1.ERROR, "Error while capturing envelope.", th);
            return tVar;
        }
    }

    @Override // io.sentry.J
    public final io.sentry.protocol.t s(C1480k1 c1480k1, C1523x c1523x) {
        io.sentry.protocol.t tVar = io.sentry.protocol.t.f16774m;
        if (!this.f15826b) {
            this.f15825a.getLogger().k(EnumC1495p1.WARNING, "Instance is disabled and this 'captureEvent' call is a no-op.", new Object[0]);
            return tVar;
        }
        try {
            c(c1480k1);
            X1 x1C = this.c.C();
            return x1C.f15992b.E(c1480k1, x1C.c, c1523x);
        } catch (Throwable th) {
            this.f15825a.getLogger().s(EnumC1495p1.ERROR, "Error while capturing event with id: " + c1480k1.l, th);
            return tVar;
        }
    }

    @Override // io.sentry.J
    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public final J m3clone() {
        if (!this.f15826b) {
            this.f15825a.getLogger().k(EnumC1495p1.WARNING, "Disabled Hub cloned.", new Object[0]);
        }
        F1 f12 = this.f15825a;
        C1489n1 c1489n1 = this.c;
        C1489n1 c1489n12 = new C1489n1((K) c1489n1.f16625n, new X1((X1) ((LinkedBlockingDeque) c1489n1.f16624m).getLast()));
        Iterator itDescendingIterator = ((LinkedBlockingDeque) c1489n1.f16624m).descendingIterator();
        if (itDescendingIterator.hasNext()) {
            itDescendingIterator.next();
        }
        while (itDescendingIterator.hasNext()) {
            ((LinkedBlockingDeque) c1489n12.f16624m).push(new X1((X1) itDescendingIterator.next()));
        }
        return new D(f12, c1489n12);
    }
}
