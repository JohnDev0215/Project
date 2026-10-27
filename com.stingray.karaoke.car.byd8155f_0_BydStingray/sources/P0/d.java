package p0;

import A7.m;
import E0.H;
import a1.k;
import android.graphics.Paint;
import h3.y;
import h6.j;
import n0.AbstractC1861K;
import n0.AbstractC1887p;
import n0.C1877f;
import n0.C1878g;
import n0.C1883l;
import n0.InterfaceC1859I;
import n0.InterfaceC1889r;

/* JADX INFO: loaded from: classes.dex */
public interface d extends a1.b {
    static void T(H h10, AbstractC1887p abstractC1887p, long j3, long j10, long j11, e eVar, int i7) {
        long j12 = (i7 & 2) != 0 ? 0L : j3;
        h10.d(abstractC1887p, j12, (i7 & 4) != 0 ? s0(h10.l.e(), j12) : j10, j11, 1.0f, (i7 & 32) != 0 ? g.f20641a : eVar, null, 3);
    }

    static /* synthetic */ void U(d dVar, InterfaceC1859I interfaceC1859I, long j3, float f10, int i7) {
        if ((i7 & 4) != 0) {
            f10 = 1.0f;
        }
        dVar.J(interfaceC1859I, j3, f10, g.f20641a, null, 3);
    }

    static void X(H h10, AbstractC1887p abstractC1887p, long j3, long j10, float f10, e eVar, int i7) {
        long j11 = (i7 & 2) != 0 ? 0L : j3;
        h10.c(abstractC1887p, j11, (i7 & 4) != 0 ? s0(h10.l.e(), j11) : j10, (i7 & 8) != 0 ? 1.0f : f10, (i7 & 16) != 0 ? g.f20641a : eVar, null, 3);
    }

    static void Z(d dVar, C1877f c1877f, long j3, long j10, long j11, float f10, C1883l c1883l, int i7, int i10) {
        dVar.g0(c1877f, (i10 & 2) != 0 ? 0L : j3, j10, 0L, (i10 & 16) != 0 ? j10 : j11, (i10 & 32) != 0 ? 1.0f : f10, g.f20641a, c1883l, 3, (i10 & 512) != 0 ? 1 : i7);
    }

    static void h(H h10, long j3, long j10, long j11, long j12) {
        h10.u0(j3, j10, j11, j12, g.f20641a, 1.0f, null, 3);
    }

    static /* synthetic */ void m(d dVar, InterfaceC1859I interfaceC1859I, AbstractC1887p abstractC1887p, float f10, h hVar, int i7) {
        if ((i7 & 4) != 0) {
            f10 = 1.0f;
        }
        float f11 = f10;
        e eVar = hVar;
        if ((i7 & 8) != 0) {
            eVar = g.f20641a;
        }
        dVar.I(interfaceC1859I, abstractC1887p, f11, eVar, null, (i7 & 32) != 0 ? 3 : 0);
    }

    static void q(H h10, AbstractC1887p abstractC1887p, long j3, long j10, float f10, float f11, int i7) {
        float f12 = (i7 & 64) != 0 ? 1.0f : f11;
        C2071b c2071b = h10.l;
        InterfaceC1889r interfaceC1889r = c2071b.l.c;
        C1878g c1878gH = c2071b.o;
        if (c1878gH == null) {
            c1878gH = AbstractC1861K.h();
            c1878gH.l(1);
            c2071b.o = c1878gH;
        }
        C1878g c1878g = c1878gH;
        if (abstractC1887p != null) {
            abstractC1887p.a(f12, c2071b.e(), c1878g);
        } else if (c1878g.f19432a.getAlpha() / 255.0f != f12) {
            c1878g.c(f12);
        }
        if (!m.a(c1878g.d, null)) {
            c1878g.f(null);
        }
        if (!AbstractC1861K.p(c1878g.f19433b, 3)) {
            c1878g.d(3);
        }
        Paint paint = c1878g.f19432a;
        if (paint.getStrokeWidth() != f10) {
            c1878g.k(f10);
        }
        if (paint.getStrokeMiter() != 4.0f) {
            c1878g.f19432a.setStrokeMiter(4.0f);
        }
        if (!AbstractC1861K.s(c1878g.a(), 0)) {
            c1878g.i(0);
        }
        if (!AbstractC1861K.t(c1878g.b(), 0)) {
            c1878g.j(0);
        }
        if (!m.a(null, null)) {
            c1878g.f19432a.setPathEffect(null);
        }
        if (!AbstractC1861K.r(paint.isFilterBitmap() ? 1 : 0, 1)) {
            c1878g.g(1);
        }
        interfaceC1889r.n(j3, j10, c1878g);
    }

    static /* synthetic */ void s(d dVar, long j3, float f10, float f11, long j10, long j11, float f12, h hVar, int i7) {
        long j12 = (i7 & 16) != 0 ? 0L : j10;
        dVar.d0(j3, f10, f11, j12, (i7 & 32) != 0 ? s0(dVar.e(), j12) : j11, (i7 & 64) != 0 ? 1.0f : f12, hVar, null, 3);
    }

    static long s0(long j3, long j10) {
        return j.f(m0.f.d(j3) - m0.c.d(j10), m0.f.b(j3) - m0.c.e(j10));
    }

    static void t(H h10, C1877f c1877f, C1883l c1883l) {
        g gVar = g.f20641a;
        C2071b c2071b = h10.l;
        c2071b.l.c.t(c1877f, 0L, c2071b.b(null, gVar, 1.0f, c1883l, 3, 1));
    }

    y F();

    void I(InterfaceC1859I interfaceC1859I, AbstractC1887p abstractC1887p, float f10, e eVar, C1883l c1883l, int i7);

    void J(InterfaceC1859I interfaceC1859I, long j3, float f10, e eVar, C1883l c1883l, int i7);

    void Q(long j3, float f10, long j10, float f11, e eVar, C1883l c1883l, int i7);

    default long S() {
        return j.k(F().y());
    }

    void c0(long j3, long j10, long j11, float f10, e eVar, C1883l c1883l, int i7);

    void d0(long j3, float f10, float f11, long j10, long j11, float f12, e eVar, C1883l c1883l, int i7);

    default long e() {
        return F().y();
    }

    void g0(C1877f c1877f, long j3, long j10, long j11, long j12, float f10, e eVar, C1883l c1883l, int i7, int i10);

    k getLayoutDirection();

    void u0(long j3, long j10, long j11, long j12, e eVar, float f10, C1883l c1883l, int i7);
}
