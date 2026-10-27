package io.sentry;

import io.sentry.protocol.C1498c;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Timer;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class P1 implements V {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final S1 f15911b;
    public final D d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f15912e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public volatile N1 f15914g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public volatile N1 f15915h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public volatile Timer f15916i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Object f15917j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final AtomicBoolean f15918k;
    public final AtomicBoolean l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final A2.c f15919m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final io.sentry.protocol.C f15920n;
    public final Y o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final C1498c f15921p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final d2 f15922q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final c2 f15923r;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final io.sentry.protocol.t f15910a = new io.sentry.protocol.t((UUID) null);
    public final CopyOnWriteArrayList c = new CopyOnWriteArrayList();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public O1 f15913f = O1.c;

    public P1(b2 b2Var, D d, c2 c2Var, d2 d2Var) {
        this.f15916i = null;
        Object obj = new Object();
        this.f15917j = obj;
        this.f15918k = new AtomicBoolean(false);
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        this.l = atomicBoolean;
        this.f15921p = new C1498c();
        this.f15911b = new S1(b2Var, this, d, c2Var.f16483m, c2Var);
        this.f15912e = b2Var.f16476v;
        this.o = b2Var.f16480z;
        this.d = d;
        this.f15922q = d2Var;
        this.f15920n = b2Var.f16477w;
        this.f15923r = c2Var;
        A2.c cVar = b2Var.f16479y;
        if (cVar != null) {
            this.f15919m = cVar;
        } else {
            this.f15919m = new A2.c(d.q().getLogger());
        }
        if (d2Var != null) {
            d2Var.c(this);
        }
        if (c2Var.f16485p == null && c2Var.f16486q == null) {
            return;
        }
        boolean z6 = true;
        this.f15916i = new Timer(true);
        Long l = c2Var.f16486q;
        if (l != null) {
            synchronized (obj) {
                try {
                    if (this.f15916i != null) {
                        C();
                        atomicBoolean.set(true);
                        this.f15915h = new N1(this, 1);
                        this.f15916i.schedule(this.f15915h, l.longValue());
                    }
                } catch (Throwable th) {
                    this.d.q().getLogger().s(EnumC1495p1.WARNING, "Failed to schedule finish timer", th);
                    W1 w1S = s();
                    if (w1S == null) {
                        w1S = W1.DEADLINE_EXCEEDED;
                    }
                    if (this.f15923r.f16485p == null) {
                        z6 = false;
                    }
                    i(w1S, z6, null);
                    this.l.set(false);
                } finally {
                }
            }
        }
        m();
    }

    @Override // io.sentry.U
    public final void A(Object obj, String str) {
        S1 s12 = this.f15911b;
        if (s12.f15947g) {
            this.d.q().getLogger().k(EnumC1495p1.DEBUG, "The transaction is already finished. Data %s cannot be set", str);
        } else {
            s12.A(obj, str);
        }
    }

    @Override // io.sentry.U
    public final AbstractC1453b1 B() {
        return this.f15911b.f15943a;
    }

    public final void C() {
        synchronized (this.f15917j) {
            try {
                if (this.f15915h != null) {
                    this.f15915h.cancel();
                    this.l.set(false);
                    this.f15915h = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void D() {
        synchronized (this.f15917j) {
            try {
                if (this.f15914g != null) {
                    this.f15914g.cancel();
                    this.f15918k.set(false);
                    this.f15914g = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final U E(V1 v12, String str, String str2, AbstractC1453b1 abstractC1453b1, Y y10, B2.P p10) {
        S1 s12 = this.f15911b;
        boolean z6 = s12.f15947g;
        C1530z0 c1530z0 = C1530z0.f16994a;
        if (z6 || !this.o.equals(y10)) {
            return c1530z0;
        }
        CopyOnWriteArrayList copyOnWriteArrayList = this.c;
        int size = copyOnWriteArrayList.size();
        D d = this.d;
        if (size >= d.q().getMaxSpans()) {
            d.q().getLogger().k(EnumC1495p1.WARNING, "Span operation: %s, description: %s dropped due to limit reached. Returning NoOpSpan.", str, str2);
            return c1530z0;
        }
        h6.e.q(v12, "parentSpanId is required");
        h6.e.q(str, "operation is required");
        D();
        S1 s13 = new S1(s12.c.l, v12, this, str, this.d, abstractC1453b1, p10, new M1(this));
        s13.c.f15960q = str2;
        s13.A(String.valueOf(Thread.currentThread().getId()), "thread.id");
        s13.A(d.q().getMainThreadChecker().a() ? "main" : Thread.currentThread().getName(), "thread.name");
        copyOnWriteArrayList.add(s13);
        d2 d2Var = this.f15922q;
        if (d2Var != null) {
            d2Var.a(s13);
        }
        return s13;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00a8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void F(io.sentry.W1 r5, io.sentry.AbstractC1453b1 r6, boolean r7, io.sentry.C1523x r8) {
        /*
            Method dump skipped, instruction units count: 289
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.P1.F(io.sentry.W1, io.sentry.b1, boolean, io.sentry.x):void");
    }

    public final boolean G() {
        ArrayList<S1> arrayList = new ArrayList(this.c);
        if (arrayList.isEmpty()) {
            return true;
        }
        for (S1 s12 : arrayList) {
            if (!s12.f15947g && s12.f15944b == null) {
                return false;
            }
        }
        return true;
    }

    public final U H(String str, String str2, AbstractC1453b1 abstractC1453b1, Y y10, B2.P p10) {
        S1 s12 = this.f15911b;
        boolean z6 = s12.f15947g;
        C1530z0 c1530z0 = C1530z0.f16994a;
        if (z6 || !this.o.equals(y10)) {
            return c1530z0;
        }
        int size = this.c.size();
        D d = this.d;
        if (size >= d.q().getMaxSpans()) {
            d.q().getLogger().k(EnumC1495p1.WARNING, "Span operation: %s, description: %s dropped due to limit reached. Returning NoOpSpan.", str, str2);
            return c1530z0;
        }
        if (s12.f15947g) {
            return c1530z0;
        }
        return s12.d.E(s12.c.f15957m, str, str2, abstractC1453b1, y10, p10);
    }

    public final void I() {
        synchronized (this) {
            try {
                if (this.f15919m.f311b) {
                    AtomicReference atomicReference = new AtomicReference();
                    AtomicReference atomicReference2 = new AtomicReference();
                    this.d.l(new C1458d0(atomicReference, 1, atomicReference2));
                    this.f15919m.p(this, (io.sentry.protocol.E) atomicReference.get(), (io.sentry.protocol.t) atomicReference2.get(), this.d.q(), this.f15911b.c.o);
                    this.f15919m.f311b = false;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // io.sentry.U
    public final String a() {
        return this.f15911b.c.f15960q;
    }

    @Override // io.sentry.U
    public final void b(W1 w1) {
        S1 s12 = this.f15911b;
        if (s12.f15947g) {
            this.d.q().getLogger().k(EnumC1495p1.DEBUG, "The transaction is already finished. Status %s cannot be set", w1 == null ? "null" : w1.name());
        } else {
            s12.c.f15961r = w1;
        }
    }

    @Override // io.sentry.V
    public final S1 c() {
        ArrayList arrayList = new ArrayList(this.c);
        if (arrayList.isEmpty()) {
            return null;
        }
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (!((S1) arrayList.get(size)).f15947g) {
                return (S1) arrayList.get(size);
            }
        }
        return null;
    }

    @Override // io.sentry.U
    public final a2 d() {
        if (!this.d.q().isTraceSampling()) {
            return null;
        }
        I();
        return this.f15919m.q();
    }

    @Override // io.sentry.U
    public final void e(String str) {
        S1 s12 = this.f15911b;
        if (s12.f15947g) {
            this.d.q().getLogger().k(EnumC1495p1.DEBUG, "The transaction is already finished. Description %s cannot be set", str);
        } else {
            s12.c.f15960q = str;
        }
    }

    @Override // io.sentry.U
    public final h3.y f() {
        return this.f15911b.f();
    }

    @Override // io.sentry.U
    public final boolean g() {
        return this.f15911b.f15947g;
    }

    @Override // io.sentry.V
    public final String getName() {
        return this.f15912e;
    }

    @Override // io.sentry.V
    public final io.sentry.protocol.t h() {
        return this.f15910a;
    }

    @Override // io.sentry.V
    public final void i(W1 w1, boolean z6, C1523x c1523x) {
        if (this.f15911b.f15947g) {
            return;
        }
        AbstractC1453b1 abstractC1453b1A = this.d.q().getDateProvider().a();
        CopyOnWriteArrayList copyOnWriteArrayList = this.c;
        ListIterator listIterator = copyOnWriteArrayList.listIterator(copyOnWriteArrayList.size());
        while (listIterator.hasPrevious()) {
            S1 s12 = (S1) listIterator.previous();
            s12.f15950j = null;
            s12.v(w1, abstractC1453b1A);
        }
        F(w1, abstractC1453b1A, z6, c1523x);
    }

    @Override // io.sentry.U
    public final U j(String str) {
        return x(str, null);
    }

    @Override // io.sentry.U
    public final boolean k(AbstractC1453b1 abstractC1453b1) {
        return this.f15911b.k(abstractC1453b1);
    }

    @Override // io.sentry.U
    public final void l(Number number, String str) {
        this.f15911b.l(number, str);
    }

    @Override // io.sentry.V
    public final void m() {
        Long l;
        synchronized (this.f15917j) {
            try {
                if (this.f15916i != null && (l = this.f15923r.f16485p) != null) {
                    D();
                    this.f15918k.set(true);
                    this.f15914g = new N1(this, 0);
                    try {
                        this.f15916i.schedule(this.f15914g, l.longValue());
                    } catch (Throwable th) {
                        this.d.q().getLogger().s(EnumC1495p1.WARNING, "Failed to schedule finish timer", th);
                        W1 w1S = s();
                        if (w1S == null) {
                            w1S = W1.OK;
                        }
                        v(w1S, null);
                        this.f15918k.set(false);
                    }
                }
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }

    @Override // io.sentry.U
    public final void n(String str, Long l, EnumC1494p0 enumC1494p0) {
        this.f15911b.n(str, l, enumC1494p0);
    }

    @Override // io.sentry.U
    public final void o(Throwable th) {
        S1 s12 = this.f15911b;
        if (s12.f15947g) {
            this.d.q().getLogger().k(EnumC1495p1.DEBUG, "The transaction is already finished. Throwable cannot be set", new Object[0]);
        } else {
            s12.f15945e = th;
        }
    }

    @Override // io.sentry.U
    public final T1 p() {
        return this.f15911b.c;
    }

    @Override // io.sentry.U
    public final void q(W1 w1) {
        v(w1, null);
    }

    @Override // io.sentry.U
    public final boolean r() {
        return false;
    }

    @Override // io.sentry.U
    public final W1 s() {
        return this.f15911b.c.f15961r;
    }

    @Override // io.sentry.U
    public final AbstractC1453b1 t() {
        return this.f15911b.f15944b;
    }

    @Override // io.sentry.U
    public final Throwable u() {
        return this.f15911b.f15945e;
    }

    @Override // io.sentry.U
    public final void v(W1 w1, AbstractC1453b1 abstractC1453b1) {
        F(w1, abstractC1453b1, true, null);
    }

    @Override // io.sentry.U
    public final B2.q w(List list) {
        if (!this.d.q().isTraceSampling()) {
            return null;
        }
        I();
        return B2.q.d(this.f15919m, list);
    }

    @Override // io.sentry.U
    public final U x(String str, String str2) {
        return H(str, str2, null, Y.SENTRY, new B2.P());
    }

    @Override // io.sentry.U
    public final U y(String str, String str2, AbstractC1453b1 abstractC1453b1, Y y10) {
        return H(str, str2, abstractC1453b1, y10, new B2.P());
    }

    @Override // io.sentry.U
    public final void z() {
        v(s(), null);
    }
}
