package F0;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import androidx.compose.ui.platform.AndroidComposeView;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import l3.C1764u;
import n0.AbstractC1858H;
import n0.AbstractC1861K;
import n0.C1854D;
import n0.C1863M;
import n0.C1870U;
import n0.C1873b;
import n0.InterfaceC1859I;
import n0.InterfaceC1889r;
import q0.C2164c;

/* JADX INFO: loaded from: classes.dex */
public final class Z0 extends View implements E0.i0 {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public static final X0 f2151A = new X0(0);
    public static Method B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public static Field f2152C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public static boolean f2153D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public static boolean f2154E;
    public final AndroidComposeView l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final C0237x0 f2155m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public A.G0 f2156n;
    public A.I0 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final H0 f2157p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f2158q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public Rect f2159r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f2160s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f2161t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final C1764u f2162u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final E0 f2163v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public long f2164w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f2165x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final long f2166y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public int f2167z;

    public Z0(AndroidComposeView androidComposeView, C0237x0 c0237x0, A.G0 g02, A.I0 i02) {
        super(androidComposeView.getContext());
        this.l = androidComposeView;
        this.f2155m = c0237x0;
        this.f2156n = g02;
        this.o = i02;
        this.f2157p = new H0();
        this.f2162u = new C1764u(6);
        this.f2163v = new E0(L.f2049q);
        this.f2164w = C1870U.f19422b;
        this.f2165x = true;
        setWillNotDraw(false);
        c0237x0.addView(this);
        this.f2166y = View.generateViewId();
    }

    private final InterfaceC1859I getManualClipPath() {
        if (getClipToOutline()) {
            H0 h02 = this.f2157p;
            if (h02.f2031f) {
                h02.d();
                return h02.d;
            }
        }
        return null;
    }

    private final void setInvalidated(boolean z6) {
        if (z6 != this.f2160s) {
            this.f2160s = z6;
            this.l.r(this, z6);
        }
    }

    @Override // E0.i0
    public final void a() {
        setInvalidated(false);
        AndroidComposeView androidComposeView = this.l;
        androidComposeView.f11635K = true;
        this.f2156n = null;
        this.o = null;
        androidComposeView.z(this);
        this.f2155m.removeViewInLayout(this);
    }

    @Override // E0.i0
    public final long b(long j3, boolean z6) {
        E0 e02 = this.f2163v;
        if (!z6) {
            return C1854D.b(j3, e02.b(this));
        }
        float[] fArrA = e02.a(this);
        if (fArrA != null) {
            return C1854D.b(j3, fArrA);
        }
        return 9187343241974906880L;
    }

    @Override // E0.i0
    public final void c(long j3) {
        int i7 = (int) (j3 >> 32);
        int i10 = (int) (j3 & 4294967295L);
        if (i7 == getWidth() && i10 == getHeight()) {
            return;
        }
        setPivotX(C1870U.b(this.f2164w) * i7);
        setPivotY(C1870U.c(this.f2164w) * i10);
        setOutlineProvider(this.f2157p.b() != null ? f2151A : null);
        layout(getLeft(), getTop(), getLeft() + i7, getTop() + i10);
        m();
        this.f2163v.c();
    }

    @Override // E0.i0
    public final void d(float[] fArr) {
        C1854D.g(fArr, this.f2163v.b(this));
    }

    @Override // android.view.View
    public final void dispatchDraw(Canvas canvas) {
        boolean z6;
        C1764u c1764u = this.f2162u;
        C1873b c1873b = (C1873b) c1764u.f18608m;
        Canvas canvas2 = c1873b.f19426a;
        c1873b.f19426a = canvas;
        if (getManualClipPath() == null && canvas.isHardwareAccelerated()) {
            z6 = false;
        } else {
            c1873b.m();
            this.f2157p.a(c1873b);
            z6 = true;
        }
        A.G0 g02 = this.f2156n;
        if (g02 != null) {
            g02.invoke(c1873b, null);
        }
        if (z6) {
            c1873b.k();
        }
        ((C1873b) c1764u.f18608m).f19426a = canvas2;
        setInvalidated(false);
    }

    @Override // E0.i0
    public final void e(float[] fArr) {
        float[] fArrA = this.f2163v.a(this);
        if (fArrA != null) {
            C1854D.g(fArr, fArrA);
        }
    }

    @Override // E0.i0
    public final void f(InterfaceC1889r interfaceC1889r, C2164c c2164c) {
        boolean z6 = getElevation() > 0.0f;
        this.f2161t = z6;
        if (z6) {
            interfaceC1889r.s();
        }
        this.f2155m.a(interfaceC1889r, this, getDrawingTime());
        if (this.f2161t) {
            interfaceC1889r.o();
        }
    }

    @Override // android.view.View
    public final void forceLayout() {
    }

    @Override // E0.i0
    public final void g(m0.b bVar, boolean z6) {
        E0 e02 = this.f2163v;
        if (!z6) {
            C1854D.c(e02.b(this), bVar);
            return;
        }
        float[] fArrA = e02.a(this);
        if (fArrA != null) {
            C1854D.c(fArrA, bVar);
            return;
        }
        bVar.f18878a = 0.0f;
        bVar.f18879b = 0.0f;
        bVar.c = 0.0f;
        bVar.d = 0.0f;
    }

    public final float getCameraDistancePx() {
        return getCameraDistance() / getResources().getDisplayMetrics().densityDpi;
    }

    public final C0237x0 getContainer() {
        return this.f2155m;
    }

    public long getLayerId() {
        return this.f2166y;
    }

    public final AndroidComposeView getOwnerView() {
        return this.l;
    }

    public long getOwnerViewId() {
        return Y0.a(this.l);
    }

    @Override // E0.i0
    public final void h(long j3) {
        int i7 = (int) (j3 >> 32);
        int left = getLeft();
        E0 e02 = this.f2163v;
        if (i7 != left) {
            offsetLeftAndRight(i7 - getLeft());
            e02.c();
        }
        int i10 = (int) (j3 & 4294967295L);
        if (i10 != getTop()) {
            offsetTopAndBottom(i10 - getTop());
            e02.c();
        }
    }

    @Override // android.view.View
    public final boolean hasOverlappingRendering() {
        return this.f2165x;
    }

    @Override // E0.i0
    public final void i() {
        if (!this.f2160s || f2154E) {
            return;
        }
        T.B(this);
        setInvalidated(false);
    }

    @Override // android.view.View, E0.i0
    public final void invalidate() {
        if (this.f2160s) {
            return;
        }
        setInvalidated(true);
        super.invalidate();
        this.l.invalidate();
    }

    @Override // E0.i0
    public final void j(A.G0 g02, A.I0 i02) {
        this.f2155m.addView(this);
        this.f2158q = false;
        this.f2161t = false;
        this.f2164w = C1870U.f19422b;
        this.f2156n = g02;
        this.o = i02;
    }

    @Override // E0.i0
    public final void k(C1863M c1863m) {
        A.I0 i02;
        int i7 = c1863m.l | this.f2167z;
        if ((i7 & 4096) != 0) {
            long j3 = c1863m.f19398y;
            this.f2164w = j3;
            setPivotX(C1870U.b(j3) * getWidth());
            setPivotY(C1870U.c(this.f2164w) * getHeight());
        }
        if ((i7 & 1) != 0) {
            setScaleX(c1863m.f19387m);
        }
        if ((i7 & 2) != 0) {
            setScaleY(c1863m.f19388n);
        }
        if ((i7 & 4) != 0) {
            setAlpha(c1863m.o);
        }
        if ((i7 & 8) != 0) {
            setTranslationX(c1863m.f19389p);
        }
        if ((i7 & 16) != 0) {
            setTranslationY(c1863m.f19390q);
        }
        if ((i7 & 32) != 0) {
            setElevation(c1863m.f19391r);
        }
        if ((i7 & 1024) != 0) {
            setRotation(c1863m.f19396w);
        }
        if ((i7 & 256) != 0) {
            setRotationX(c1863m.f19394u);
        }
        if ((i7 & 512) != 0) {
            setRotationY(c1863m.f19395v);
        }
        if ((i7 & 2048) != 0) {
            setCameraDistancePx(c1863m.f19397x);
        }
        boolean z6 = true;
        boolean z10 = getManualClipPath() != null;
        boolean z11 = c1863m.f19382A;
        l9.n nVar = AbstractC1861K.f19381a;
        boolean z12 = z11 && c1863m.f19399z != nVar;
        if ((i7 & 24576) != 0) {
            this.f2158q = z11 && c1863m.f19399z == nVar;
            m();
            setClipToOutline(z12);
        }
        boolean zC = this.f2157p.c(c1863m.f19386F, c1863m.o, z12, c1863m.f19391r, c1863m.f19383C);
        H0 h02 = this.f2157p;
        if (h02.f2030e) {
            setOutlineProvider(h02.b() != null ? f2151A : null);
        }
        boolean z13 = getManualClipPath() != null;
        if (z10 != z13 || (z13 && zC)) {
            invalidate();
        }
        if (!this.f2161t && getElevation() > 0.0f && (i02 = this.o) != null) {
            i02.a();
        }
        if ((i7 & 7963) != 0) {
            this.f2163v.c();
        }
        int i10 = Build.VERSION.SDK_INT;
        int i11 = i7 & 64;
        b1 b1Var = b1.f2179a;
        if (i11 != 0) {
            b1Var.a(this, AbstractC1861K.E(c1863m.f19392s));
        }
        if ((i7 & 128) != 0) {
            b1Var.b(this, AbstractC1861K.E(c1863m.f19393t));
        }
        if (i10 >= 31 && (131072 & i7) != 0) {
            c1.f2186a.a(this, null);
        }
        if ((i7 & 32768) != 0) {
            int i12 = c1863m.B;
            if (AbstractC1861K.q(i12, 1)) {
                setLayerType(2, null);
            } else if (AbstractC1861K.q(i12, 2)) {
                setLayerType(0, null);
                z6 = false;
            } else {
                setLayerType(0, null);
            }
            this.f2165x = z6;
        }
        this.f2167z = c1863m.l;
    }

    @Override // E0.i0
    public final boolean l(long j3) {
        AbstractC1858H abstractC1858H;
        float fD = m0.c.d(j3);
        float fE = m0.c.e(j3);
        if (this.f2158q) {
            return 0.0f <= fD && fD < ((float) getWidth()) && 0.0f <= fE && fE < ((float) getHeight());
        }
        if (!getClipToOutline()) {
            return true;
        }
        H0 h02 = this.f2157p;
        if (h02.l && (abstractC1858H = h02.f2029b) != null) {
            return T.u(abstractC1858H, m0.c.d(j3), m0.c.e(j3), null, null);
        }
        return true;
    }

    public final void m() {
        Rect rect;
        if (this.f2158q) {
            Rect rect2 = this.f2159r;
            if (rect2 == null) {
                this.f2159r = new Rect(0, 0, getWidth(), getHeight());
            } else {
                A7.m.c(rect2);
                rect2.set(0, 0, getWidth(), getHeight());
            }
            rect = this.f2159r;
        } else {
            rect = null;
        }
        setClipBounds(rect);
    }

    @Override // android.view.View
    public final void onLayout(boolean z6, int i7, int i10, int i11, int i12) {
    }

    public final void setCameraDistancePx(float f10) {
        setCameraDistance(f10 * getResources().getDisplayMetrics().densityDpi);
    }
}
