package y;

import A.C0024l;
import E0.AbstractC0156f;
import E0.InterfaceC0165o;
import m0.AbstractC1792a;
import n0.AbstractC1858H;
import n0.AbstractC1861K;
import n0.AbstractC1887p;
import n0.C1855E;
import n0.C1856F;
import n0.C1857G;
import n0.C1880i;
import n0.C1891t;
import n0.InterfaceC1866P;
import p0.C2071b;

/* JADX INFO: renamed from: y.p, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2711p extends g0.n implements InterfaceC0165o, E0.f0 {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public float f23978A;
    public InterfaceC1866P B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public long f23979C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public a1.k f23980D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public AbstractC1858H f23981E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public InterfaceC1866P f23982F;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public long f23983y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public AbstractC1887p f23984z;

    @Override // E0.f0
    public final void L() {
        this.f23979C = 9205357640488583168L;
        this.f23980D = null;
        this.f23981E = null;
        this.f23982F = null;
        AbstractC0156f.m(this);
    }

    @Override // E0.InterfaceC0165o
    public final void a(E0.H h10) {
        C1880i c1880i;
        E0.H h11;
        if (this.B == AbstractC1861K.f19381a) {
            if (!C1891t.c(this.f23983y, C1891t.f19452k)) {
                h10.c0(this.f23983y, 0L, (58 & 4) != 0 ? p0.d.s0(h10.e(), 0L) : 0L, (58 & 8) != 0 ? 1.0f : 0.0f, p0.g.f20641a, (58 & 32) != 0 ? null : null, (58 & 64) != 0 ? 3 : 0);
            }
            AbstractC1887p abstractC1887p = this.f23984z;
            if (abstractC1887p != null) {
                p0.d.X(h10, abstractC1887p, 0L, 0L, this.f23978A, null, 118);
            }
        } else {
            A7.z zVar = new A7.z();
            C2071b c2071b = h10.l;
            if (m0.f.a(c2071b.e(), this.f23979C) && h10.getLayoutDirection() == this.f23980D && A7.m.a(this.f23982F, this.B)) {
                AbstractC1858H abstractC1858H = this.f23981E;
                A7.m.c(abstractC1858H);
                zVar.l = abstractC1858H;
            } else {
                AbstractC0156f.q(this, new C0024l(zVar, this, h10, 18));
            }
            this.f23981E = (AbstractC1858H) zVar.l;
            this.f23979C = c2071b.e();
            this.f23980D = h10.getLayoutDirection();
            this.f23982F = this.B;
            Object obj = zVar.l;
            A7.m.c(obj);
            AbstractC1858H abstractC1858H2 = (AbstractC1858H) obj;
            if (!C1891t.c(this.f23983y, C1891t.f19452k)) {
                AbstractC1861K.o(h10, abstractC1858H2, this.f23983y);
            }
            AbstractC1887p abstractC1887p2 = this.f23984z;
            if (abstractC1887p2 != null) {
                float f10 = this.f23978A;
                p0.g gVar = p0.g.f20641a;
                if (abstractC1858H2 instanceof C1856F) {
                    m0.d dVar = ((C1856F) abstractC1858H2).f19376a;
                    h10.c(abstractC1887p2, h6.e.a(dVar.f18882a, dVar.f18883b), h6.j.f(dVar.c(), dVar.b()), f10, gVar, null, 3);
                } else {
                    if (abstractC1858H2 instanceof C1857G) {
                        C1857G c1857g = (C1857G) abstractC1858H2;
                        C1880i c1880i2 = c1857g.f19378b;
                        if (c1880i2 != null) {
                            h11 = h10;
                            c1880i = c1880i2;
                        } else {
                            m0.e eVar = c1857g.f19377a;
                            float fB = AbstractC1792a.b(eVar.f18889h);
                            h10.d(abstractC1887p2, h6.e.a(eVar.f18884a, eVar.f18885b), h6.j.f(eVar.b(), eVar.a()), n7.z.a(fB, fB), f10, gVar, null, 3);
                        }
                    } else {
                        if (!(abstractC1858H2 instanceof C1855E)) {
                            throw new A9.k();
                        }
                        c1880i = ((C1855E) abstractC1858H2).f19375a;
                        h11 = h10;
                    }
                    h11.I(c1880i, abstractC1887p2, f10, gVar, null, 3);
                }
            }
        }
        h10.a();
    }
}
