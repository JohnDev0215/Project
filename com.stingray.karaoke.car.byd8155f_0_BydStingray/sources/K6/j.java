package K6;

import E0.H;
import T.C0735d;
import T.C0753m;
import T.C0761q;
import T.C0772x;
import T.S;
import T.X;
import g0.o;
import m7.q;
import n0.C1891t;
import n7.z;
import p0.C2071b;
import y.y0;
import y0.v;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final class j implements InterfaceC2786o {
    public final /* synthetic */ n l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ y0 f3712m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f3713n;
    public final /* synthetic */ float o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ float f3714p;

    public j(n nVar, y0 y0Var, boolean z6, float f10, float f11) {
        this.l = nVar;
        this.f3712m = y0Var;
        this.f3713n = z6;
        this.o = f10;
        this.f3714p = f11;
    }

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) {
        o oVar = (o) obj;
        C0761q c0761q = (C0761q) obj2;
        ((Number) obj3).intValue();
        A7.m.f(oVar, "$this$composed");
        c0761q.Q(1684743063);
        Object objG = c0761q.G();
        S s6 = C0753m.f8270a;
        if (objG == s6) {
            C0772x c0772x = new C0772x(C0735d.B(c0761q));
            c0761q.a0(c0772x);
            objG = c0772x;
        }
        X8.c cVar = ((C0772x) objG).l;
        c0761q.Q(-1551626515);
        Object objG2 = c0761q.G();
        if (objG2 == s6) {
            objG2 = C0735d.O(Boolean.FALSE, S.f8224q);
            c0761q.a0(objG2);
        }
        final X x8 = (X) objG2;
        c0761q.p(false);
        q qVar = q.f19267a;
        c0761q.Q(-1551623697);
        final n nVar = this.l;
        boolean zF = c0761q.f(nVar);
        final y0 y0Var = this.f3712m;
        boolean zF2 = zF | c0761q.f(y0Var) | c0761q.h(cVar);
        Object objG3 = c0761q.G();
        if (zF2 || objG3 == s6) {
            i iVar = new i(nVar, y0Var, cVar, x8, null);
            c0761q.a0(iVar);
            objG3 = iVar;
        }
        c0761q.p(false);
        o oVarA = v.a(oVar, qVar, (InterfaceC2785n) objG3);
        c0761q.Q(-1551591971);
        boolean zF3 = c0761q.f(nVar) | c0761q.f(y0Var) | c0761q.g(this.f3713n) | c0761q.c(this.o) | c0761q.c(this.f3714p);
        Object objG4 = c0761q.G();
        if (zF3 || objG4 == s6) {
            final boolean z6 = this.f3713n;
            final float f10 = this.o;
            final float f11 = this.f3714p;
            objG4 = new InterfaceC2782k() { // from class: K6.f
                @Override // z7.InterfaceC2782k
                public final Object b(Object obj4) {
                    float f12;
                    float f13;
                    float f14;
                    H h10 = (H) obj4;
                    A7.m.f(h10, "$this$drawWithContent");
                    h10.a();
                    C2071b c2071b = h10.l;
                    float fB = m0.f.b(c2071b.e());
                    n nVar2 = nVar;
                    float fY = fB - h10.y(nVar2.d);
                    y0 y0Var2 = y0Var;
                    float fI = y0Var2.d.i() + fY;
                    float f15 = (fY / fI) * fY;
                    float fI2 = (y0Var2.f24028a.i() / fI) * fY;
                    boolean z10 = z6;
                    float f16 = f10;
                    float f17 = f11;
                    float f18 = nVar2.c;
                    if (z10) {
                        f12 = f17;
                        f13 = f15;
                        f14 = f18;
                        p0.d.h(h10, nVar2.f3716a, h6.e.a(m0.f.d(c2071b.e()) - h10.y(f18), 0.0f), h6.j.f(h10.y(f17), fY), z.a(f16, f16));
                    } else {
                        f12 = f17;
                        f13 = f15;
                        f14 = f18;
                    }
                    long jA = z.a(f16, f16);
                    boolean zBooleanValue = ((Boolean) x8.getValue()).booleanValue();
                    long jB = nVar2.f3717b;
                    if (zBooleanValue) {
                        jB = C1891t.b(0.8f, jB);
                    }
                    p0.d.h(h10, jB, h6.e.a(m0.f.d(c2071b.e()) - h10.y(f14), fI2), h6.j.f(h10.y(f12), f13), jA);
                    return q.f19267a;
                }
            };
            c0761q.a0(objG4);
        }
        c0761q.p(false);
        o oVarC = androidx.compose.ui.draw.a.c(oVarA, (InterfaceC2782k) objG4);
        c0761q.p(false);
        return oVarC;
    }
}
