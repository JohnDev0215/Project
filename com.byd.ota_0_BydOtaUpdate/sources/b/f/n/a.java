package b.f.n;

import android.content.res.Resources;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import b.f.m.s;

/* JADX INFO: compiled from: AutoScrollHelper.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a implements View.OnTouchListener {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int f1821c = ViewConfiguration.getTapTimeout();
    public int G3;
    public int H3;
    public boolean L3;
    public boolean M3;
    public boolean N3;
    public boolean O3;
    public boolean P3;
    public boolean Q3;
    public final View x;
    public Runnable y;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final C0032a f1822d = new C0032a();
    public final Interpolator s = new AccelerateInterpolator();
    public float[] E3 = {0.0f, 0.0f};
    public float[] F3 = {Float.MAX_VALUE, Float.MAX_VALUE};
    public float[] I3 = {0.0f, 0.0f};
    public float[] J3 = {0.0f, 0.0f};
    public float[] K3 = {Float.MAX_VALUE, Float.MAX_VALUE};

    /* JADX INFO: renamed from: b.f.n.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AutoScrollHelper.java */
    public static class C0032a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1823a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1824b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f1825c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f1826d;
        public float j;
        public int k;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public long f1827e = Long.MIN_VALUE;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public long f1831i = -1;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public long f1828f = 0;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f1829g = 0;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f1830h = 0;

        public void a() {
            if (this.f1828f == 0) {
                throw new RuntimeException("Cannot compute scroll delta before calling start()");
            }
            long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            float fG = g(e(jCurrentAnimationTimeMillis));
            long j = jCurrentAnimationTimeMillis - this.f1828f;
            this.f1828f = jCurrentAnimationTimeMillis;
            float f2 = j * fG;
            this.f1829g = (int) (this.f1825c * f2);
            this.f1830h = (int) (f2 * this.f1826d);
        }

        public int b() {
            return this.f1829g;
        }

        public int c() {
            return this.f1830h;
        }

        public int d() {
            float f2 = this.f1825c;
            return (int) (f2 / Math.abs(f2));
        }

        public final float e(long j) {
            if (j < this.f1827e) {
                return 0.0f;
            }
            long j2 = this.f1831i;
            if (j2 < 0 || j < j2) {
                return a.e((j - r0) / this.f1823a, 0.0f, 1.0f) * 0.5f;
            }
            float f2 = this.j;
            return (1.0f - f2) + (f2 * a.e((j - j2) / this.k, 0.0f, 1.0f));
        }

        public int f() {
            float f2 = this.f1826d;
            return (int) (f2 / Math.abs(f2));
        }

        public final float g(float f2) {
            return ((-4.0f) * f2 * f2) + (f2 * 4.0f);
        }

        public boolean h() {
            return this.f1831i > 0 && AnimationUtils.currentAnimationTimeMillis() > this.f1831i + ((long) this.k);
        }

        public void i() {
            long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            this.k = a.f((int) (jCurrentAnimationTimeMillis - this.f1827e), 0, this.f1824b);
            this.j = e(jCurrentAnimationTimeMillis);
            this.f1831i = jCurrentAnimationTimeMillis;
        }

        public void j(int i2) {
            this.f1824b = i2;
        }

        public void k(int i2) {
            this.f1823a = i2;
        }

        public void l(float f2, float f3) {
            this.f1825c = f2;
            this.f1826d = f3;
        }

        public void m() {
            long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            this.f1827e = jCurrentAnimationTimeMillis;
            this.f1831i = -1L;
            this.f1828f = jCurrentAnimationTimeMillis;
            this.j = 0.5f;
            this.f1829g = 0;
            this.f1830h = 0;
        }
    }

    /* JADX INFO: compiled from: AutoScrollHelper.java */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a aVar = a.this;
            if (aVar.O3) {
                if (aVar.M3) {
                    aVar.M3 = false;
                    aVar.f1822d.m();
                }
                C0032a c0032a = a.this.f1822d;
                if (c0032a.h() || !a.this.u()) {
                    a.this.O3 = false;
                    return;
                }
                a aVar2 = a.this;
                if (aVar2.N3) {
                    aVar2.N3 = false;
                    aVar2.c();
                }
                c0032a.a();
                a.this.j(c0032a.b(), c0032a.c());
                s.O(a.this.x, this);
            }
        }
    }

    public a(View view) {
        this.x = view;
        float f2 = Resources.getSystem().getDisplayMetrics().density;
        float f3 = (int) ((1575.0f * f2) + 0.5f);
        o(f3, f3);
        float f4 = (int) ((f2 * 315.0f) + 0.5f);
        p(f4, f4);
        l(1);
        n(Float.MAX_VALUE, Float.MAX_VALUE);
        s(0.2f, 0.2f);
        t(1.0f, 1.0f);
        k(f1821c);
        r(500);
        q(500);
    }

    public static float e(float f2, float f3, float f4) {
        return f2 > f4 ? f4 : f2 < f3 ? f3 : f2;
    }

    public static int f(int i2, int i3, int i4) {
        return i2 > i4 ? i4 : i2 < i3 ? i3 : i2;
    }

    public abstract boolean a(int i2);

    public abstract boolean b(int i2);

    public void c() {
        long jUptimeMillis = SystemClock.uptimeMillis();
        MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
        this.x.onTouchEvent(motionEventObtain);
        motionEventObtain.recycle();
    }

    public final float d(int i2, float f2, float f3, float f4) {
        float fH = h(this.E3[i2], f3, this.F3[i2], f2);
        if (fH == 0.0f) {
            return 0.0f;
        }
        float f5 = this.I3[i2];
        float f6 = this.J3[i2];
        float f7 = this.K3[i2];
        float f8 = f5 * f4;
        return fH > 0.0f ? e(fH * f8, f6, f7) : -e((-fH) * f8, f6, f7);
    }

    public final float g(float f2, float f3) {
        if (f3 == 0.0f) {
            return 0.0f;
        }
        int i2 = this.G3;
        if (i2 == 0 || i2 == 1) {
            if (f2 < f3) {
                if (f2 >= 0.0f) {
                    return 1.0f - (f2 / f3);
                }
                if (this.O3 && i2 == 1) {
                    return 1.0f;
                }
            }
        } else if (i2 == 2 && f2 < 0.0f) {
            return f2 / (-f3);
        }
        return 0.0f;
    }

    public final float h(float f2, float f3, float f4, float f5) {
        float interpolation;
        float fE = e(f2 * f3, 0.0f, f4);
        float fG = g(f3 - f5, fE) - g(f5, fE);
        if (fG < 0.0f) {
            interpolation = -this.s.getInterpolation(-fG);
        } else {
            if (fG <= 0.0f) {
                return 0.0f;
            }
            interpolation = this.s.getInterpolation(fG);
        }
        return e(interpolation, -1.0f, 1.0f);
    }

    public final void i() {
        if (this.M3) {
            this.O3 = false;
        } else {
            this.f1822d.i();
        }
    }

    public abstract void j(int i2, int i3);

    public a k(int i2) {
        this.H3 = i2;
        return this;
    }

    public a l(int i2) {
        this.G3 = i2;
        return this;
    }

    public a m(boolean z) {
        if (this.P3 && !z) {
            i();
        }
        this.P3 = z;
        return this;
    }

    public a n(float f2, float f3) {
        float[] fArr = this.F3;
        fArr[0] = f2;
        fArr[1] = f3;
        return this;
    }

    public a o(float f2, float f3) {
        float[] fArr = this.K3;
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
            boolean r0 = r5.P3
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
            r5.N3 = r2
            r5.L3 = r1
        L1e:
            float r0 = r7.getX()
            int r3 = r6.getWidth()
            float r3 = (float) r3
            android.view.View r4 = r5.x
            int r4 = r4.getWidth()
            float r4 = (float) r4
            float r0 = r5.d(r1, r0, r3, r4)
            float r7 = r7.getY()
            int r6 = r6.getHeight()
            float r6 = (float) r6
            android.view.View r3 = r5.x
            int r3 = r3.getHeight()
            float r3 = (float) r3
            float r6 = r5.d(r2, r7, r6, r3)
            b.f.n.a$a r7 = r5.f1822d
            r7.l(r0, r6)
            boolean r6 = r5.O3
            if (r6 != 0) goto L58
            boolean r6 = r5.u()
            if (r6 == 0) goto L58
            r5.v()
        L58:
            boolean r6 = r5.Q3
            if (r6 == 0) goto L61
            boolean r6 = r5.O3
            if (r6 == 0) goto L61
            r1 = r2
        L61:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: b.f.n.a.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    public a p(float f2, float f3) {
        float[] fArr = this.J3;
        fArr[0] = f2 / 1000.0f;
        fArr[1] = f3 / 1000.0f;
        return this;
    }

    public a q(int i2) {
        this.f1822d.j(i2);
        return this;
    }

    public a r(int i2) {
        this.f1822d.k(i2);
        return this;
    }

    public a s(float f2, float f3) {
        float[] fArr = this.E3;
        fArr[0] = f2;
        fArr[1] = f3;
        return this;
    }

    public a t(float f2, float f3) {
        float[] fArr = this.I3;
        fArr[0] = f2 / 1000.0f;
        fArr[1] = f3 / 1000.0f;
        return this;
    }

    public boolean u() {
        C0032a c0032a = this.f1822d;
        int iF = c0032a.f();
        int iD = c0032a.d();
        return (iF != 0 && b(iF)) || (iD != 0 && a(iD));
    }

    public final void v() {
        int i2;
        if (this.y == null) {
            this.y = new b();
        }
        this.O3 = true;
        this.M3 = true;
        if (this.L3 || (i2 = this.H3) <= 0) {
            this.y.run();
        } else {
            s.P(this.x, this.y, i2);
        }
        this.L3 = true;
    }
}
