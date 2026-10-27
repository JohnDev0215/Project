package r0;

import android.content.res.Resources;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import o0.t;

/* JADX INFO: compiled from: AutoScrollHelper.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a implements View.OnTouchListener {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int f7693d = ViewConfiguration.getTapTimeout();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final View f7696g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Runnable f7697h;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f7700k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f7701l;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f7705p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f7706q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f7707r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f7708s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f7709t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f7710u;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C0112a f7694e = new C0112a();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Interpolator f7695f = new AccelerateInterpolator();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float[] f7698i = {0.0f, 0.0f};

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public float[] f7699j = {Float.MAX_VALUE, Float.MAX_VALUE};

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public float[] f7702m = {0.0f, 0.0f};

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public float[] f7703n = {0.0f, 0.0f};

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public float[] f7704o = {Float.MAX_VALUE, Float.MAX_VALUE};

    /* JADX INFO: renamed from: r0.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AutoScrollHelper.java */
    public static class C0112a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f7711a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f7712b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f7713c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f7714d;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public float f7720j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public int f7721k;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public long f7715e = Long.MIN_VALUE;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public long f7719i = -1;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public long f7716f = 0;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f7717g = 0;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f7718h = 0;

        public void a() {
            if (this.f7716f == 0) {
                throw new RuntimeException("Cannot compute scroll delta before calling start()");
            }
            long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            float fG = g(e(jCurrentAnimationTimeMillis));
            long j8 = jCurrentAnimationTimeMillis - this.f7716f;
            this.f7716f = jCurrentAnimationTimeMillis;
            float f8 = j8 * fG;
            this.f7717g = (int) (this.f7713c * f8);
            this.f7718h = (int) (f8 * this.f7714d);
        }

        public int b() {
            return this.f7717g;
        }

        public int c() {
            return this.f7718h;
        }

        public int d() {
            float f8 = this.f7713c;
            return (int) (f8 / Math.abs(f8));
        }

        public final float e(long j8) {
            if (j8 < this.f7715e) {
                return 0.0f;
            }
            long j9 = this.f7719i;
            if (j9 < 0 || j8 < j9) {
                return a.e((j8 - r0) / this.f7711a, 0.0f, 1.0f) * 0.5f;
            }
            float f8 = this.f7720j;
            return (1.0f - f8) + (f8 * a.e((j8 - j9) / this.f7721k, 0.0f, 1.0f));
        }

        public int f() {
            float f8 = this.f7714d;
            return (int) (f8 / Math.abs(f8));
        }

        public final float g(float f8) {
            return ((-4.0f) * f8 * f8) + (f8 * 4.0f);
        }

        public boolean h() {
            return this.f7719i > 0 && AnimationUtils.currentAnimationTimeMillis() > this.f7719i + ((long) this.f7721k);
        }

        public void i() {
            long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            this.f7721k = a.f((int) (jCurrentAnimationTimeMillis - this.f7715e), 0, this.f7712b);
            this.f7720j = e(jCurrentAnimationTimeMillis);
            this.f7719i = jCurrentAnimationTimeMillis;
        }

        public void j(int i8) {
            this.f7712b = i8;
        }

        public void k(int i8) {
            this.f7711a = i8;
        }

        public void l(float f8, float f9) {
            this.f7713c = f8;
            this.f7714d = f9;
        }

        public void m() {
            long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            this.f7715e = jCurrentAnimationTimeMillis;
            this.f7719i = -1L;
            this.f7716f = jCurrentAnimationTimeMillis;
            this.f7720j = 0.5f;
            this.f7717g = 0;
            this.f7718h = 0;
        }
    }

    /* JADX INFO: compiled from: AutoScrollHelper.java */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a aVar = a.this;
            if (aVar.f7708s) {
                if (aVar.f7706q) {
                    aVar.f7706q = false;
                    aVar.f7694e.m();
                }
                C0112a c0112a = a.this.f7694e;
                if (c0112a.h() || !a.this.u()) {
                    a.this.f7708s = false;
                    return;
                }
                a aVar2 = a.this;
                if (aVar2.f7707r) {
                    aVar2.f7707r = false;
                    aVar2.c();
                }
                c0112a.a();
                a.this.j(c0112a.b(), c0112a.c());
                t.d0(a.this.f7696g, this);
            }
        }
    }

    public a(View view) {
        this.f7696g = view;
        float f8 = Resources.getSystem().getDisplayMetrics().density;
        float f9 = (int) ((1575.0f * f8) + 0.5f);
        o(f9, f9);
        float f10 = (int) ((f8 * 315.0f) + 0.5f);
        p(f10, f10);
        l(1);
        n(Float.MAX_VALUE, Float.MAX_VALUE);
        s(0.2f, 0.2f);
        t(1.0f, 1.0f);
        k(f7693d);
        r(500);
        q(500);
    }

    public static float e(float f8, float f9, float f10) {
        return f8 > f10 ? f10 : f8 < f9 ? f9 : f8;
    }

    public static int f(int i8, int i9, int i10) {
        return i8 > i10 ? i10 : i8 < i9 ? i9 : i8;
    }

    public abstract boolean a(int i8);

    public abstract boolean b(int i8);

    public void c() {
        long jUptimeMillis = SystemClock.uptimeMillis();
        MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
        this.f7696g.onTouchEvent(motionEventObtain);
        motionEventObtain.recycle();
    }

    public final float d(int i8, float f8, float f9, float f10) {
        float fH = h(this.f7698i[i8], f9, this.f7699j[i8], f8);
        if (fH == 0.0f) {
            return 0.0f;
        }
        float f11 = this.f7702m[i8];
        float f12 = this.f7703n[i8];
        float f13 = this.f7704o[i8];
        float f14 = f11 * f10;
        return fH > 0.0f ? e(fH * f14, f12, f13) : -e((-fH) * f14, f12, f13);
    }

    public final float g(float f8, float f9) {
        if (f9 == 0.0f) {
            return 0.0f;
        }
        int i8 = this.f7700k;
        if (i8 == 0 || i8 == 1) {
            if (f8 < f9) {
                if (f8 >= 0.0f) {
                    return 1.0f - (f8 / f9);
                }
                if (this.f7708s && i8 == 1) {
                    return 1.0f;
                }
            }
        } else if (i8 == 2 && f8 < 0.0f) {
            return f8 / (-f9);
        }
        return 0.0f;
    }

    public final float h(float f8, float f9, float f10, float f11) {
        float interpolation;
        float fE = e(f8 * f9, 0.0f, f10);
        float fG = g(f9 - f11, fE) - g(f11, fE);
        if (fG < 0.0f) {
            interpolation = -this.f7695f.getInterpolation(-fG);
        } else {
            if (fG <= 0.0f) {
                return 0.0f;
            }
            interpolation = this.f7695f.getInterpolation(fG);
        }
        return e(interpolation, -1.0f, 1.0f);
    }

    public final void i() {
        if (this.f7706q) {
            this.f7708s = false;
        } else {
            this.f7694e.i();
        }
    }

    public abstract void j(int i8, int i9);

    public a k(int i8) {
        this.f7701l = i8;
        return this;
    }

    public a l(int i8) {
        this.f7700k = i8;
        return this;
    }

    public a m(boolean z7) {
        if (this.f7709t && !z7) {
            i();
        }
        this.f7709t = z7;
        return this;
    }

    public a n(float f8, float f9) {
        float[] fArr = this.f7699j;
        fArr[0] = f8;
        fArr[1] = f9;
        return this;
    }

    public a o(float f8, float f9) {
        float[] fArr = this.f7704o;
        fArr[0] = f8 / 1000.0f;
        fArr[1] = f9 / 1000.0f;
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
            boolean r0 = r5.f7709t
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
            r5.f7707r = r2
            r5.f7705p = r1
        L1e:
            float r0 = r7.getX()
            int r3 = r6.getWidth()
            float r3 = (float) r3
            android.view.View r4 = r5.f7696g
            int r4 = r4.getWidth()
            float r4 = (float) r4
            float r0 = r5.d(r1, r0, r3, r4)
            float r7 = r7.getY()
            int r6 = r6.getHeight()
            float r6 = (float) r6
            android.view.View r3 = r5.f7696g
            int r3 = r3.getHeight()
            float r3 = (float) r3
            float r6 = r5.d(r2, r7, r6, r3)
            r0.a$a r7 = r5.f7694e
            r7.l(r0, r6)
            boolean r6 = r5.f7708s
            if (r6 != 0) goto L58
            boolean r6 = r5.u()
            if (r6 == 0) goto L58
            r5.v()
        L58:
            boolean r6 = r5.f7710u
            if (r6 == 0) goto L61
            boolean r6 = r5.f7708s
            if (r6 == 0) goto L61
            r1 = r2
        L61:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: r0.a.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    public a p(float f8, float f9) {
        float[] fArr = this.f7703n;
        fArr[0] = f8 / 1000.0f;
        fArr[1] = f9 / 1000.0f;
        return this;
    }

    public a q(int i8) {
        this.f7694e.j(i8);
        return this;
    }

    public a r(int i8) {
        this.f7694e.k(i8);
        return this;
    }

    public a s(float f8, float f9) {
        float[] fArr = this.f7698i;
        fArr[0] = f8;
        fArr[1] = f9;
        return this;
    }

    public a t(float f8, float f9) {
        float[] fArr = this.f7702m;
        fArr[0] = f8 / 1000.0f;
        fArr[1] = f9 / 1000.0f;
        return this;
    }

    public boolean u() {
        C0112a c0112a = this.f7694e;
        int iF = c0112a.f();
        int iD = c0112a.d();
        return (iF != 0 && b(iF)) || (iD != 0 && a(iD));
    }

    public final void v() {
        int i8;
        if (this.f7697h == null) {
            this.f7697h = new b();
        }
        this.f7708s = true;
        this.f7706q = true;
        if (this.f7705p || (i8 = this.f7701l) <= 0) {
            this.f7697h.run();
        } else {
            t.e0(this.f7696g, this.f7697h, i8);
        }
        this.f7705p = true;
    }
}
