package p6;

import A7.m;
import A7.x;
import D.C0138u;
import N0.D;
import N0.L;
import Q.c1;
import S0.AbstractC0703o;
import T.C0753m;
import T.C0761q;
import T.Q0;
import T.S;
import T.X;
import g0.l;
import g0.o;
import h6.j;
import m7.i;
import m7.q;
import n0.C1863M;
import n0.C1891t;
import p0.g;
import p0.h;
import x.C2620I;
import z7.InterfaceC2782k;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final class f implements InterfaceC2786o {
    public final /* synthetic */ float l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f20932m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ float f20933n;
    public final /* synthetic */ float o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ long f20934p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ i f20935q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ Q0 f20936r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ boolean f20937s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ C2620I f20938t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ x f20939u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ L f20940v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final /* synthetic */ long f20941w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final /* synthetic */ X f20942x;

    public f(float f10, int i7, float f11, float f12, long j3, i iVar, Q0 q02, boolean z6, C2620I c2620i, x xVar, L l, long j10, X x8) {
        this.l = f10;
        this.f20932m = i7;
        this.f20933n = f11;
        this.o = f12;
        this.f20934p = j3;
        this.f20935q = iVar;
        this.f20936r = q02;
        this.f20937s = z6;
        this.f20938t = c2620i;
        this.f20939u = xVar;
        this.f20940v = l;
        this.f20941w = j10;
        this.f20942x = x8;
    }

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) {
        C0761q c0761q = (C0761q) obj2;
        int iIntValue = ((Number) obj3).intValue();
        m.f((C0138u) obj, "$this$BoxWithConstraints");
        if ((iIntValue & 17) == 16 && c0761q.x()) {
            c0761q.K();
        } else {
            l lVar = l.f14684a;
            o oVarC = androidx.compose.foundation.layout.c.c(lVar, 1.0f);
            c0761q.Q(146129019);
            boolean zC = c0761q.c(this.l) | c0761q.d(this.f20932m) | c0761q.c(this.f20933n) | c0761q.c(this.o) | c0761q.e(this.f20934p);
            i iVar = this.f20935q;
            boolean zF = zC | c0761q.f(iVar);
            Object objG = c0761q.G();
            S s6 = C0753m.f8270a;
            if (zF || objG == s6) {
                final float f10 = this.f20933n;
                final float f11 = this.o;
                final float f12 = this.l;
                final int i7 = this.f20932m;
                final long j3 = this.f20934p;
                final i iVar2 = this.f20935q;
                objG = new InterfaceC2782k() { // from class: p6.d
                    @Override // z7.InterfaceC2782k
                    public final Object b(Object obj4) {
                        p0.d dVar = (p0.d) obj4;
                        m.f(dVar, "$this$Canvas");
                        float f13 = 360 * f12;
                        int i10 = i7;
                        float f14 = 360.0f / i10;
                        float f15 = 2;
                        float fC = m0.f.c(dVar.e()) / f15;
                        float f16 = f10;
                        float fY = (dVar.y(f16) / f15) + fC;
                        float f17 = f11;
                        float fY2 = dVar.y(f17) + fY;
                        float fY3 = (fC - (dVar.y(f16) / f15)) - dVar.y(f17);
                        long j10 = j3;
                        long jB = C1891t.b(0.6f, j10);
                        g gVar = g.f20641a;
                        dVar.Q(jB, fY2, (120 & 4) != 0 ? dVar.S() : 0L, 1.0f, (120 & 16) != 0 ? g.f20641a : gVar, null, 3);
                        dVar.Q(j10, fY3, (120 & 4) != 0 ? dVar.S() : 0L, 1.0f, (120 & 16) != 0 ? g.f20641a : gVar, null, 3);
                        i iVar3 = iVar2;
                        dVar.Q(((C1891t) iVar3.f19259m).f19453a, fY2, (120 & 4) != 0 ? dVar.S() : 0L, 1.0f, (120 & 16) != 0 ? g.f20641a : new h(dVar.y(f17), 0.0f, 0, 0, 30), null, 3);
                        dVar.Q(((C1891t) iVar3.f19259m).f19453a, fY3, (120 & 4) != 0 ? dVar.S() : 0L, 1.0f, (120 & 16) != 0 ? g.f20641a : new h(dVar.y(f17), 0.0f, 0, 0, 30), null, 3);
                        int i11 = 0;
                        while (i11 < i10) {
                            float f18 = i11 * f14;
                            p0.d.s(dVar, ((C1891t) iVar3.l).f19453a, (f18 - 90.0f) - (f14 / f15), f14 - 2.0f, 0L, 0L, f18 + f14 <= f13 ? 1.0f : 0.2f, new h(dVar.y(f16), 0.0f, 0, 0, 26), 816);
                            i11++;
                            f16 = f16;
                            f15 = f15;
                            iVar3 = iVar3;
                        }
                        return q.f19267a;
                    }
                };
                c0761q.a0(objG);
            }
            c0761q.p(false);
            j.b(6, c0761q, oVarC, (InterfaceC2782k) objG);
            c0761q.Q(146187028);
            final Q0 q02 = this.f20936r;
            boolean zF2 = c0761q.f(q02);
            final boolean z6 = this.f20937s;
            boolean zG = zF2 | c0761q.g(z6);
            final C2620I c2620i = this.f20938t;
            boolean zF3 = zG | c0761q.f(c2620i);
            Object objG2 = c0761q.G();
            if (zF3 || objG2 == s6) {
                final X x8 = this.f20942x;
                objG2 = new InterfaceC2782k() { // from class: p6.e
                    @Override // z7.InterfaceC2782k
                    public final Object b(Object obj4) {
                        C1863M c1863m = (C1863M) obj4;
                        m.f(c1863m, "$this$graphicsLayer");
                        X x9 = x8;
                        boolean zBooleanValue = ((Boolean) x9.getValue()).booleanValue();
                        boolean z10 = z6;
                        Q0 q03 = q02;
                        C2620I c2620i2 = c2620i;
                        float fFloatValue = 1.0f;
                        c1863m.i(zBooleanValue ? ((Number) q03.getValue()).floatValue() : z10 ? ((Number) c2620i2.o.getValue()).floatValue() : 1.0f);
                        if (((Boolean) x9.getValue()).booleanValue()) {
                            fFloatValue = ((Number) q03.getValue()).floatValue();
                        } else if (z10) {
                            fFloatValue = ((Number) c2620i2.o.getValue()).floatValue();
                        }
                        c1863m.j(fFloatValue);
                        return q.f19267a;
                    }
                };
                c0761q.a0(objG2);
            }
            c0761q.p(false);
            o oVarA = androidx.compose.ui.graphics.a.a(lVar, (InterfaceC2782k) objG2);
            String strE = android.hardware.bydauto.ac.tsimpl.a.e(this.f20939u.l, "x");
            D d = this.f20940v.f4358a;
            AbstractC0703o abstractC0703o = d.f4327f;
            c1.b(strE, oVarA, ((C1891t) iVar.l).f19453a, this.f20941w, null, d.c, abstractC0703o, 0L, null, new Y0.i(3), 0L, 0, false, 0, 0, null, null, c0761q, 0, 0, 130448);
        }
        return q.f19267a;
    }
}
