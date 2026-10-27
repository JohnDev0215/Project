package g4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import l3.P;

/* JADX INFO: loaded from: classes.dex */
public final class p implements B4.b {

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public static final io.sentry.hints.i f14919H = new io.sentry.hints.i(20);

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public boolean f14920A;
    public t B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public boolean f14921C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public r f14922D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public i f14923E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public volatile boolean f14924F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public boolean f14925G;
    public final o l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final B4.e f14926m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final l f14927n;
    public final P o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final io.sentry.hints.i f14928p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final l f14929q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final j4.e f14930r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final j4.e f14931s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final j4.e f14932t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final AtomicInteger f14933u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public q f14934v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f14935w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f14936x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public x f14937y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public int f14938z;

    public p(j4.e eVar, j4.e eVar2, j4.e eVar3, j4.e eVar4, l lVar, l lVar2, P p10) {
        io.sentry.hints.i iVar = f14919H;
        this.l = new o(new ArrayList(2));
        this.f14926m = new B4.e();
        this.f14933u = new AtomicInteger();
        this.f14930r = eVar;
        this.f14931s = eVar2;
        this.f14932t = eVar4;
        this.f14929q = lVar;
        this.f14927n = lVar2;
        this.o = p10;
        this.f14928p = iVar;
    }

    public final synchronized void a(w4.g gVar, Executor executor) {
        try {
            this.f14926m.a();
            o oVar = this.l;
            oVar.getClass();
            oVar.l.add(new n(gVar, executor));
            if (this.f14920A) {
                e(1);
                executor.execute(new m(this, gVar, 1));
            } else if (this.f14921C) {
                e(1);
                executor.execute(new m(this, gVar, 0));
            } else {
                A4.h.a("Cannot add callbacks to a cancelled EngineJob", !this.f14924F);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public final void b() {
        if (f()) {
            return;
        }
        this.f14924F = true;
        i iVar = this.f14923E;
        iVar.f14886L = true;
        g gVar = iVar.f14884J;
        if (gVar != null) {
            gVar.cancel();
        }
        l lVar = this.f14929q;
        q qVar = this.f14934v;
        synchronized (lVar) {
            S6.c cVar = lVar.f14910a;
            cVar.getClass();
            HashMap map = cVar.f8070a;
            if (equals(map.get(qVar))) {
                map.remove(qVar);
            }
        }
    }

    @Override // B4.b
    public final B4.e c() {
        return this.f14926m;
    }

    public final void d() {
        r rVar;
        synchronized (this) {
            try {
                this.f14926m.a();
                A4.h.a("Not yet complete!", f());
                int iDecrementAndGet = this.f14933u.decrementAndGet();
                A4.h.a("Can't decrement below 0", iDecrementAndGet >= 0);
                if (iDecrementAndGet == 0) {
                    rVar = this.f14922D;
                    g();
                } else {
                    rVar = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        if (rVar != null) {
            rVar.c();
        }
    }

    public final synchronized void e(int i7) {
        r rVar;
        A4.h.a("Not yet complete!", f());
        if (this.f14933u.getAndAdd(i7) == 0 && (rVar = this.f14922D) != null) {
            rVar.a();
        }
    }

    public final boolean f() {
        return this.f14921C || this.f14920A || this.f14924F;
    }

    public final synchronized void g() {
        boolean zB;
        if (this.f14934v == null) {
            throw new IllegalArgumentException();
        }
        this.l.l.clear();
        this.f14934v = null;
        this.f14922D = null;
        this.f14937y = null;
        this.f14921C = false;
        this.f14924F = false;
        this.f14920A = false;
        this.f14925G = false;
        i iVar = this.f14923E;
        V2.c cVar = iVar.f14895r;
        synchronized (cVar) {
            cVar.f9334a = true;
            zB = cVar.b();
        }
        if (zB) {
            iVar.k();
        }
        this.f14923E = null;
        this.B = null;
        this.f14938z = 0;
        this.o.B(this);
    }

    public final synchronized void h(w4.g gVar) {
        try {
            this.f14926m.a();
            o oVar = this.l;
            oVar.l.remove(new n(gVar, A4.h.f420b));
            if (this.l.l.isEmpty()) {
                b();
                if (this.f14920A || this.f14921C) {
                    if (this.f14933u.get() == 0) {
                        g();
                    }
                }
            }
        } catch (Throwable th) {
            throw th;
        }
    }
}
