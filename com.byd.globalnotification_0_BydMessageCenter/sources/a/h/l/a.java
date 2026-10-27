package a.h.l;

import a.h.k.q;
import android.content.res.Resources;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

/* JADX INFO: loaded from: classes.dex */
public abstract class a implements View.OnTouchListener {
    public static final int q = ViewConfiguration.getTapTimeout();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final View f871c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Runnable f872d;
    public int g;
    public int h;
    public boolean l;
    public boolean m;
    public boolean n;
    public boolean o;
    public boolean p;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C0018a f869a = new C0018a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Interpolator f870b = new AccelerateInterpolator();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float[] f873e = {0.0f, 0.0f};

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float[] f874f = {Float.MAX_VALUE, Float.MAX_VALUE};
    public float[] i = {0.0f, 0.0f};
    public float[] j = {0.0f, 0.0f};
    public float[] k = {Float.MAX_VALUE, Float.MAX_VALUE};

    /* JADX INFO: renamed from: a.h.l.a$a, reason: collision with other inner class name */
    public static class C0018a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f875a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f876b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f877c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f878d;
        public float j;
        public int k;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public long f879e = Long.MIN_VALUE;
        public long i = -1;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public long f880f = 0;
        public int g = 0;
        public int h = 0;

        public final float a(long j) {
            if (j < this.f879e) {
                return 0.0f;
            }
            long j2 = this.i;
            if (j2 < 0 || j < j2) {
                return a.b((j - this.f879e) / this.f875a, 0.0f, 1.0f) * 0.5f;
            }
            float f2 = this.j;
            return (a.b((j - j2) / this.k, 0.0f, 1.0f) * f2) + (1.0f - f2);
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a aVar = a.this;
            if (aVar.o) {
                if (aVar.m) {
                    aVar.m = false;
                    C0018a c0018a = aVar.f869a;
                    if (c0018a == null) {
                        throw null;
                    }
                    long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
                    c0018a.f879e = jCurrentAnimationTimeMillis;
                    c0018a.i = -1L;
                    c0018a.f880f = jCurrentAnimationTimeMillis;
                    c0018a.j = 0.5f;
                    c0018a.g = 0;
                    c0018a.h = 0;
                }
                C0018a c0018a2 = a.this.f869a;
                if ((c0018a2.i > 0 && AnimationUtils.currentAnimationTimeMillis() > c0018a2.i + ((long) c0018a2.k)) || !a.this.e()) {
                    a.this.o = false;
                    return;
                }
                a aVar2 = a.this;
                if (aVar2.n) {
                    aVar2.n = false;
                    if (aVar2 == null) {
                        throw null;
                    }
                    long jUptimeMillis = SystemClock.uptimeMillis();
                    MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
                    aVar2.f871c.onTouchEvent(motionEventObtain);
                    motionEventObtain.recycle();
                }
                if (c0018a2.f880f == 0) {
                    throw new RuntimeException("Cannot compute scroll delta before calling start()");
                }
                long jCurrentAnimationTimeMillis2 = AnimationUtils.currentAnimationTimeMillis();
                float fA = c0018a2.a(jCurrentAnimationTimeMillis2);
                long j = jCurrentAnimationTimeMillis2 - c0018a2.f880f;
                c0018a2.f880f = jCurrentAnimationTimeMillis2;
                float f2 = j * ((fA * 4.0f) + ((-4.0f) * fA * fA));
                c0018a2.g = (int) (c0018a2.f877c * f2);
                int i = (int) (f2 * c0018a2.f878d);
                c0018a2.h = i;
                ((a.h.l.b) a.this).r.scrollListBy(i);
                q.N(a.this.f871c, this);
            }
        }
    }

    public a(View view) {
        this.f871c = view;
        float f2 = Resources.getSystem().getDisplayMetrics().density;
        float[] fArr = this.k;
        float f3 = ((int) ((1575.0f * f2) + 0.5f)) / 1000.0f;
        fArr[0] = f3;
        fArr[1] = f3;
        float[] fArr2 = this.j;
        float f4 = ((int) ((f2 * 315.0f) + 0.5f)) / 1000.0f;
        fArr2[0] = f4;
        fArr2[1] = f4;
        this.g = 1;
        float[] fArr3 = this.f874f;
        fArr3[0] = Float.MAX_VALUE;
        fArr3[1] = Float.MAX_VALUE;
        float[] fArr4 = this.f873e;
        fArr4[0] = 0.2f;
        fArr4[1] = 0.2f;
        float[] fArr5 = this.i;
        fArr5[0] = 0.001f;
        fArr5[1] = 0.001f;
        this.h = q;
        C0018a c0018a = this.f869a;
        c0018a.f875a = 500;
        c0018a.f876b = 500;
    }

    public static float b(float f2, float f3, float f4) {
        return f2 > f4 ? f4 : f2 < f3 ? f3 : f2;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x003d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x003e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final float a(int r4, float r5, float r6, float r7) {
        /*
            r3 = this;
            float[] r0 = r3.f873e
            r0 = r0[r4]
            float[] r1 = r3.f874f
            r1 = r1[r4]
            float r0 = r0 * r6
            r2 = 0
            float r0 = b(r0, r2, r1)
            float r1 = r3.c(r5, r0)
            float r6 = r6 - r5
            float r5 = r3.c(r6, r0)
            float r5 = r5 - r1
            int r6 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r6 >= 0) goto L25
            android.view.animation.Interpolator r6 = r3.f870b
            float r5 = -r5
            float r5 = r6.getInterpolation(r5)
            float r5 = -r5
            goto L2f
        L25:
            int r6 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r6 <= 0) goto L38
            android.view.animation.Interpolator r6 = r3.f870b
            float r5 = r6.getInterpolation(r5)
        L2f:
            r6 = -1082130432(0xffffffffbf800000, float:-1.0)
            r0 = 1065353216(0x3f800000, float:1.0)
            float r5 = b(r5, r6, r0)
            goto L39
        L38:
            r5 = r2
        L39:
            int r6 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r6 != 0) goto L3e
            return r2
        L3e:
            float[] r0 = r3.i
            r0 = r0[r4]
            float[] r1 = r3.j
            r1 = r1[r4]
            float[] r3 = r3.k
            r3 = r3[r4]
            float r0 = r0 * r7
            if (r6 <= 0) goto L53
            float r5 = r5 * r0
            float r3 = b(r5, r1, r3)
            return r3
        L53:
            float r4 = -r5
            float r4 = r4 * r0
            float r3 = b(r4, r1, r3)
            float r3 = -r3
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: a.h.l.a.a(int, float, float, float):float");
    }

    public final float c(float f2, float f3) {
        if (f3 == 0.0f) {
            return 0.0f;
        }
        int i = this.g;
        if (i == 0 || i == 1) {
            if (f2 < f3) {
                if (f2 >= 0.0f) {
                    return 1.0f - (f2 / f3);
                }
                if (this.o && this.g == 1) {
                    return 1.0f;
                }
            }
        } else if (i == 2 && f2 < 0.0f) {
            return f2 / (-f3);
        }
        return 0.0f;
    }

    public final void d() {
        int i = 0;
        if (this.m) {
            this.o = false;
            return;
        }
        C0018a c0018a = this.f869a;
        if (c0018a == null) {
            throw null;
        }
        long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        int i2 = (int) (jCurrentAnimationTimeMillis - c0018a.f879e);
        int i3 = c0018a.f876b;
        if (i2 > i3) {
            i = i3;
        } else if (i2 >= 0) {
            i = i2;
        }
        c0018a.k = i;
        c0018a.j = c0018a.a(jCurrentAnimationTimeMillis);
        c0018a.i = jCurrentAnimationTimeMillis;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:6:0x0020  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean e() {
        /*
            r8 = this;
            a.h.l.a$a r0 = r8.f869a
            float r1 = r0.f878d
            float r2 = java.lang.Math.abs(r1)
            float r1 = r1 / r2
            int r1 = (int) r1
            float r0 = r0.f877c
            float r2 = java.lang.Math.abs(r0)
            float r0 = r0 / r2
            int r0 = (int) r0
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L52
            a.h.l.b r8 = (a.h.l.b) r8
            android.widget.ListView r8 = r8.r
            int r4 = r8.getCount()
            if (r4 != 0) goto L22
        L20:
            r8 = r3
            goto L50
        L22:
            int r5 = r8.getChildCount()
            int r6 = r8.getFirstVisiblePosition()
            int r7 = r6 + r5
            if (r1 <= 0) goto L40
            if (r7 < r4) goto L4f
            int r5 = r5 - r2
            android.view.View r1 = r8.getChildAt(r5)
            int r1 = r1.getBottom()
            int r8 = r8.getHeight()
            if (r1 > r8) goto L4f
            goto L20
        L40:
            if (r1 >= 0) goto L20
            if (r6 > 0) goto L4f
            android.view.View r8 = r8.getChildAt(r3)
            int r8 = r8.getTop()
            if (r8 < 0) goto L4f
            goto L20
        L4f:
            r8 = r2
        L50:
            if (r8 != 0) goto L53
        L52:
            r2 = r3
        L53:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: a.h.l.a.e():boolean");
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
            boolean r0 = r5.p
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
            goto L7d
        L16:
            r5.d()
            goto L7d
        L1a:
            r5.n = r2
            r5.l = r1
        L1e:
            float r0 = r7.getX()
            int r3 = r6.getWidth()
            float r3 = (float) r3
            android.view.View r4 = r5.f871c
            int r4 = r4.getWidth()
            float r4 = (float) r4
            float r0 = r5.a(r1, r0, r3, r4)
            float r7 = r7.getY()
            int r6 = r6.getHeight()
            float r6 = (float) r6
            android.view.View r3 = r5.f871c
            int r3 = r3.getHeight()
            float r3 = (float) r3
            float r6 = r5.a(r2, r7, r6, r3)
            a.h.l.a$a r7 = r5.f869a
            r7.f877c = r0
            r7.f878d = r6
            boolean r6 = r5.o
            if (r6 != 0) goto L7d
            boolean r6 = r5.e()
            if (r6 == 0) goto L7d
            java.lang.Runnable r6 = r5.f872d
            if (r6 != 0) goto L61
            a.h.l.a$b r6 = new a.h.l.a$b
            r6.<init>()
            r5.f872d = r6
        L61:
            r5.o = r2
            r5.m = r2
            boolean r6 = r5.l
            if (r6 != 0) goto L76
            int r6 = r5.h
            if (r6 <= 0) goto L76
            android.view.View r7 = r5.f871c
            java.lang.Runnable r0 = r5.f872d
            long r3 = (long) r6
            a.h.k.q.O(r7, r0, r3)
            goto L7b
        L76:
            java.lang.Runnable r6 = r5.f872d
            r6.run()
        L7b:
            r5.l = r2
        L7d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: a.h.l.a.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }
}
