package F0;

import android.graphics.Canvas;
import android.graphics.RenderNode;
import android.os.Build;
import androidx.compose.ui.platform.AndroidComposeView;
import l3.C1764u;
import n0.AbstractC1858H;
import n0.AbstractC1861K;
import n0.AbstractC1874c;
import n0.C1854D;
import n0.C1863M;
import n0.C1870U;
import n0.C1878g;
import n0.InterfaceC1889r;
import q0.C2164c;

/* JADX INFO: loaded from: classes.dex */
public final class N0 implements E0.i0 {
    public final AndroidComposeView l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public A.G0 f2087m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public A.I0 f2088n;
    public boolean o;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f2090q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f2091r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public C1878g f2092s;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final L0 f2096w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f2097x;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final H0 f2089p = new H0();

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final E0 f2093t = new E0(L.f2048p);

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final C1764u f2094u = new C1764u(6);

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public long f2095v = C1870U.f19422b;

    public N0(AndroidComposeView androidComposeView, A.G0 g02, A.I0 i02) {
        this.l = androidComposeView;
        this.f2087m = g02;
        this.f2088n = i02;
        L0 l02 = new L0();
        RenderNode renderNode = l02.f2051a;
        renderNode.setHasOverlappingRendering(true);
        renderNode.setClipToBounds(false);
        this.f2096w = l02;
    }

    @Override // E0.i0
    public final void a() {
        L0 l02 = this.f2096w;
        if (l02.f2051a.hasDisplayList()) {
            l02.f2051a.discardDisplayList();
        }
        this.f2087m = null;
        this.f2088n = null;
        this.f2090q = true;
        m(false);
        AndroidComposeView androidComposeView = this.l;
        androidComposeView.f11635K = true;
        androidComposeView.z(this);
    }

    @Override // E0.i0
    public final long b(long j3, boolean z6) {
        L0 l02 = this.f2096w;
        E0 e02 = this.f2093t;
        if (!z6) {
            return C1854D.b(j3, e02.b(l02));
        }
        float[] fArrA = e02.a(l02);
        if (fArrA != null) {
            return C1854D.b(j3, fArrA);
        }
        return 9187343241974906880L;
    }

    @Override // E0.i0
    public final void c(long j3) {
        int i7 = (int) (j3 >> 32);
        int i10 = (int) (j3 & 4294967295L);
        float fB = C1870U.b(this.f2095v) * i7;
        L0 l02 = this.f2096w;
        l02.f2051a.setPivotX(fB);
        l02.f2051a.setPivotY(C1870U.c(this.f2095v) * i10);
        if (l02.f2051a.setPosition(l02.f2051a.getLeft(), l02.f2051a.getTop(), l02.f2051a.getLeft() + i7, l02.f2051a.getTop() + i10)) {
            l02.f2051a.setOutline(this.f2089p.b());
            if (!this.o && !this.f2090q) {
                this.l.invalidate();
                m(true);
            }
            this.f2093t.c();
        }
    }

    @Override // E0.i0
    public final void d(float[] fArr) {
        C1854D.g(fArr, this.f2093t.b(this.f2096w));
    }

    @Override // E0.i0
    public final void e(float[] fArr) {
        float[] fArrA = this.f2093t.a(this.f2096w);
        if (fArrA != null) {
            C1854D.g(fArr, fArrA);
        }
    }

    @Override // E0.i0
    public final void f(InterfaceC1889r interfaceC1889r, C2164c c2164c) {
        Canvas canvasA = AbstractC1874c.a(interfaceC1889r);
        boolean zIsHardwareAccelerated = canvasA.isHardwareAccelerated();
        L0 l02 = this.f2096w;
        if (zIsHardwareAccelerated) {
            i();
            boolean z6 = l02.f2051a.getElevation() > 0.0f;
            this.f2091r = z6;
            if (z6) {
                interfaceC1889r.s();
            }
            canvasA.drawRenderNode(l02.f2051a);
            if (this.f2091r) {
                interfaceC1889r.o();
                return;
            }
            return;
        }
        float left = l02.f2051a.getLeft();
        float top = l02.f2051a.getTop();
        float right = l02.f2051a.getRight();
        float bottom = l02.f2051a.getBottom();
        if (l02.f2051a.getAlpha() < 1.0f) {
            C1878g c1878gH = this.f2092s;
            if (c1878gH == null) {
                c1878gH = AbstractC1861K.h();
                this.f2092s = c1878gH;
            }
            c1878gH.c(l02.f2051a.getAlpha());
            canvasA.saveLayer(left, top, right, bottom, c1878gH.f19432a);
        } else {
            interfaceC1889r.m();
        }
        interfaceC1889r.j(left, top);
        interfaceC1889r.r(this.f2093t.b(l02));
        if (l02.f2051a.getClipToOutline() || l02.f2051a.getClipToBounds()) {
            this.f2089p.a(interfaceC1889r);
        }
        A.G0 g02 = this.f2087m;
        if (g02 != null) {
            g02.invoke(interfaceC1889r, null);
        }
        interfaceC1889r.k();
        m(false);
    }

    @Override // E0.i0
    public final void g(m0.b bVar, boolean z6) {
        L0 l02 = this.f2096w;
        E0 e02 = this.f2093t;
        if (!z6) {
            C1854D.c(e02.b(l02), bVar);
            return;
        }
        float[] fArrA = e02.a(l02);
        if (fArrA != null) {
            C1854D.c(fArrA, bVar);
            return;
        }
        bVar.f18878a = 0.0f;
        bVar.f18879b = 0.0f;
        bVar.c = 0.0f;
        bVar.d = 0.0f;
    }

    @Override // E0.i0
    public final void h(long j3) {
        L0 l02 = this.f2096w;
        int left = l02.f2051a.getLeft();
        int top = l02.f2051a.getTop();
        int i7 = (int) (j3 >> 32);
        int i10 = (int) (j3 & 4294967295L);
        if (left == i7 && top == i10) {
            return;
        }
        if (left != i7) {
            l02.f2051a.offsetLeftAndRight(i7 - left);
        }
        if (top != i10) {
            l02.f2051a.offsetTopAndBottom(i10 - top);
        }
        v1.f2288a.a(this.l);
        this.f2093t.c();
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
    @Override // E0.i0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void i() {
        /*
            r8 = this;
            boolean r0 = r8.o
            F0.L0 r1 = r8.f2096w
            if (r0 != 0) goto Le
            android.graphics.RenderNode r0 = r1.f2051a
            boolean r0 = r0.hasDisplayList()
            if (r0 != 0) goto L56
        Le:
            android.graphics.RenderNode r0 = r1.f2051a
            boolean r0 = r0.getClipToOutline()
            r2 = 0
            if (r0 == 0) goto L23
            F0.H0 r0 = r8.f2089p
            boolean r3 = r0.f2031f
            if (r3 == 0) goto L23
            r0.d()
            n0.I r0 = r0.d
            goto L24
        L23:
            r0 = r2
        L24:
            A.G0 r3 = r8.f2087m
            if (r3 == 0) goto L52
            android.graphics.RenderNode r1 = r1.f2051a
            android.graphics.RecordingCanvas r4 = r1.beginRecording()
            l3.u r5 = r8.f2094u
            java.lang.Object r6 = r5.f18608m
            n0.b r6 = (n0.C1873b) r6
            android.graphics.Canvas r7 = r6.f19426a
            r6.f19426a = r4
            if (r0 == 0) goto L41
            r6.m()
            r4 = 1
            r6.p(r0, r4)
        L41:
            r3.invoke(r6, r2)
            if (r0 == 0) goto L49
            r6.k()
        L49:
            java.lang.Object r0 = r5.f18608m
            n0.b r0 = (n0.C1873b) r0
            r0.f19426a = r7
            r1.endRecording()
        L52:
            r0 = 0
            r8.m(r0)
        L56:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: F0.N0.i():void");
    }

    @Override // E0.i0
    public final void invalidate() {
        if (this.o || this.f2090q) {
            return;
        }
        this.l.invalidate();
        m(true);
    }

    @Override // E0.i0
    public final void j(A.G0 g02, A.I0 i02) {
        m(false);
        this.f2090q = false;
        this.f2091r = false;
        this.f2095v = C1870U.f19422b;
        this.f2087m = g02;
        this.f2088n = i02;
    }

    @Override // E0.i0
    public final void k(C1863M c1863m) {
        A.I0 i02;
        int i7 = c1863m.l | this.f2097x;
        int i10 = i7 & 4096;
        if (i10 != 0) {
            this.f2095v = c1863m.f19398y;
        }
        L0 l02 = this.f2096w;
        boolean clipToOutline = l02.f2051a.getClipToOutline();
        H0 h02 = this.f2089p;
        boolean z6 = false;
        boolean z10 = clipToOutline && h02.f2031f;
        if ((i7 & 1) != 0) {
            l02.f2051a.setScaleX(c1863m.f19387m);
        }
        if ((i7 & 2) != 0) {
            l02.f2051a.setScaleY(c1863m.f19388n);
        }
        if ((i7 & 4) != 0) {
            l02.f2051a.setAlpha(c1863m.o);
        }
        if ((i7 & 8) != 0) {
            l02.f2051a.setTranslationX(c1863m.f19389p);
        }
        if ((i7 & 16) != 0) {
            l02.f2051a.setTranslationY(c1863m.f19390q);
        }
        if ((i7 & 32) != 0) {
            l02.f2051a.setElevation(c1863m.f19391r);
        }
        if ((i7 & 64) != 0) {
            l02.f2051a.setAmbientShadowColor(AbstractC1861K.E(c1863m.f19392s));
        }
        if ((i7 & 128) != 0) {
            l02.f2051a.setSpotShadowColor(AbstractC1861K.E(c1863m.f19393t));
        }
        if ((i7 & 1024) != 0) {
            l02.f2051a.setRotationZ(c1863m.f19396w);
        }
        if ((i7 & 256) != 0) {
            l02.f2051a.setRotationX(c1863m.f19394u);
        }
        if ((i7 & 512) != 0) {
            l02.f2051a.setRotationY(c1863m.f19395v);
        }
        if ((i7 & 2048) != 0) {
            l02.f2051a.setCameraDistance(c1863m.f19397x);
        }
        if (i10 != 0) {
            l02.f2051a.setPivotX(C1870U.b(this.f2095v) * l02.f2051a.getWidth());
            l02.f2051a.setPivotY(C1870U.c(this.f2095v) * l02.f2051a.getHeight());
        }
        boolean z11 = c1863m.f19382A;
        l9.n nVar = AbstractC1861K.f19381a;
        boolean z12 = z11 && c1863m.f19399z != nVar;
        if ((i7 & 24576) != 0) {
            l02.f2051a.setClipToOutline(z12);
            l02.f2051a.setClipToBounds(c1863m.f19382A && c1863m.f19399z == nVar);
        }
        if ((131072 & i7) != 0) {
            if (Build.VERSION.SDK_INT >= 31) {
                M0.f2085a.a(l02.f2051a, null);
            } else {
                l02.getClass();
            }
        }
        if ((32768 & i7) != 0) {
            int i11 = c1863m.B;
            boolean zQ = AbstractC1861K.q(i11, 1);
            RenderNode renderNode = l02.f2051a;
            if (zQ) {
                renderNode.setUseCompositingLayer(true, null);
                renderNode.setHasOverlappingRendering(true);
            } else if (AbstractC1861K.q(i11, 2)) {
                renderNode.setUseCompositingLayer(false, null);
                renderNode.setHasOverlappingRendering(false);
            } else {
                renderNode.setUseCompositingLayer(false, null);
                renderNode.setHasOverlappingRendering(true);
            }
        }
        boolean zC = this.f2089p.c(c1863m.f19386F, c1863m.o, z12, c1863m.f19391r, c1863m.f19383C);
        if (h02.f2030e) {
            l02.f2051a.setOutline(h02.b());
        }
        if (z12 && h02.f2031f) {
            z6 = true;
        }
        AndroidComposeView androidComposeView = this.l;
        if (z10 == z6 && (!z6 || !zC)) {
            v1.f2288a.a(androidComposeView);
        } else if (!this.o && !this.f2090q) {
            androidComposeView.invalidate();
            m(true);
        }
        if (!this.f2091r && l02.f2051a.getElevation() > 0.0f && (i02 = this.f2088n) != null) {
            i02.a();
        }
        if ((i7 & 7963) != 0) {
            this.f2093t.c();
        }
        this.f2097x = c1863m.l;
    }

    @Override // E0.i0
    public final boolean l(long j3) {
        AbstractC1858H abstractC1858H;
        float fD = m0.c.d(j3);
        float fE = m0.c.e(j3);
        L0 l02 = this.f2096w;
        if (l02.f2051a.getClipToBounds()) {
            return 0.0f <= fD && fD < ((float) l02.f2051a.getWidth()) && 0.0f <= fE && fE < ((float) l02.f2051a.getHeight());
        }
        if (!l02.f2051a.getClipToOutline()) {
            return true;
        }
        H0 h02 = this.f2089p;
        if (h02.l && (abstractC1858H = h02.f2029b) != null) {
            return T.u(abstractC1858H, m0.c.d(j3), m0.c.e(j3), null, null);
        }
        return true;
    }

    public final void m(boolean z6) {
        if (z6 != this.o) {
            this.o = z6;
            this.l.r(this, z6);
        }
    }
}
