package u2;

import android.os.Looper;
import e2.C1192A;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import k2.InterfaceC1606g;
import o2.C2026c;
import s3.RunnableC2389i;
import y2.ExecutorC2739a;

/* JADX INFO: loaded from: classes.dex */
public final class W extends AbstractC2498a {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final InterfaceC1606g f22751h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final C2026c f22752i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final q2.o f22753j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final x2.i f22754k;
    public final int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final boolean f22755m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f22756n = true;
    public long o = -9223372036854775807L;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f22757p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f22758q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public k2.E f22759r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public e2.F f22760s;

    public W(e2.F f10, InterfaceC1606g interfaceC1606g, C2026c c2026c, q2.o oVar, x2.i iVar, int i7, boolean z6) {
        this.f22760s = f10;
        this.f22751h = interfaceC1606g;
        this.f22752i = c2026c;
        this.f22753j = oVar;
        this.f22754k = iVar;
        this.l = i7;
        this.f22755m = z6;
    }

    @Override // u2.AbstractC2498a
    public final boolean a(e2.F f10) {
        C1192A c1192a = h().f13879b;
        c1192a.getClass();
        C1192A c1192a2 = f10.f13879b;
        if (c1192a2 != null && c1192a2.f13845a.equals(c1192a.f13845a) && c1192a2.f13850h == c1192a.f13850h) {
            int i7 = AbstractC1343A.f15195a;
            if (Objects.equals(c1192a2.f13848f, c1192a.f13848f)) {
                return true;
            }
        }
        return false;
    }

    @Override // u2.AbstractC2498a
    public final InterfaceC2490A b(C2492C c2492c, x2.e eVar, long j3) {
        k2.h hVarQ = this.f22751h.q();
        k2.E e10 = this.f22759r;
        if (e10 != null) {
            hVarQ.b(e10);
        }
        C1192A c1192a = h().f13879b;
        c1192a.getClass();
        AbstractC1346c.k(this.f22795g);
        h3.y yVar = new h3.y((B2.u) this.f22752i.f20404m);
        q2.k kVar = new q2.k(this.d.c, 0, c2492c);
        B5.u uVar = new B5.u((CopyOnWriteArrayList) this.c.o, 0, c2492c);
        long jH = AbstractC1343A.H(c1192a.f13850h);
        return new T(c1192a.f13845a, hVarQ, yVar, this.f22753j, kVar, this.f22754k, uVar, this, eVar, c1192a.f13848f, this.l, this.f22755m, jH, null);
    }

    @Override // u2.AbstractC2498a
    public final synchronized e2.F h() {
        return this.f22760s;
    }

    @Override // u2.AbstractC2498a
    public final void j() {
    }

    @Override // u2.AbstractC2498a
    public final void l(k2.E e10) {
        this.f22759r = e10;
        Looper looperMyLooper = Looper.myLooper();
        looperMyLooper.getClass();
        o2.l lVar = this.f22795g;
        AbstractC1346c.k(lVar);
        q2.o oVar = this.f22753j;
        oVar.f(looperMyLooper, lVar);
        oVar.c();
        t();
    }

    @Override // u2.AbstractC2498a
    public final void n(InterfaceC2490A interfaceC2490A) {
        T t10 = (T) interfaceC2490A;
        if (t10.f22715I) {
            for (Z z6 : t10.f22712F) {
                z6.f();
                q2.h hVar = z6.f22774h;
                if (hVar != null) {
                    hVar.d(z6.f22771e);
                    z6.f22774h = null;
                    z6.f22773g = null;
                }
            }
        }
        x2.n nVar = t10.f22744x;
        x2.k kVar = nVar.f23758b;
        if (kVar != null) {
            kVar.a(true);
        }
        RunnableC2389i runnableC2389i = new RunnableC2389i(t10, 3);
        ExecutorC2739a executorC2739a = nVar.f23757a;
        executorC2739a.execute(runnableC2389i);
        I i7 = executorC2739a.f24118m;
        executorC2739a.l.shutdown();
        t10.f22709C.removeCallbacksAndMessages(null);
        t10.f22710D = null;
        t10.f22733a0 = true;
    }

    @Override // u2.AbstractC2498a
    public final void p() {
        this.f22753j.a();
    }

    @Override // u2.AbstractC2498a
    public final synchronized void s(e2.F f10) {
        this.f22760s = f10;
    }

    public final void t() {
        e2.f0 g0Var = new g0(this.o, this.f22757p, this.f22758q, h());
        if (this.f22756n) {
            g0Var = new U(g0Var);
        }
        m(g0Var);
    }

    public final void u(long j3, boolean z6, boolean z10) {
        if (j3 == -9223372036854775807L) {
            j3 = this.o;
        }
        if (!this.f22756n && this.o == j3 && this.f22757p == z6 && this.f22758q == z10) {
            return;
        }
        this.o = j3;
        this.f22757p = z6;
        this.f22758q = z10;
        this.f22756n = false;
        t();
    }
}
