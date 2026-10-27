package androidx.core.widget;

import android.content.res.Resources;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import i0.u;

/* JADX INFO: compiled from: AutoScrollHelper.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a implements View.OnTouchListener {

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private static final int f1988w = ViewConfiguration.getTapTimeout();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    final View f1991h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private Runnable f1992i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private int f1995l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private int f1996m;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private boolean f2000q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    boolean f2001r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    boolean f2002s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    boolean f2003t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private boolean f2004u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private boolean f2005v;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final C0019a f1989e = new C0019a();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final Interpolator f1990g = new AccelerateInterpolator();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private float[] f1993j = {0.0f, 0.0f};

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private float[] f1994k = {Float.MAX_VALUE, Float.MAX_VALUE};

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private float[] f1997n = {0.0f, 0.0f};

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private float[] f1998o = {0.0f, 0.0f};

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private float[] f1999p = {Float.MAX_VALUE, Float.MAX_VALUE};

    /* JADX INFO: renamed from: androidx.core.widget.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AutoScrollHelper.java */
    private static class C0019a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f2006a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f2007b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private float f2008c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private float f2009d;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        private float f2015j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        private int f2016k;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private long f2010e = Long.MIN_VALUE;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private long f2014i = -1;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private long f2011f = 0;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private int f2012g = 0;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private int f2013h = 0;

        C0019a() {
        }

        private float e(long j7) {
            if (j7 < this.f2010e) {
                return 0.0f;
            }
            long j8 = this.f2014i;
            if (j8 < 0 || j7 < j8) {
                return a.e((j7 - r0) / this.f2006a, 0.0f, 1.0f) * 0.5f;
            }
            float f7 = this.f2015j;
            return (1.0f - f7) + (f7 * a.e((j7 - j8) / this.f2016k, 0.0f, 1.0f));
        }

        private float g(float f7) {
            return ((-4.0f) * f7 * f7) + (f7 * 4.0f);
        }

        public void a() {
            if (this.f2011f == 0) {
                throw new RuntimeException("Cannot compute scroll delta before calling start()");
            }
            long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            float fG = g(e(jCurrentAnimationTimeMillis));
            long j7 = jCurrentAnimationTimeMillis - this.f2011f;
            this.f2011f = jCurrentAnimationTimeMillis;
            float f7 = j7 * fG;
            this.f2012g = (int) (this.f2008c * f7);
            this.f2013h = (int) (f7 * this.f2009d);
        }

        public int b() {
            return this.f2012g;
        }

        public int c() {
            return this.f2013h;
        }

        public int d() {
            float f7 = this.f2008c;
            return (int) (f7 / Math.abs(f7));
        }

        public int f() {
            float f7 = this.f2009d;
            return (int) (f7 / Math.abs(f7));
        }

        public boolean h() {
            return this.f2014i > 0 && AnimationUtils.currentAnimationTimeMillis() > this.f2014i + ((long) this.f2016k);
        }

        public void i() {
            long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            this.f2016k = a.f((int) (jCurrentAnimationTimeMillis - this.f2010e), 0, this.f2007b);
            this.f2015j = e(jCurrentAnimationTimeMillis);
            this.f2014i = jCurrentAnimationTimeMillis;
        }

        public void j(int i7) {
            this.f2007b = i7;
        }

        public void k(int i7) {
            this.f2006a = i7;
        }

        public void l(float f7, float f8) {
            this.f2008c = f7;
            this.f2009d = f8;
        }

        public void m() {
            long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            this.f2010e = jCurrentAnimationTimeMillis;
            this.f2014i = -1L;
            this.f2011f = jCurrentAnimationTimeMillis;
            this.f2015j = 0.5f;
            this.f2012g = 0;
            this.f2013h = 0;
        }
    }

    /* JADX INFO: compiled from: AutoScrollHelper.java */
    private class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a aVar = a.this;
            if (aVar.f2003t) {
                if (aVar.f2001r) {
                    aVar.f2001r = false;
                    aVar.f1989e.m();
                }
                C0019a c0019a = a.this.f1989e;
                if (c0019a.h() || !a.this.u()) {
                    a.this.f2003t = false;
                    return;
                }
                a aVar2 = a.this;
                if (aVar2.f2002s) {
                    aVar2.f2002s = false;
                    aVar2.c();
                }
                c0019a.a();
                a.this.j(c0019a.b(), c0019a.c());
                u.c0(a.this.f1991h, this);
            }
        }
    }

    public a(View view) {
        this.f1991h = view;
        float f7 = Resources.getSystem().getDisplayMetrics().density;
        float f8 = (int) ((1575.0f * f7) + 0.5f);
        o(f8, f8);
        float f9 = (int) ((f7 * 315.0f) + 0.5f);
        p(f9, f9);
        l(1);
        n(Float.MAX_VALUE, Float.MAX_VALUE);
        s(0.2f, 0.2f);
        t(1.0f, 1.0f);
        k(f1988w);
        r(500);
        q(500);
    }

    private float d(int i7, float f7, float f8, float f9) {
        float fH = h(this.f1993j[i7], f8, this.f1994k[i7], f7);
        if (fH == 0.0f) {
            return 0.0f;
        }
        float f10 = this.f1997n[i7];
        float f11 = this.f1998o[i7];
        float f12 = this.f1999p[i7];
        float f13 = f10 * f9;
        return fH > 0.0f ? e(fH * f13, f11, f12) : -e((-fH) * f13, f11, f12);
    }

    static float e(float f7, float f8, float f9) {
        return f7 > f9 ? f9 : f7 < f8 ? f8 : f7;
    }

    static int f(int i7, int i8, int i9) {
        return i7 > i9 ? i9 : i7 < i8 ? i8 : i7;
    }

    private float g(float f7, float f8) {
        if (f8 == 0.0f) {
            return 0.0f;
        }
        int i7 = this.f1995l;
        if (i7 == 0 || i7 == 1) {
            if (f7 < f8) {
                if (f7 >= 0.0f) {
                    return 1.0f - (f7 / f8);
                }
                if (this.f2003t && i7 == 1) {
                    return 1.0f;
                }
            }
        } else if (i7 == 2 && f7 < 0.0f) {
            return f7 / (-f8);
        }
        return 0.0f;
    }

    private float h(float f7, float f8, float f9, float f10) {
        float interpolation;
        float fE = e(f7 * f8, 0.0f, f9);
        float fG = g(f8 - f10, fE) - g(f10, fE);
        if (fG < 0.0f) {
            interpolation = -this.f1990g.getInterpolation(-fG);
        } else {
            if (fG <= 0.0f) {
                return 0.0f;
            }
            interpolation = this.f1990g.getInterpolation(fG);
        }
        return e(interpolation, -1.0f, 1.0f);
    }

    private void i() {
        if (this.f2001r) {
            this.f2003t = false;
        } else {
            this.f1989e.i();
        }
    }

    private void v() {
        int i7;
        if (this.f1992i == null) {
            this.f1992i = new b();
        }
        this.f2003t = true;
        this.f2001r = true;
        if (this.f2000q || (i7 = this.f1996m) <= 0) {
            this.f1992i.run();
        } else {
            u.d0(this.f1991h, this.f1992i, i7);
        }
        this.f2000q = true;
    }

    public abstract boolean a(int i7);

    public abstract boolean b(int i7);

    void c() {
        long jUptimeMillis = SystemClock.uptimeMillis();
        MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
        this.f1991h.onTouchEvent(motionEventObtain);
        motionEventObtain.recycle();
    }

    public abstract void j(int i7, int i8);

    public a k(int i7) {
        this.f1996m = i7;
        return this;
    }

    public a l(int i7) {
        this.f1995l = i7;
        return this;
    }

    public a m(boolean z6) {
        if (this.f2004u && !z6) {
            i();
        }
        this.f2004u = z6;
        return this;
    }

    public a n(float f7, float f8) {
        float[] fArr = this.f1994k;
        fArr[0] = f7;
        fArr[1] = f8;
        return this;
    }

    public a o(float f7, float f8) {
        float[] fArr = this.f1999p;
        fArr[0] = f7 / 1000.0f;
        fArr[1] = f8 / 1000.0f;
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
            boolean r0 = r5.f2004u
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
            r5.f2002s = r2
            r5.f2000q = r1
        L1e:
            float r0 = r7.getX()
            int r3 = r6.getWidth()
            float r3 = (float) r3
            android.view.View r4 = r5.f1991h
            int r4 = r4.getWidth()
            float r4 = (float) r4
            float r0 = r5.d(r1, r0, r3, r4)
            float r7 = r7.getY()
            int r6 = r6.getHeight()
            float r6 = (float) r6
            android.view.View r3 = r5.f1991h
            int r3 = r3.getHeight()
            float r3 = (float) r3
            float r6 = r5.d(r2, r7, r6, r3)
            androidx.core.widget.a$a r7 = r5.f1989e
            r7.l(r0, r6)
            boolean r6 = r5.f2003t
            if (r6 != 0) goto L58
            boolean r6 = r5.u()
            if (r6 == 0) goto L58
            r5.v()
        L58:
            boolean r6 = r5.f2005v
            if (r6 == 0) goto L61
            boolean r6 = r5.f2003t
            if (r6 == 0) goto L61
            r1 = r2
        L61:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.widget.a.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    public a p(float f7, float f8) {
        float[] fArr = this.f1998o;
        fArr[0] = f7 / 1000.0f;
        fArr[1] = f8 / 1000.0f;
        return this;
    }

    public a q(int i7) {
        this.f1989e.j(i7);
        return this;
    }

    public a r(int i7) {
        this.f1989e.k(i7);
        return this;
    }

    public a s(float f7, float f8) {
        float[] fArr = this.f1993j;
        fArr[0] = f7;
        fArr[1] = f8;
        return this;
    }

    public a t(float f7, float f8) {
        float[] fArr = this.f1997n;
        fArr[0] = f7 / 1000.0f;
        fArr[1] = f8 / 1000.0f;
        return this;
    }

    boolean u() {
        C0019a c0019a = this.f1989e;
        int iF = c0019a.f();
        int iD = c0019a.d();
        return (iF != 0 && b(iF)) || (iD != 0 && a(iD));
    }
}
