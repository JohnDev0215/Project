package androidx.core.widget;

import android.content.res.Resources;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import b.g.k.t;

/* JADX INFO: compiled from: AutoScrollHelper.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a implements View.OnTouchListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final int f1379b = ViewConfiguration.getTapTimeout();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final View f1382e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Runnable f1383f;
    private int i;
    private int j;
    private boolean n;
    boolean o;
    boolean p;
    boolean q;
    private boolean r;
    private boolean s;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final C0019a f1380c = new C0019a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Interpolator f1381d = new AccelerateInterpolator();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private float[] f1384g = {0.0f, 0.0f};

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private float[] f1385h = {Float.MAX_VALUE, Float.MAX_VALUE};
    private float[] k = {0.0f, 0.0f};
    private float[] l = {0.0f, 0.0f};
    private float[] m = {Float.MAX_VALUE, Float.MAX_VALUE};

    /* JADX INFO: renamed from: androidx.core.widget.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AutoScrollHelper.java */
    private static class C0019a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f1386a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f1387b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private float f1388c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private float f1389d;
        private float j;
        private int k;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private long f1390e = Long.MIN_VALUE;
        private long i = -1;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private long f1391f = 0;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private int f1392g = 0;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private int f1393h = 0;

        C0019a() {
        }

        private float e(long j) {
            if (j < this.f1390e) {
                return 0.0f;
            }
            long j2 = this.i;
            if (j2 < 0 || j < j2) {
                return a.e((j - r0) / this.f1386a, 0.0f, 1.0f) * 0.5f;
            }
            float f2 = this.j;
            return (1.0f - f2) + (f2 * a.e((j - j2) / this.k, 0.0f, 1.0f));
        }

        private float g(float f2) {
            return ((-4.0f) * f2 * f2) + (f2 * 4.0f);
        }

        public void a() {
            if (this.f1391f == 0) {
                throw new RuntimeException("Cannot compute scroll delta before calling start()");
            }
            long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            float fG = g(e(jCurrentAnimationTimeMillis));
            long j = jCurrentAnimationTimeMillis - this.f1391f;
            this.f1391f = jCurrentAnimationTimeMillis;
            float f2 = j * fG;
            this.f1392g = (int) (this.f1388c * f2);
            this.f1393h = (int) (f2 * this.f1389d);
        }

        public int b() {
            return this.f1392g;
        }

        public int c() {
            return this.f1393h;
        }

        public int d() {
            float f2 = this.f1388c;
            return (int) (f2 / Math.abs(f2));
        }

        public int f() {
            float f2 = this.f1389d;
            return (int) (f2 / Math.abs(f2));
        }

        public boolean h() {
            return this.i > 0 && AnimationUtils.currentAnimationTimeMillis() > this.i + ((long) this.k);
        }

        public void i() {
            long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            this.k = a.f((int) (jCurrentAnimationTimeMillis - this.f1390e), 0, this.f1387b);
            this.j = e(jCurrentAnimationTimeMillis);
            this.i = jCurrentAnimationTimeMillis;
        }

        public void j(int i) {
            this.f1387b = i;
        }

        public void k(int i) {
            this.f1386a = i;
        }

        public void l(float f2, float f3) {
            this.f1388c = f2;
            this.f1389d = f3;
        }

        public void m() {
            long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            this.f1390e = jCurrentAnimationTimeMillis;
            this.i = -1L;
            this.f1391f = jCurrentAnimationTimeMillis;
            this.j = 0.5f;
            this.f1392g = 0;
            this.f1393h = 0;
        }
    }

    /* JADX INFO: compiled from: AutoScrollHelper.java */
    private class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a aVar = a.this;
            if (aVar.q) {
                if (aVar.o) {
                    aVar.o = false;
                    aVar.f1380c.m();
                }
                C0019a c0019a = a.this.f1380c;
                if (c0019a.h() || !a.this.u()) {
                    a.this.q = false;
                    return;
                }
                a aVar2 = a.this;
                if (aVar2.p) {
                    aVar2.p = false;
                    aVar2.c();
                }
                c0019a.a();
                a.this.j(c0019a.b(), c0019a.c());
                t.b0(a.this.f1382e, this);
            }
        }
    }

    public a(View view) {
        this.f1382e = view;
        float f2 = Resources.getSystem().getDisplayMetrics().density;
        float f3 = (int) ((1575.0f * f2) + 0.5f);
        o(f3, f3);
        float f4 = (int) ((f2 * 315.0f) + 0.5f);
        p(f4, f4);
        l(1);
        n(Float.MAX_VALUE, Float.MAX_VALUE);
        s(0.2f, 0.2f);
        t(1.0f, 1.0f);
        k(f1379b);
        r(500);
        q(500);
    }

    private float d(int i, float f2, float f3, float f4) {
        float fH = h(this.f1384g[i], f3, this.f1385h[i], f2);
        if (fH == 0.0f) {
            return 0.0f;
        }
        float f5 = this.k[i];
        float f6 = this.l[i];
        float f7 = this.m[i];
        float f8 = f5 * f4;
        return fH > 0.0f ? e(fH * f8, f6, f7) : -e((-fH) * f8, f6, f7);
    }

    static float e(float f2, float f3, float f4) {
        return f2 > f4 ? f4 : f2 < f3 ? f3 : f2;
    }

    static int f(int i, int i2, int i3) {
        return i > i3 ? i3 : i < i2 ? i2 : i;
    }

    private float g(float f2, float f3) {
        if (f3 == 0.0f) {
            return 0.0f;
        }
        int i = this.i;
        if (i == 0 || i == 1) {
            if (f2 < f3) {
                if (f2 >= 0.0f) {
                    return 1.0f - (f2 / f3);
                }
                if (this.q && i == 1) {
                    return 1.0f;
                }
            }
        } else if (i == 2 && f2 < 0.0f) {
            return f2 / (-f3);
        }
        return 0.0f;
    }

    private float h(float f2, float f3, float f4, float f5) {
        float interpolation;
        float fE = e(f2 * f3, 0.0f, f4);
        float fG = g(f3 - f5, fE) - g(f5, fE);
        if (fG < 0.0f) {
            interpolation = -this.f1381d.getInterpolation(-fG);
        } else {
            if (fG <= 0.0f) {
                return 0.0f;
            }
            interpolation = this.f1381d.getInterpolation(fG);
        }
        return e(interpolation, -1.0f, 1.0f);
    }

    private void i() {
        if (this.o) {
            this.q = false;
        } else {
            this.f1380c.i();
        }
    }

    private void v() {
        int i;
        if (this.f1383f == null) {
            this.f1383f = new b();
        }
        this.q = true;
        this.o = true;
        if (this.n || (i = this.j) <= 0) {
            this.f1383f.run();
        } else {
            t.c0(this.f1382e, this.f1383f, i);
        }
        this.n = true;
    }

    public abstract boolean a(int i);

    public abstract boolean b(int i);

    void c() {
        long jUptimeMillis = SystemClock.uptimeMillis();
        MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
        this.f1382e.onTouchEvent(motionEventObtain);
        motionEventObtain.recycle();
    }

    public abstract void j(int i, int i2);

    public a k(int i) {
        this.j = i;
        return this;
    }

    public a l(int i) {
        this.i = i;
        return this;
    }

    public a m(boolean z) {
        if (this.r && !z) {
            i();
        }
        this.r = z;
        return this;
    }

    public a n(float f2, float f3) {
        float[] fArr = this.f1385h;
        fArr[0] = f2;
        fArr[1] = f3;
        return this;
    }

    public a o(float f2, float f3) {
        float[] fArr = this.m;
        fArr[0] = f2 / 1000.0f;
        fArr[1] = f3 / 1000.0f;
        return this;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0016  */
    @Override // android.view.View.OnTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouch(android.view.View r6, android.view.MotionEvent r7) {
        /*
            r5 = this;
            boolean r0 = r5.r
            r1 = 0
            if (r0 != 0) goto L6
            return r1
        L6:
            int r0 = r7.getActionMasked()
            r2 = 1
            if (r0 == 0) goto L1a
            if (r0 == r2) goto L16
            r3 = 2
            if (r0 == r3) goto L1e
            r6 = 3
            if (r0 == r6) goto L16
            goto L58
        L16:
            r5.i()
            goto L58
        L1a:
            r5.p = r2
            r5.n = r1
        L1e:
            float r0 = r7.getX()
            int r3 = r6.getWidth()
            float r3 = (float) r3
            android.view.View r4 = r5.f1382e
            int r4 = r4.getWidth()
            float r4 = (float) r4
            float r0 = r5.d(r1, r0, r3, r4)
            float r7 = r7.getY()
            int r6 = r6.getHeight()
            float r6 = (float) r6
            android.view.View r3 = r5.f1382e
            int r3 = r3.getHeight()
            float r3 = (float) r3
            float r6 = r5.d(r2, r7, r6, r3)
            androidx.core.widget.a$a r7 = r5.f1380c
            r7.l(r0, r6)
            boolean r6 = r5.q
            if (r6 != 0) goto L58
            boolean r6 = r5.u()
            if (r6 == 0) goto L58
            r5.v()
        L58:
            boolean r6 = r5.s
            if (r6 == 0) goto L61
            boolean r6 = r5.q
            if (r6 == 0) goto L61
            r1 = r2
        L61:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.widget.a.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    public a p(float f2, float f3) {
        float[] fArr = this.l;
        fArr[0] = f2 / 1000.0f;
        fArr[1] = f3 / 1000.0f;
        return this;
    }

    public a q(int i) {
        this.f1380c.j(i);
        return this;
    }

    public a r(int i) {
        this.f1380c.k(i);
        return this;
    }

    public a s(float f2, float f3) {
        float[] fArr = this.f1384g;
        fArr[0] = f2;
        fArr[1] = f3;
        return this;
    }

    public a t(float f2, float f3) {
        float[] fArr = this.k;
        fArr[0] = f2 / 1000.0f;
        fArr[1] = f3 / 1000.0f;
        return this;
    }

    boolean u() {
        C0019a c0019a = this.f1380c;
        int iF = c0019a.f();
        int iD = c0019a.d();
        return (iF != 0 && b(iF)) || (iD != 0 && a(iD));
    }
}
