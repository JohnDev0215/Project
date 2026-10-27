package p0;

import A7.m;
import a1.k;
import android.graphics.Paint;
import h3.y;
import m0.AbstractC1792a;
import n0.AbstractC1861K;
import n0.AbstractC1887p;
import n0.C1877f;
import n0.C1878g;
import n0.C1883l;
import n0.C1891t;
import n0.InterfaceC1859I;

/* JADX INFO: renamed from: p0.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2071b implements d {
    public final C2070a l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final y f20638m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public C1878g f20639n;
    public C1878g o;

    public C2071b() {
        a1.c cVar = AbstractC2072c.f20640a;
        k kVar = k.l;
        f fVar = new f();
        C2070a c2070a = new C2070a();
        c2070a.f20636a = cVar;
        c2070a.f20637b = kVar;
        c2070a.c = fVar;
        c2070a.d = 0L;
        this.l = c2070a;
        this.f20638m = new y(this);
    }

    public static C1878g a(C2071b c2071b, long j3, e eVar, float f10, C1883l c1883l, int i7) {
        C1878g c1878gC = c2071b.c(eVar);
        if (f10 != 1.0f) {
            j3 = C1891t.b(C1891t.d(j3) * f10, j3);
        }
        if (!C1891t.c(AbstractC1861K.c(c1878gC.f19432a.getColor()), j3)) {
            c1878gC.e(j3);
        }
        if (c1878gC.c != null) {
            c1878gC.h(null);
        }
        if (!m.a(c1878gC.d, c1883l)) {
            c1878gC.f(c1883l);
        }
        if (!AbstractC1861K.p(c1878gC.f19433b, i7)) {
            c1878gC.d(i7);
        }
        if (!AbstractC1861K.r(c1878gC.f19432a.isFilterBitmap() ? 1 : 0, 1)) {
            c1878gC.g(1);
        }
        return c1878gC;
    }

    @Override // p0.d
    public final y F() {
        return this.f20638m;
    }

    @Override // p0.d
    public final void I(InterfaceC1859I interfaceC1859I, AbstractC1887p abstractC1887p, float f10, e eVar, C1883l c1883l, int i7) {
        this.l.c.f(interfaceC1859I, b(abstractC1887p, eVar, f10, c1883l, i7, 1));
    }

    @Override // p0.d
    public final void J(InterfaceC1859I interfaceC1859I, long j3, float f10, e eVar, C1883l c1883l, int i7) {
        this.l.c.f(interfaceC1859I, a(this, j3, eVar, f10, c1883l, i7));
    }

    @Override // p0.d
    public final void Q(long j3, float f10, long j10, float f11, e eVar, C1883l c1883l, int i7) {
        this.l.c.d(f10, j10, a(this, j3, eVar, f11, c1883l, i7));
    }

    public final C1878g b(AbstractC1887p abstractC1887p, e eVar, float f10, C1883l c1883l, int i7, int i10) {
        C1878g c1878gC = c(eVar);
        if (abstractC1887p != null) {
            abstractC1887p.a(f10, e(), c1878gC);
        } else {
            if (c1878gC.c != null) {
                c1878gC.h(null);
            }
            long jC = AbstractC1861K.c(c1878gC.f19432a.getColor());
            long j3 = C1891t.f19445b;
            if (!C1891t.c(jC, j3)) {
                c1878gC.e(j3);
            }
            if (c1878gC.f19432a.getAlpha() / 255.0f != f10) {
                c1878gC.c(f10);
            }
        }
        if (!m.a(c1878gC.d, c1883l)) {
            c1878gC.f(c1883l);
        }
        if (!AbstractC1861K.p(c1878gC.f19433b, i7)) {
            c1878gC.d(i7);
        }
        if (!AbstractC1861K.r(c1878gC.f19432a.isFilterBitmap() ? 1 : 0, i10)) {
            c1878gC.g(i10);
        }
        return c1878gC;
    }

    public final C1878g c(e eVar) {
        if (m.a(eVar, g.f20641a)) {
            C1878g c1878g = this.f20639n;
            if (c1878g != null) {
                return c1878g;
            }
            C1878g c1878gH = AbstractC1861K.h();
            c1878gH.l(0);
            this.f20639n = c1878gH;
            return c1878gH;
        }
        if (!(eVar instanceof h)) {
            throw new A9.k();
        }
        C1878g c1878gH2 = this.o;
        if (c1878gH2 == null) {
            c1878gH2 = AbstractC1861K.h();
            c1878gH2.l(1);
            this.o = c1878gH2;
        }
        Paint paint = c1878gH2.f19432a;
        float strokeWidth = paint.getStrokeWidth();
        h hVar = (h) eVar;
        float f10 = hVar.f20642a;
        if (strokeWidth != f10) {
            c1878gH2.k(f10);
        }
        int iA = c1878gH2.a();
        int i7 = hVar.c;
        if (!AbstractC1861K.s(iA, i7)) {
            c1878gH2.i(i7);
        }
        float strokeMiter = paint.getStrokeMiter();
        float f11 = hVar.f20643b;
        if (strokeMiter != f11) {
            c1878gH2.f19432a.setStrokeMiter(f11);
        }
        int iB = c1878gH2.b();
        int i10 = hVar.d;
        if (!AbstractC1861K.t(iB, i10)) {
            c1878gH2.j(i10);
        }
        if (!m.a(null, null)) {
            c1878gH2.f19432a.setPathEffect(null);
        }
        return c1878gH2;
    }

    @Override // p0.d
    public final void c0(long j3, long j10, long j11, float f10, e eVar, C1883l c1883l, int i7) {
        this.l.c.l(m0.c.d(j10), m0.c.e(j10), m0.f.d(j11) + m0.c.d(j10), m0.f.b(j11) + m0.c.e(j10), a(this, j3, eVar, f10, c1883l, i7));
    }

    @Override // p0.d
    public final void d0(long j3, float f10, float f11, long j10, long j11, float f12, e eVar, C1883l c1883l, int i7) {
        this.l.c.h(m0.c.d(j10), m0.c.e(j10), m0.f.d(j11) + m0.c.d(j10), m0.f.b(j11) + m0.c.e(j10), f10, f11, a(this, j3, eVar, f12, c1883l, i7));
    }

    @Override // p0.d
    public final void g0(C1877f c1877f, long j3, long j10, long j11, long j12, float f10, e eVar, C1883l c1883l, int i7, int i10) {
        this.l.c.a(c1877f, j3, j10, j11, j12, b(null, eVar, f10, c1883l, i7, i10));
    }

    @Override // a1.b
    public final float getDensity() {
        return this.l.f20636a.getDensity();
    }

    @Override // p0.d
    public final k getLayoutDirection() {
        return this.l.f20637b;
    }

    @Override // a1.b
    public final float p() {
        return this.l.f20636a.p();
    }

    @Override // p0.d
    public final void u0(long j3, long j10, long j11, long j12, e eVar, float f10, C1883l c1883l, int i7) {
        this.l.c.e(m0.c.d(j10), m0.c.e(j10), m0.f.d(j11) + m0.c.d(j10), m0.f.b(j11) + m0.c.e(j10), AbstractC1792a.b(j12), AbstractC1792a.c(j12), a(this, j3, eVar, f10, c1883l, i7));
    }
}
