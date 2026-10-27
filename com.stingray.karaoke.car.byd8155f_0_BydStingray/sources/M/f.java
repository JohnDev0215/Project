package M;

import A.C0022k;
import A.F;
import E0.AbstractC0156f;
import F0.AbstractC0227s0;
import F0.C0229t0;
import F0.R0;
import K.C0365x;
import K.p0;
import N0.H;
import S8.AbstractC0727y;
import S8.EnumC0726x;
import S8.k0;
import U8.EnumC0810a;
import V8.AbstractC0828q;
import V8.K;
import V8.S;
import android.graphics.Rect;
import android.view.View;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes.dex */
public final class f implements T0.t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public v f4137a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public k0 f4138b;
    public z c;
    public S d;

    @Override // T0.t
    public final void a(m0.d dVar) {
        Rect rect;
        z zVar = this.c;
        if (zVar != null) {
            zVar.l = new Rect(C7.a.W(dVar.f18882a), C7.a.W(dVar.f18883b), C7.a.W(dVar.c), C7.a.W(dVar.d));
            if (!zVar.f4179j.isEmpty() || (rect = zVar.l) == null) {
                return;
            }
            zVar.f4172a.requestRectangleOnScreen(new Rect(rect));
        }
    }

    @Override // T0.t
    public final void b() {
        j(null);
    }

    @Override // T0.t
    public final void c() {
        R0 r02;
        v vVar = this.f4137a;
        if (vVar == null || (r02 = (R0) AbstractC0156f.i(vVar, AbstractC0227s0.f2266n)) == null) {
            return;
        }
        ((C0229t0) r02).b();
    }

    @Override // T0.t
    public final void d(T0.y yVar, T0.s sVar, H h10, F f10, m0.d dVar, m0.d dVar2) {
        z zVar = this.c;
        if (zVar != null) {
            w wVar = zVar.f4181m;
            synchronized (wVar.c) {
                try {
                    wVar.f4164j = yVar;
                    wVar.l = sVar;
                    wVar.f4165k = h10;
                    wVar.f4166m = dVar;
                    wVar.f4167n = dVar2;
                    if (wVar.f4159e || wVar.d) {
                        wVar.a();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    @Override // T0.t
    public final void e() {
        R0 r02;
        v vVar = this.f4137a;
        if (vVar == null || (r02 = (R0) AbstractC0156f.i(vVar, AbstractC0227s0.f2266n)) == null) {
            return;
        }
        ((C0229t0) r02).a();
    }

    @Override // T0.t
    public final void f() throws IllegalAccessException, InvocationTargetException {
        k0 k0Var = this.f4138b;
        if (k0Var != null) {
            k0Var.g(null);
        }
        this.f4138b = null;
        K kI = i();
        if (kI != null) {
            ((S) kI).h();
        }
    }

    @Override // T0.t
    public final void g(T0.y yVar, T0.y yVar2) {
        z zVar = this.c;
        if (zVar != null) {
            boolean z6 = (N0.K.a(zVar.f4177h.f8482b, yVar2.f8482b) && A7.m.a(zVar.f4177h.c, yVar2.c)) ? false : true;
            zVar.f4177h = yVar2;
            int size = zVar.f4179j.size();
            for (int i7 = 0; i7 < size; i7++) {
                B b10 = (B) ((WeakReference) zVar.f4179j.get(i7)).get();
                if (b10 != null) {
                    b10.f4119g = yVar2;
                }
            }
            w wVar = zVar.f4181m;
            synchronized (wVar.c) {
                wVar.f4164j = null;
                wVar.l = null;
                wVar.f4165k = null;
                wVar.f4166m = null;
                wVar.f4167n = null;
            }
            if (A7.m.a(yVar, yVar2)) {
                if (z6) {
                    t tVar = zVar.f4173b;
                    int iE = N0.K.e(yVar2.f8482b);
                    int iD = N0.K.d(yVar2.f8482b);
                    N0.K k10 = zVar.f4177h.c;
                    int iE2 = k10 != null ? N0.K.e(k10.f4357a) : -1;
                    N0.K k11 = zVar.f4177h.c;
                    tVar.G().updateSelection((View) tVar.l, iE, iD, iE2, k11 != null ? N0.K.d(k11.f4357a) : -1);
                    return;
                }
                return;
            }
            if (yVar != null && (!A7.m.a(yVar.f8481a.l, yVar2.f8481a.l) || (N0.K.a(yVar.f8482b, yVar2.f8482b) && !A7.m.a(yVar.c, yVar2.c)))) {
                t tVar2 = zVar.f4173b;
                tVar2.G().restartInput((View) tVar2.l);
                return;
            }
            int size2 = zVar.f4179j.size();
            for (int i10 = 0; i10 < size2; i10++) {
                B b11 = (B) ((WeakReference) zVar.f4179j.get(i10)).get();
                if (b11 != null) {
                    T0.y yVar3 = zVar.f4177h;
                    t tVar3 = zVar.f4173b;
                    if (b11.f4123k) {
                        b11.f4119g = yVar3;
                        if (b11.f4121i) {
                            tVar3.G().updateExtractedText((View) tVar3.l, b11.f4120h, P8.n.k(yVar3));
                        }
                        N0.K k12 = yVar3.c;
                        int iE3 = k12 != null ? N0.K.e(k12.f4357a) : -1;
                        N0.K k13 = yVar3.c;
                        int iD2 = k13 != null ? N0.K.d(k13.f4357a) : -1;
                        long j3 = yVar3.f8482b;
                        tVar3.G().updateSelection((View) tVar3.l, N0.K.e(j3), N0.K.d(j3), iE3, iD2);
                    }
                }
            }
        }
    }

    @Override // T0.t
    public final void h(T0.y yVar, T0.m mVar, C0022k c0022k, C0365x c0365x) {
        j(new p0(yVar, this, mVar, c0022k, c0365x));
    }

    public final K i() {
        S s6 = this.d;
        if (s6 != null) {
            return s6;
        }
        if (!L.e.f3874a) {
            return null;
        }
        S sB = AbstractC0828q.b(1, 0, EnumC0810a.f9224n, 2);
        this.d = sB;
        return sB;
    }

    public final void j(p0 p0Var) {
        v vVar = this.f4137a;
        if (vVar == null) {
            return;
        }
        this.f4138b = vVar.f14695x ? AbstractC0727y.w(vVar.v0(), null, EnumC0726x.o, new u(vVar, new e(p0Var, this, vVar, null), null), 1) : null;
    }

    public final void k(v vVar) {
        if (this.f4137a == vVar) {
            this.f4137a = null;
            return;
        }
        throw new IllegalStateException(("Expected textInputModifierNode to be " + vVar + " but was " + this.f4137a).toString());
    }
}
