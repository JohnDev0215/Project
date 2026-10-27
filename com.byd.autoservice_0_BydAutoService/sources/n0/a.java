package n0;

import android.content.res.Resources;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import l0.t;

/* JADX INFO: compiled from: AutoScrollHelper.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a implements View.OnTouchListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int f4040b = ViewConfiguration.getTapTimeout();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final View f4043e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Runnable f4044f;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f4047i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f4048j;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f4052n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f4053o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f4054p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f4055q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f4056r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f4057s;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final C0041a f4041c = new C0041a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Interpolator f4042d = new AccelerateInterpolator();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float[] f4045g = {0.0f, 0.0f};

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float[] f4046h = {Float.MAX_VALUE, Float.MAX_VALUE};

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public float[] f4049k = {0.0f, 0.0f};

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public float[] f4050l = {0.0f, 0.0f};

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public float[] f4051m = {Float.MAX_VALUE, Float.MAX_VALUE};

    /* JADX INFO: renamed from: n0.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AutoScrollHelper.java */
    public static class C0041a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f4058a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f4059b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f4060c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f4061d;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public float f4067j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public int f4068k;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public long f4062e = Long.MIN_VALUE;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public long f4066i = -1;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public long f4063f = 0;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f4064g = 0;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f4065h = 0;

        public void a() {
            if (this.f4063f == 0) {
                throw new RuntimeException("Cannot compute scroll delta before calling start()");
            }
            long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            float fG = g(e(jCurrentAnimationTimeMillis));
            long j3 = jCurrentAnimationTimeMillis - this.f4063f;
            this.f4063f = jCurrentAnimationTimeMillis;
            float f3 = j3 * fG;
            this.f4064g = (int) (this.f4060c * f3);
            this.f4065h = (int) (f3 * this.f4061d);
        }

        public int b() {
            return this.f4064g;
        }

        public int c() {
            return this.f4065h;
        }

        public int d() {
            float f3 = this.f4060c;
            return (int) (f3 / Math.abs(f3));
        }

        public final float e(long j3) {
            if (j3 < this.f4062e) {
                return 0.0f;
            }
            long j4 = this.f4066i;
            if (j4 < 0 || j3 < j4) {
                return a.e((j3 - r0) / this.f4058a, 0.0f, 1.0f) * 0.5f;
            }
            float f3 = this.f4067j;
            return (1.0f - f3) + (f3 * a.e((j3 - j4) / this.f4068k, 0.0f, 1.0f));
        }

        public int f() {
            float f3 = this.f4061d;
            return (int) (f3 / Math.abs(f3));
        }

        public final float g(float f3) {
            return ((-4.0f) * f3 * f3) + (f3 * 4.0f);
        }

        public boolean h() {
            return this.f4066i > 0 && AnimationUtils.currentAnimationTimeMillis() > this.f4066i + ((long) this.f4068k);
        }

        public void i() {
            long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            this.f4068k = a.f((int) (jCurrentAnimationTimeMillis - this.f4062e), 0, this.f4059b);
            this.f4067j = e(jCurrentAnimationTimeMillis);
            this.f4066i = jCurrentAnimationTimeMillis;
        }

        public void j(int i3) {
            this.f4059b = i3;
        }

        public void k(int i3) {
            this.f4058a = i3;
        }

        public void l(float f3, float f4) {
            this.f4060c = f3;
            this.f4061d = f4;
        }

        public void m() {
            long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            this.f4062e = jCurrentAnimationTimeMillis;
            this.f4066i = -1L;
            this.f4063f = jCurrentAnimationTimeMillis;
            this.f4067j = 0.5f;
            this.f4064g = 0;
            this.f4065h = 0;
        }
    }

    /* JADX INFO: compiled from: AutoScrollHelper.java */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a aVar = a.this;
            if (aVar.f4055q) {
                if (aVar.f4053o) {
                    aVar.f4053o = false;
                    aVar.f4041c.m();
                }
                C0041a c0041a = a.this.f4041c;
                if (c0041a.h() || !a.this.u()) {
                    a.this.f4055q = false;
                    return;
                }
                a aVar2 = a.this;
                if (aVar2.f4054p) {
                    aVar2.f4054p = false;
                    aVar2.c();
                }
                c0041a.a();
                a.this.j(c0041a.b(), c0041a.c());
                t.b0(a.this.f4043e, this);
            }
        }
    }

    public a(View view) {
        this.f4043e = view;
        float f3 = Resources.getSystem().getDisplayMetrics().density;
        float f4 = (int) ((1575.0f * f3) + 0.5f);
        o(f4, f4);
        float f5 = (int) ((f3 * 315.0f) + 0.5f);
        p(f5, f5);
        l(1);
        n(Float.MAX_VALUE, Float.MAX_VALUE);
        s(0.2f, 0.2f);
        t(1.0f, 1.0f);
        k(f4040b);
        r(500);
        q(500);
    }

    public static float e(float f3, float f4, float f5) {
        return f3 > f5 ? f5 : f3 < f4 ? f4 : f3;
    }

    public static int f(int i3, int i4, int i5) {
        return i3 > i5 ? i5 : i3 < i4 ? i4 : i3;
    }

    public abstract boolean a(int i3);

    public abstract boolean b(int i3);

    public void c() {
        long jUptimeMillis = SystemClock.uptimeMillis();
        MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
        this.f4043e.onTouchEvent(motionEventObtain);
        motionEventObtain.recycle();
    }

    public final float d(int i3, float f3, float f4, float f5) {
        float fH = h(this.f4045g[i3], f4, this.f4046h[i3], f3);
        if (fH == 0.0f) {
            return 0.0f;
        }
        float f6 = this.f4049k[i3];
        float f7 = this.f4050l[i3];
        float f8 = this.f4051m[i3];
        float f9 = f6 * f5;
        return fH > 0.0f ? e(fH * f9, f7, f8) : -e((-fH) * f9, f7, f8);
    }

    public final float g(float f3, float f4) {
        if (f4 == 0.0f) {
            return 0.0f;
        }
        int i3 = this.f4047i;
        if (i3 == 0 || i3 == 1) {
            if (f3 < f4) {
                if (f3 >= 0.0f) {
                    return 1.0f - (f3 / f4);
                }
                if (this.f4055q && i3 == 1) {
                    return 1.0f;
                }
            }
        } else if (i3 == 2 && f3 < 0.0f) {
            return f3 / (-f4);
        }
        return 0.0f;
    }

    public final float h(float f3, float f4, float f5, float f6) {
        float interpolation;
        float fE = e(f3 * f4, 0.0f, f5);
        float fG = g(f4 - f6, fE) - g(f6, fE);
        if (fG < 0.0f) {
            interpolation = -this.f4042d.getInterpolation(-fG);
        } else {
            if (fG <= 0.0f) {
                return 0.0f;
            }
            interpolation = this.f4042d.getInterpolation(fG);
        }
        return e(interpolation, -1.0f, 1.0f);
    }

    public final void i() {
        if (this.f4053o) {
            this.f4055q = false;
        } else {
            this.f4041c.i();
        }
    }

    public abstract void j(int i3, int i4);

    public a k(int i3) {
        this.f4048j = i3;
        return this;
    }

    public a l(int i3) {
        this.f4047i = i3;
        return this;
    }

    public a m(boolean z3) {
        if (this.f4056r && !z3) {
            i();
        }
        this.f4056r = z3;
        return this;
    }

    public a n(float f3, float f4) {
        float[] fArr = this.f4046h;
        fArr[0] = f3;
        fArr[1] = f4;
        return this;
    }

    public a o(float f3, float f4) {
        float[] fArr = this.f4051m;
        fArr[0] = f3 / 1000.0f;
        fArr[1] = f4 / 1000.0f;
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
            boolean r0 = r5.f4056r
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
            r5.f4054p = r2
            r5.f4052n = r1
        L1e:
            float r0 = r7.getX()
            int r3 = r6.getWidth()
            float r3 = (float) r3
            android.view.View r4 = r5.f4043e
            int r4 = r4.getWidth()
            float r4 = (float) r4
            float r0 = r5.d(r1, r0, r3, r4)
            float r7 = r7.getY()
            int r6 = r6.getHeight()
            float r6 = (float) r6
            android.view.View r3 = r5.f4043e
            int r3 = r3.getHeight()
            float r3 = (float) r3
            float r6 = r5.d(r2, r7, r6, r3)
            n0.a$a r7 = r5.f4041c
            r7.l(r0, r6)
            boolean r6 = r5.f4055q
            if (r6 != 0) goto L58
            boolean r6 = r5.u()
            if (r6 == 0) goto L58
            r5.v()
        L58:
            boolean r6 = r5.f4057s
            if (r6 == 0) goto L61
            boolean r6 = r5.f4055q
            if (r6 == 0) goto L61
            r1 = r2
        L61:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: n0.a.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    public a p(float f3, float f4) {
        float[] fArr = this.f4050l;
        fArr[0] = f3 / 1000.0f;
        fArr[1] = f4 / 1000.0f;
        return this;
    }

    public a q(int i3) {
        this.f4041c.j(i3);
        return this;
    }

    public a r(int i3) {
        this.f4041c.k(i3);
        return this;
    }

    public a s(float f3, float f4) {
        float[] fArr = this.f4045g;
        fArr[0] = f3;
        fArr[1] = f4;
        return this;
    }

    public a t(float f3, float f4) {
        float[] fArr = this.f4049k;
        fArr[0] = f3 / 1000.0f;
        fArr[1] = f4 / 1000.0f;
        return this;
    }

    public boolean u() {
        C0041a c0041a = this.f4041c;
        int iF = c0041a.f();
        int iD = c0041a.d();
        return (iF != 0 && b(iF)) || (iD != 0 && a(iD));
    }

    public final void v() {
        int i3;
        if (this.f4044f == null) {
            this.f4044f = new b();
        }
        this.f4055q = true;
        this.f4053o = true;
        if (this.f4052n || (i3 = this.f4048j) <= 0) {
            this.f4044f.run();
        } else {
            t.c0(this.f4043e, this.f4044f, i3);
        }
        this.f4052n = true;
    }
}
