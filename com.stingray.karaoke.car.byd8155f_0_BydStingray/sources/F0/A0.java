package F0;

import android.graphics.Canvas;
import android.graphics.RenderNode;
import android.os.Build;
import androidx.compose.ui.platform.AndroidComposeView;
import m0.AbstractC1792a;
import n0.AbstractC1858H;
import n0.AbstractC1861K;
import n0.AbstractC1874c;
import n0.C1854D;
import n0.C1855E;
import n0.C1856F;
import n0.C1857G;
import n0.C1863M;
import n0.C1870U;
import n0.C1878g;
import n0.C1880i;
import n0.C1891t;
import n0.InterfaceC1859I;
import n0.InterfaceC1889r;
import n0.InterfaceC1897z;
import p0.C2071b;
import q0.C2164c;
import q0.C2165d;
import q0.C2168g;

/* JADX INFO: loaded from: classes.dex */
public final class A0 implements E0.i0 {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public AbstractC1858H f1990A;
    public C1880i B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public C1878g f1991C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public boolean f1992D;
    public C2164c l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final InterfaceC1897z f1994m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final AndroidComposeView f1995n;
    public A.G0 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public A.I0 f1996p;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f1998r;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public float[] f2000t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f2001u;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f2005y;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public long f1997q = H7.H.c(Integer.MAX_VALUE, Integer.MAX_VALUE);

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final float[] f1999s = C1854D.a();

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public a1.b f2002v = l9.d.g();

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public a1.k f2003w = a1.k.l;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final C2071b f2004x = new C2071b();

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public long f2006z = C1870U.f19422b;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final A.F f1993E = new A.F(this, 12);

    public A0(C2164c c2164c, InterfaceC1897z interfaceC1897z, AndroidComposeView androidComposeView, A.G0 g02, A.I0 i02) {
        this.l = c2164c;
        this.f1994m = interfaceC1897z;
        this.f1995n = androidComposeView;
        this.o = g02;
        this.f1996p = i02;
    }

    @Override // E0.i0
    public final void a() {
        this.o = null;
        this.f1996p = null;
        this.f1998r = true;
        boolean z6 = this.f2001u;
        AndroidComposeView androidComposeView = this.f1995n;
        if (z6) {
            this.f2001u = false;
            androidComposeView.r(this, false);
        }
        InterfaceC1897z interfaceC1897z = this.f1994m;
        if (interfaceC1897z != null) {
            interfaceC1897z.b(this.l);
            androidComposeView.z(this);
        }
    }

    @Override // E0.i0
    public final long b(long j3, boolean z6) {
        if (!z6) {
            return C1854D.b(j3, n());
        }
        float[] fArrM = m();
        if (fArrM != null) {
            return C1854D.b(j3, fArrM);
        }
        return 9187343241974906880L;
    }

    @Override // E0.i0
    public final void c(long j3) {
        if (a1.j.a(j3, this.f1997q)) {
            return;
        }
        this.f1997q = j3;
        if (this.f2001u || this.f1998r) {
            return;
        }
        AndroidComposeView androidComposeView = this.f1995n;
        androidComposeView.invalidate();
        if (true != this.f2001u) {
            this.f2001u = true;
            androidComposeView.r(this, true);
        }
    }

    @Override // E0.i0
    public final void d(float[] fArr) {
        C1854D.g(fArr, n());
    }

    @Override // E0.i0
    public final void e(float[] fArr) {
        float[] fArrM = m();
        if (fArrM != null) {
            C1854D.g(fArr, fArrM);
        }
    }

    @Override // E0.i0
    public final void f(InterfaceC1889r interfaceC1889r, C2164c c2164c) {
        Canvas canvasA = AbstractC1874c.a(interfaceC1889r);
        if (canvasA.isHardwareAccelerated()) {
            i();
            this.f1992D = this.l.f21120a.f21148m > 0.0f;
            C2071b c2071b = this.f2004x;
            h3.y yVar = c2071b.f20638m;
            yVar.I(interfaceC1889r);
            yVar.f15500n = c2164c;
            h6.m.o(c2071b, this.l);
            return;
        }
        C2164c c2164c2 = this.l;
        long j3 = c2164c2.f21134s;
        float f10 = (int) (j3 >> 32);
        float f11 = (int) (j3 & 4294967295L);
        long j10 = this.f1997q;
        float f12 = ((int) (j10 >> 32)) + f10;
        float f13 = f11 + ((int) (j10 & 4294967295L));
        if (c2164c2.f21120a.f21143g < 1.0f) {
            C1878g c1878gH = this.f1991C;
            if (c1878gH == null) {
                c1878gH = AbstractC1861K.h();
                this.f1991C = c1878gH;
            }
            c1878gH.c(this.l.f21120a.f21143g);
            canvasA.saveLayer(f10, f11, f12, f13, c1878gH.f19432a);
        } else {
            interfaceC1889r.m();
        }
        interfaceC1889r.j(f10, f11);
        interfaceC1889r.r(n());
        C2164c c2164c3 = this.l;
        boolean z6 = c2164c3.f21137v;
        if (z6 && z6) {
            AbstractC1858H abstractC1858HC = c2164c3.c();
            if (abstractC1858HC instanceof C1856F) {
                InterfaceC1889r.q(interfaceC1889r, ((C1856F) abstractC1858HC).f19376a);
            } else if (abstractC1858HC instanceof C1857G) {
                C1880i c1880iI = this.B;
                if (c1880iI == null) {
                    c1880iI = AbstractC1861K.i();
                    this.B = c1880iI;
                }
                c1880iI.e();
                InterfaceC1859I.a(c1880iI, ((C1857G) abstractC1858HC).f19377a);
                interfaceC1889r.p(c1880iI, 1);
            } else if (abstractC1858HC instanceof C1855E) {
                interfaceC1889r.p(((C1855E) abstractC1858HC).f19375a, 1);
            }
        }
        A.G0 g02 = this.o;
        if (g02 != null) {
            g02.invoke(interfaceC1889r, null);
        }
        interfaceC1889r.k();
    }

    @Override // E0.i0
    public final void g(m0.b bVar, boolean z6) {
        if (!z6) {
            C1854D.c(n(), bVar);
            return;
        }
        float[] fArrM = m();
        if (fArrM != null) {
            C1854D.c(fArrM, bVar);
            return;
        }
        bVar.f18878a = 0.0f;
        bVar.f18879b = 0.0f;
        bVar.c = 0.0f;
        bVar.d = 0.0f;
    }

    @Override // E0.i0
    public final void h(long j3) {
        C2164c c2164c = this.l;
        if (!a1.h.b(c2164c.f21134s, j3)) {
            c2164c.f21134s = j3;
            long j10 = c2164c.f21135t;
            int i7 = (int) (j3 >> 32);
            int i10 = (int) (j3 & 4294967295L);
            C2165d c2165d = c2164c.f21120a;
            RenderNode renderNode = c2165d.c;
            renderNode.setPosition(i7, i10, ((int) (j10 >> 32)) + i7, ((int) (4294967295L & j10)) + i10);
            c2165d.d = H7.H.S(j10);
        }
        v1.f2288a.a(this.f1995n);
    }

    @Override // E0.i0
    public final void i() {
        if (this.f2001u) {
            if (!C1870U.a(this.f2006z, C1870U.f19422b) && !a1.j.a(this.l.f21135t, this.f1997q)) {
                C2164c c2164c = this.l;
                long jA = h6.e.a(C1870U.b(this.f2006z) * ((int) (this.f1997q >> 32)), C1870U.c(this.f2006z) * ((int) (this.f1997q & 4294967295L)));
                if (!m0.c.b(c2164c.f21136u, jA)) {
                    c2164c.f21136u = jA;
                    C2165d c2165d = c2164c.f21120a;
                    boolean zN = h6.e.n(jA);
                    RenderNode renderNode = c2165d.c;
                    if (zN) {
                        renderNode.resetPivot();
                    } else {
                        renderNode.setPivotX(m0.c.d(jA));
                        renderNode.setPivotY(m0.c.e(jA));
                    }
                }
            }
            C2164c c2164c2 = this.l;
            a1.b bVar = this.f2002v;
            a1.k kVar = this.f2003w;
            long j3 = this.f1997q;
            if (!a1.j.a(c2164c2.f21135t, j3)) {
                c2164c2.f21135t = j3;
                long j10 = c2164c2.f21134s;
                int i7 = (int) (j10 >> 32);
                int i10 = (int) (j10 & 4294967295L);
                C2165d c2165d2 = c2164c2.f21120a;
                c2165d2.c.setPosition(i7, i10, ((int) (j3 >> 32)) + i7, ((int) (4294967295L & j3)) + i10);
                c2165d2.d = H7.H.S(j3);
                if (c2164c2.f21126i == 9205357640488583168L) {
                    c2164c2.f21124g = true;
                    c2164c2.a();
                }
            }
            c2164c2.f21121b = bVar;
            c2164c2.c = kVar;
            c2164c2.d = this.f1993E;
            c2164c2.e();
            if (this.f2001u) {
                this.f2001u = false;
                this.f1995n.r(this, false);
            }
        }
    }

    @Override // E0.i0
    public final void invalidate() {
        if (this.f2001u || this.f1998r) {
            return;
        }
        AndroidComposeView androidComposeView = this.f1995n;
        androidComposeView.invalidate();
        if (true != this.f2001u) {
            this.f2001u = true;
            androidComposeView.r(this, true);
        }
    }

    @Override // E0.i0
    public final void j(A.G0 g02, A.I0 i02) {
        InterfaceC1897z interfaceC1897z = this.f1994m;
        if (interfaceC1897z == null) {
            throw new IllegalArgumentException("currently reuse is only supported when we manage the layer lifecycle");
        }
        if (!this.l.f21133r) {
            throw new IllegalArgumentException("layer should have been released before reuse");
        }
        this.l = interfaceC1897z.a();
        this.f1998r = false;
        this.o = g02;
        this.f1996p = i02;
        this.f2006z = C1870U.f19422b;
        this.f1992D = false;
        this.f1997q = H7.H.c(Integer.MAX_VALUE, Integer.MAX_VALUE);
        this.f1990A = null;
        this.f2005y = 0;
    }

    @Override // E0.i0
    public final void k(C1863M c1863m) {
        A.I0 i02;
        int i7;
        A.I0 i03;
        int i10 = c1863m.l | this.f2005y;
        this.f2003w = c1863m.f19385E;
        this.f2002v = c1863m.f19384D;
        int i11 = i10 & 4096;
        if (i11 != 0) {
            this.f2006z = c1863m.f19398y;
        }
        if ((i10 & 1) != 0) {
            C2164c c2164c = this.l;
            float f10 = c1863m.f19387m;
            C2165d c2165d = c2164c.f21120a;
            if (c2165d.f21145i != f10) {
                c2165d.f21145i = f10;
                c2165d.c.setScaleX(f10);
            }
        }
        if ((i10 & 2) != 0) {
            C2164c c2164c2 = this.l;
            float f11 = c1863m.f19388n;
            C2165d c2165d2 = c2164c2.f21120a;
            if (c2165d2.f21146j != f11) {
                c2165d2.f21146j = f11;
                c2165d2.c.setScaleY(f11);
            }
        }
        if ((i10 & 4) != 0) {
            this.l.f(c1863m.o);
        }
        if ((i10 & 8) != 0) {
            C2164c c2164c3 = this.l;
            float f12 = c1863m.f19389p;
            C2165d c2165d3 = c2164c3.f21120a;
            if (c2165d3.f21147k != f12) {
                c2165d3.f21147k = f12;
                c2165d3.c.setTranslationX(f12);
            }
        }
        if ((i10 & 16) != 0) {
            C2164c c2164c4 = this.l;
            float f13 = c1863m.f19390q;
            C2165d c2165d4 = c2164c4.f21120a;
            if (c2165d4.l != f13) {
                c2165d4.l = f13;
                c2165d4.c.setTranslationY(f13);
            }
        }
        boolean z6 = true;
        if ((i10 & 32) != 0) {
            C2164c c2164c5 = this.l;
            float f14 = c1863m.f19391r;
            C2165d c2165d5 = c2164c5.f21120a;
            if (c2165d5.f21148m != f14) {
                c2165d5.f21148m = f14;
                c2165d5.c.setElevation(f14);
                c2164c5.f21124g = true;
                c2164c5.a();
            }
            if (c1863m.f19391r > 0.0f && !this.f1992D && (i03 = this.f1996p) != null) {
                i03.a();
            }
        }
        if ((i10 & 64) != 0) {
            C2164c c2164c6 = this.l;
            long j3 = c1863m.f19392s;
            C2165d c2165d6 = c2164c6.f21120a;
            if (!C1891t.c(j3, c2165d6.f21149n)) {
                c2165d6.f21149n = j3;
                c2165d6.c.setAmbientShadowColor(AbstractC1861K.E(j3));
            }
        }
        if ((i10 & 128) != 0) {
            C2164c c2164c7 = this.l;
            long j10 = c1863m.f19393t;
            C2165d c2165d7 = c2164c7.f21120a;
            if (!C1891t.c(j10, c2165d7.o)) {
                c2165d7.o = j10;
                c2165d7.c.setSpotShadowColor(AbstractC1861K.E(j10));
            }
        }
        if ((i10 & 1024) != 0) {
            C2164c c2164c8 = this.l;
            float f15 = c1863m.f19396w;
            C2165d c2165d8 = c2164c8.f21120a;
            if (c2165d8.f21152r != f15) {
                c2165d8.f21152r = f15;
                c2165d8.c.setRotationZ(f15);
            }
        }
        if ((i10 & 256) != 0) {
            C2164c c2164c9 = this.l;
            float f16 = c1863m.f19394u;
            C2165d c2165d9 = c2164c9.f21120a;
            if (c2165d9.f21150p != f16) {
                c2165d9.f21150p = f16;
                c2165d9.c.setRotationX(f16);
            }
        }
        if ((i10 & 512) != 0) {
            C2164c c2164c10 = this.l;
            float f17 = c1863m.f19395v;
            C2165d c2165d10 = c2164c10.f21120a;
            if (c2165d10.f21151q != f17) {
                c2165d10.f21151q = f17;
                c2165d10.c.setRotationY(f17);
            }
        }
        if ((i10 & 2048) != 0) {
            C2164c c2164c11 = this.l;
            float f18 = c1863m.f19397x;
            C2165d c2165d11 = c2164c11.f21120a;
            if (c2165d11.f21153s != f18) {
                c2165d11.f21153s = f18;
                c2165d11.c.setCameraDistance(f18);
            }
        }
        if (i11 != 0) {
            if (C1870U.a(this.f2006z, C1870U.f19422b)) {
                C2164c c2164c12 = this.l;
                if (!m0.c.b(c2164c12.f21136u, 9205357640488583168L)) {
                    c2164c12.f21136u = 9205357640488583168L;
                    C2165d c2165d12 = c2164c12.f21120a;
                    boolean zN = h6.e.n(9205357640488583168L);
                    RenderNode renderNode = c2165d12.c;
                    if (zN) {
                        renderNode.resetPivot();
                    } else {
                        renderNode.setPivotX(m0.c.d(9205357640488583168L));
                        renderNode.setPivotY(m0.c.e(9205357640488583168L));
                    }
                }
            } else {
                C2164c c2164c13 = this.l;
                long jA = h6.e.a(C1870U.b(this.f2006z) * ((int) (this.f1997q >> 32)), C1870U.c(this.f2006z) * ((int) (this.f1997q & 4294967295L)));
                if (!m0.c.b(c2164c13.f21136u, jA)) {
                    c2164c13.f21136u = jA;
                    C2165d c2165d13 = c2164c13.f21120a;
                    boolean zN2 = h6.e.n(jA);
                    RenderNode renderNode2 = c2165d13.c;
                    if (zN2) {
                        renderNode2.resetPivot();
                    } else {
                        renderNode2.setPivotX(m0.c.d(jA));
                        renderNode2.setPivotY(m0.c.e(jA));
                    }
                }
            }
        }
        if ((i10 & 16384) != 0) {
            C2164c c2164c14 = this.l;
            boolean z10 = c1863m.f19382A;
            if (c2164c14.f21137v != z10) {
                c2164c14.f21137v = z10;
                c2164c14.f21124g = true;
                c2164c14.a();
            }
        }
        if ((131072 & i10) != 0) {
            C2165d c2165d14 = this.l.f21120a;
            if (!A7.m.a(null, null) && Build.VERSION.SDK_INT >= 31) {
                C2168g.f21160a.a(c2165d14.c, null);
            }
        }
        if ((32768 & i10) != 0) {
            C2164c c2164c15 = this.l;
            int i12 = c1863m.B;
            if (AbstractC1861K.q(i12, 0)) {
                i7 = 0;
            } else if (AbstractC1861K.q(i12, 1)) {
                i7 = 1;
            } else {
                i7 = 2;
                if (!AbstractC1861K.q(i12, 2)) {
                    throw new IllegalStateException("Not supported composition strategy");
                }
            }
            C2165d c2165d15 = c2164c15.f21120a;
            if (!h6.j.j(c2165d15.f21157w, i7)) {
                c2165d15.f21157w = i7;
                boolean zJ = h6.j.j(i7, 1);
                RenderNode renderNode3 = c2165d15.c;
                if (zJ || !AbstractC1861K.p(c2165d15.f21144h, 3)) {
                    C2165d.b(renderNode3, 1);
                } else {
                    C2165d.b(renderNode3, c2165d15.f21157w);
                }
            }
        }
        if (A7.m.a(this.f1990A, c1863m.f19386F)) {
            z6 = false;
        } else {
            AbstractC1858H abstractC1858H = c1863m.f19386F;
            this.f1990A = abstractC1858H;
            if (abstractC1858H != null) {
                C2164c c2164c16 = this.l;
                if (abstractC1858H instanceof C1856F) {
                    m0.d dVar = ((C1856F) abstractC1858H).f19376a;
                    c2164c16.g(h6.e.a(dVar.f18882a, dVar.f18883b), h6.j.f(dVar.c(), dVar.b()), 0.0f);
                } else if (abstractC1858H instanceof C1855E) {
                    c2164c16.f21128k = null;
                    c2164c16.f21126i = 9205357640488583168L;
                    c2164c16.f21125h = 0L;
                    c2164c16.f21127j = 0.0f;
                    c2164c16.f21124g = true;
                    c2164c16.f21130n = false;
                    c2164c16.l = ((C1855E) abstractC1858H).f19375a;
                    c2164c16.a();
                } else if (abstractC1858H instanceof C1857G) {
                    C1857G c1857g = (C1857G) abstractC1858H;
                    C1880i c1880i = c1857g.f19378b;
                    if (c1880i != null) {
                        c2164c16.f21128k = null;
                        c2164c16.f21126i = 9205357640488583168L;
                        c2164c16.f21125h = 0L;
                        c2164c16.f21127j = 0.0f;
                        c2164c16.f21124g = true;
                        c2164c16.f21130n = false;
                        c2164c16.l = c1880i;
                        c2164c16.a();
                    } else {
                        m0.e eVar = c1857g.f19377a;
                        c2164c16.g(h6.e.a(eVar.f18884a, eVar.f18885b), h6.j.f(eVar.b(), eVar.a()), AbstractC1792a.b(eVar.f18889h));
                    }
                }
                if ((abstractC1858H instanceof C1855E) && Build.VERSION.SDK_INT < 33 && (i02 = this.f1996p) != null) {
                    i02.a();
                }
            }
        }
        this.f2005y = c1863m.l;
        if (i10 != 0 || z6) {
            v1.f2288a.a(this.f1995n);
        }
    }

    @Override // E0.i0
    public final boolean l(long j3) {
        float fD = m0.c.d(j3);
        float fE = m0.c.e(j3);
        C2164c c2164c = this.l;
        if (c2164c.f21137v) {
            return T.u(c2164c.c(), fD, fE, null, null);
        }
        return true;
    }

    public final float[] m() {
        float[] fArrN = n();
        float[] fArrA = this.f2000t;
        if (fArrA == null) {
            fArrA = C1854D.a();
            this.f2000t = fArrA;
        }
        if (T.s(fArrN, fArrA)) {
            return fArrA;
        }
        return null;
    }

    public final float[] n() {
        C2164c c2164c = this.l;
        long jK = h6.e.n(c2164c.f21136u) ? h6.j.k(H7.H.S(this.f1997q)) : c2164c.f21136u;
        float[] fArr = this.f1999s;
        C1854D.d(fArr);
        float[] fArrA = C1854D.a();
        C1854D.h(-m0.c.d(jK), -m0.c.e(jK), 0.0f, fArrA);
        C1854D.g(fArr, fArrA);
        float[] fArrA2 = C1854D.a();
        C2165d c2165d = c2164c.f21120a;
        C1854D.h(c2165d.f21147k, c2165d.l, 0.0f, fArrA2);
        double d = (((double) c2165d.f21150p) * 3.141592653589793d) / 180.0d;
        float fCos = (float) Math.cos(d);
        float fSin = (float) Math.sin(d);
        float f10 = fArrA2[1];
        float f11 = fArrA2[2];
        float f12 = fArrA2[5];
        float f13 = fArrA2[6];
        float f14 = fArrA2[9];
        float f15 = fArrA2[10];
        float f16 = fArrA2[13];
        float f17 = fArrA2[14];
        fArrA2[1] = (f10 * fCos) - (f11 * fSin);
        fArrA2[2] = (f11 * fCos) + (f10 * fSin);
        fArrA2[5] = (f12 * fCos) - (f13 * fSin);
        fArrA2[6] = (f13 * fCos) + (f12 * fSin);
        fArrA2[9] = (f14 * fCos) - (f15 * fSin);
        fArrA2[10] = (f15 * fCos) + (f14 * fSin);
        fArrA2[13] = (f16 * fCos) - (f17 * fSin);
        fArrA2[14] = (f17 * fCos) + (f16 * fSin);
        double d10 = (((double) c2165d.f21151q) * 3.141592653589793d) / 180.0d;
        float fCos2 = (float) Math.cos(d10);
        float fSin2 = (float) Math.sin(d10);
        float f18 = fArrA2[0];
        float f19 = fArrA2[2];
        float f20 = fArrA2[4];
        float f21 = fArrA2[6];
        float f22 = fArrA2[8];
        float f23 = fArrA2[10];
        float f24 = fArrA2[12];
        float f25 = fArrA2[14];
        fArrA2[0] = (f19 * fSin2) + (f18 * fCos2);
        fArrA2[2] = (f19 * fCos2) + ((-f18) * fSin2);
        fArrA2[4] = (f21 * fSin2) + (f20 * fCos2);
        fArrA2[6] = (f21 * fCos2) + ((-f20) * fSin2);
        fArrA2[8] = (f23 * fSin2) + (f22 * fCos2);
        fArrA2[10] = (f23 * fCos2) + ((-f22) * fSin2);
        fArrA2[12] = (f25 * fSin2) + (f24 * fCos2);
        fArrA2[14] = (f25 * fCos2) + ((-f24) * fSin2);
        C1854D.e(fArrA2, c2165d.f21152r);
        C1854D.f(c2165d.f21145i, c2165d.f21146j, 1.0f, fArrA2);
        C1854D.g(fArr, fArrA2);
        float[] fArrA3 = C1854D.a();
        C1854D.h(m0.c.d(jK), m0.c.e(jK), 0.0f, fArrA3);
        C1854D.g(fArr, fArrA3);
        return fArr;
    }
}
