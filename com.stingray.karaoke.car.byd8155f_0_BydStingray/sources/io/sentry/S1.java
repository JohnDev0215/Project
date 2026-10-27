package io.sentry;

import h3.C1350a;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final class S1 implements U {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AbstractC1453b1 f15943a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public AbstractC1453b1 f15944b;
    public final T1 c;
    public final P1 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Throwable f15945e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final D f15946f;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final B2.P f15949i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public U1 f15950j;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f15947g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final AtomicBoolean f15948h = new AtomicBoolean(false);

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final ConcurrentHashMap f15951k = new ConcurrentHashMap();
    public final ConcurrentHashMap l = new ConcurrentHashMap();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final io.sentry.util.d f15952m = new io.sentry.util.d(new C1350a(9));

    public S1(io.sentry.protocol.t tVar, V1 v12, P1 p12, String str, D d, AbstractC1453b1 abstractC1453b1, B2.P p10, M1 m12) {
        this.c = new T1(tVar, new V1(), str, v12, p12.f15911b.c.o);
        this.d = p12;
        h6.e.q(d, "hub is required");
        this.f15946f = d;
        this.f15949i = p10;
        this.f15950j = m12;
        if (abstractC1453b1 != null) {
            this.f15943a = abstractC1453b1;
        } else {
            this.f15943a = d.q().getDateProvider().a();
        }
    }

    @Override // io.sentry.U
    public final void A(Object obj, String str) {
        this.f15951k.put(str, obj);
    }

    @Override // io.sentry.U
    public final AbstractC1453b1 B() {
        return this.f15943a;
    }

    @Override // io.sentry.U
    public final String a() {
        return this.c.f15960q;
    }

    @Override // io.sentry.U
    public final void b(W1 w1) {
        this.c.f15961r = w1;
    }

    @Override // io.sentry.U
    public final void e(String str) {
        this.c.f15960q = str;
    }

    @Override // io.sentry.U
    public final h3.y f() {
        T1 t12 = this.c;
        io.sentry.protocol.t tVar = t12.l;
        B8.t tVar2 = t12.o;
        return new h3.y(tVar, t12.f15957m, tVar2 == null ? null : (Boolean) tVar2.f808n, 3);
    }

    @Override // io.sentry.U
    public final boolean g() {
        return this.f15947g;
    }

    @Override // io.sentry.U
    public final U j(String str) {
        return x(str, null);
    }

    @Override // io.sentry.U
    public final boolean k(AbstractC1453b1 abstractC1453b1) {
        if (this.f15944b == null) {
            return false;
        }
        this.f15944b = abstractC1453b1;
        return true;
    }

    @Override // io.sentry.U
    public final void l(Number number, String str) {
        if (this.f15947g) {
            this.f15946f.q().getLogger().k(EnumC1495p1.DEBUG, "The span is already finished. Measurement %s cannot be set", str);
            return;
        }
        this.l.put(str, new io.sentry.protocol.i(number, null));
        P1 p12 = this.d;
        S1 s12 = p12.f15911b;
        if (s12 == this || s12.l.containsKey(str)) {
            return;
        }
        p12.l(number, str);
    }

    @Override // io.sentry.U
    public final void n(String str, Long l, EnumC1494p0 enumC1494p0) {
        if (this.f15947g) {
            this.f15946f.q().getLogger().k(EnumC1495p1.DEBUG, "The span is already finished. Measurement %s cannot be set", str);
            return;
        }
        this.l.put(str, new io.sentry.protocol.i(l, enumC1494p0.apiName()));
        P1 p12 = this.d;
        S1 s12 = p12.f15911b;
        if (s12 == this || s12.l.containsKey(str)) {
            return;
        }
        p12.n(str, l, enumC1494p0);
    }

    @Override // io.sentry.U
    public final void o(Throwable th) {
        this.f15945e = th;
    }

    @Override // io.sentry.U
    public final T1 p() {
        return this.c;
    }

    @Override // io.sentry.U
    public final void q(W1 w1) {
        v(w1, this.f15946f.q().getDateProvider().a());
    }

    @Override // io.sentry.U
    public final boolean r() {
        return false;
    }

    @Override // io.sentry.U
    public final W1 s() {
        return this.c.f15961r;
    }

    @Override // io.sentry.U
    public final AbstractC1453b1 t() {
        return this.f15944b;
    }

    @Override // io.sentry.U
    public final Throwable u() {
        return this.f15945e;
    }

    @Override // io.sentry.U
    public final void v(W1 w1, AbstractC1453b1 abstractC1453b1) {
        AbstractC1453b1 abstractC1453b12;
        AbstractC1453b1 abstractC1453b13;
        if (this.f15947g || !this.f15948h.compareAndSet(false, true)) {
            return;
        }
        T1 t12 = this.c;
        t12.f15961r = w1;
        D d = this.f15946f;
        if (abstractC1453b1 == null) {
            abstractC1453b1 = d.q().getDateProvider().a();
        }
        this.f15944b = abstractC1453b1;
        B2.P p10 = this.f15949i;
        p10.getClass();
        boolean z6 = p10.l;
        P1 p12 = this.d;
        if (z6) {
            V1 v12 = p12.f15911b.c.f15957m;
            V1 v13 = t12.f15957m;
            boolean zEquals = v12.equals(v13);
            CopyOnWriteArrayList<S1> copyOnWriteArrayList = p12.c;
            List<S1> list = copyOnWriteArrayList;
            if (!zEquals) {
                ArrayList arrayList = new ArrayList();
                for (S1 s12 : copyOnWriteArrayList) {
                    V1 v14 = s12.c.f15958n;
                    if (v14 != null && v14.equals(v13)) {
                        arrayList.add(s12);
                    }
                }
                list = arrayList;
            }
            AbstractC1453b1 abstractC1453b14 = null;
            AbstractC1453b1 abstractC1453b15 = null;
            for (S1 s13 : list) {
                if (abstractC1453b14 == null || s13.f15943a.b(abstractC1453b14) < 0) {
                    abstractC1453b14 = s13.f15943a;
                }
                if (abstractC1453b15 == null || ((abstractC1453b13 = s13.f15944b) != null && abstractC1453b13.b(abstractC1453b15) > 0)) {
                    abstractC1453b15 = s13.f15944b;
                }
            }
            if (p10.l && abstractC1453b15 != null && ((abstractC1453b12 = this.f15944b) == null || abstractC1453b12.b(abstractC1453b15) > 0)) {
                k(abstractC1453b15);
            }
        }
        Throwable cause = this.f15945e;
        if (cause != null) {
            String str = p12.f15912e;
            d.getClass();
            h6.e.q(cause, "throwable is required");
            h6.e.q(str, "transactionName is required");
            while (cause.getCause() != null && cause.getCause() != cause) {
                cause = cause.getCause();
            }
            Map map = d.f15827e;
            if (!map.containsKey(cause)) {
                map.put(cause, new io.sentry.util.e(new WeakReference(this), str));
            }
        }
        U1 u12 = this.f15950j;
        if (u12 != null) {
            u12.a(this);
        }
        this.f15947g = true;
    }

    @Override // io.sentry.U
    public final B2.q w(List list) {
        return this.d.w(list);
    }

    @Override // io.sentry.U
    public final U x(String str, String str2) {
        if (this.f15947g) {
            return C1530z0.f16994a;
        }
        V1 v12 = this.c.f15957m;
        P1 p12 = this.d;
        p12.getClass();
        return p12.E(v12, str, str2, null, Y.SENTRY, new B2.P());
    }

    @Override // io.sentry.U
    public final void z() {
        q(this.c.f15961r);
    }

    public S1(b2 b2Var, P1 p12, D d, AbstractC1453b1 abstractC1453b1, c2 c2Var) {
        this.c = b2Var;
        h6.e.q(p12, "sentryTracer is required");
        this.d = p12;
        this.f15946f = d;
        this.f15950j = null;
        if (abstractC1453b1 != null) {
            this.f15943a = abstractC1453b1;
        } else {
            this.f15943a = d.q().getDateProvider().a();
        }
        this.f15949i = c2Var;
    }
}
