package defpackage;

import android.content.res.Resources;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import defpackage.om3;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: AutoScrollHelper.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class z9 implements View.OnTouchListener {
    public static final int u = ViewConfiguration.getTapTimeout();
    public final a e;
    public final AccelerateInterpolator f;
    public final View g;
    public b h;
    public final float[] i;
    public final float[] j;
    public int k;
    public int l;
    public final float[] m;
    public final float[] n;
    public final float[] o;
    public boolean p;
    public boolean q;
    public boolean r;
    public boolean s;
    public boolean t;

    /* JADX INFO: compiled from: AutoScrollHelper.java */
    public static class a {
        public int a;
        public int b;
        public float c;
        public float d;
        public float h;
        public int i;
        public long e = Long.MIN_VALUE;
        public long g = -1;
        public long f = 0;

        public final float a(long j) {
            if (j < this.e) {
                return 0.0f;
            }
            long j2 = this.g;
            if (j2 < 0 || j < j2) {
                return z9.b((j - r0) / this.a, 0.0f, 1.0f) * 0.5f;
            }
            float f = this.h;
            return (z9.b((j - j2) / this.i, 0.0f, 1.0f) * f) + (1.0f - f);
        }
    }

    /* JADX INFO: compiled from: AutoScrollHelper.java */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            z9 z9Var = z9.this;
            if (z9Var.s) {
                boolean z = z9Var.q;
                a aVar = z9Var.e;
                if (z) {
                    z9Var.q = false;
                    aVar.getClass();
                    long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
                    aVar.e = jCurrentAnimationTimeMillis;
                    aVar.g = -1L;
                    aVar.f = jCurrentAnimationTimeMillis;
                    aVar.h = 0.5f;
                }
                if ((aVar.g > 0 && AnimationUtils.currentAnimationTimeMillis() > aVar.g + ((long) aVar.i)) || !z9Var.f()) {
                    z9Var.s = false;
                    return;
                }
                boolean z2 = z9Var.r;
                View view = z9Var.g;
                if (z2) {
                    z9Var.r = false;
                    long jUptimeMillis = SystemClock.uptimeMillis();
                    MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
                    view.onTouchEvent(motionEventObtain);
                    motionEventObtain.recycle();
                }
                if (aVar.f == 0) {
                    throw new RuntimeException("Cannot compute scroll delta before calling start()");
                }
                long jCurrentAnimationTimeMillis2 = AnimationUtils.currentAnimationTimeMillis();
                float fA = aVar.a(jCurrentAnimationTimeMillis2);
                long j = jCurrentAnimationTimeMillis2 - aVar.f;
                aVar.f = jCurrentAnimationTimeMillis2;
                ((b91) z9Var).v.scrollListBy((int) (j * ((fA * 4.0f) + ((-4.0f) * fA * fA)) * aVar.d));
                WeakHashMap<View, in3> weakHashMap = om3.a;
                om3.d.k(view, this);
            }
        }
    }

    public z9(View view) {
        a aVar = new a();
        this.e = aVar;
        this.f = new AccelerateInterpolator();
        float[] fArr = {0.0f, 0.0f};
        this.i = fArr;
        float[] fArr2 = {Float.MAX_VALUE, Float.MAX_VALUE};
        this.j = fArr2;
        float[] fArr3 = {0.0f, 0.0f};
        this.m = fArr3;
        float[] fArr4 = {0.0f, 0.0f};
        this.n = fArr4;
        float[] fArr5 = {Float.MAX_VALUE, Float.MAX_VALUE};
        this.o = fArr5;
        this.g = view;
        float f = Resources.getSystem().getDisplayMetrics().density;
        float f2 = ((int) ((1575.0f * f) + 0.5f)) / 1000.0f;
        fArr5[0] = f2;
        fArr5[1] = f2;
        float f3 = ((int) ((f * 315.0f) + 0.5f)) / 1000.0f;
        fArr4[0] = f3;
        fArr4[1] = f3;
        this.k = 1;
        fArr2[0] = Float.MAX_VALUE;
        fArr2[1] = Float.MAX_VALUE;
        fArr[0] = 0.2f;
        fArr[1] = 0.2f;
        fArr3[0] = 0.001f;
        fArr3[1] = 0.001f;
        this.l = u;
        aVar.a = 500;
        aVar.b = 500;
    }

    public static float b(float f, float f2, float f3) {
        return f > f3 ? f3 : f < f2 ? f2 : f;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x003b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x003c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final float a(float r4, float r5, float r6, int r7) {
        /*
            r3 = this;
            float[] r0 = r3.i
            r0 = r0[r7]
            float[] r1 = r3.j
            r1 = r1[r7]
            float r0 = r0 * r5
            r2 = 0
            float r0 = b(r0, r2, r1)
            float r1 = r3.c(r4, r0)
            float r5 = r5 - r4
            float r4 = r3.c(r5, r0)
            float r4 = r4 - r1
            int r5 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            android.view.animation.AccelerateInterpolator r0 = r3.f
            if (r5 >= 0) goto L25
            float r4 = -r4
            float r4 = r0.getInterpolation(r4)
            float r4 = -r4
            goto L2d
        L25:
            int r5 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r5 <= 0) goto L36
            float r4 = r0.getInterpolation(r4)
        L2d:
            r5 = -1082130432(0xffffffffbf800000, float:-1.0)
            r0 = 1065353216(0x3f800000, float:1.0)
            float r4 = b(r4, r5, r0)
            goto L37
        L36:
            r4 = r2
        L37:
            int r5 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r5 != 0) goto L3c
            return r2
        L3c:
            float[] r0 = r3.m
            r0 = r0[r7]
            float[] r1 = r3.n
            r1 = r1[r7]
            float[] r3 = r3.o
            r3 = r3[r7]
            float r0 = r0 * r6
            if (r5 <= 0) goto L51
            float r4 = r4 * r0
            float r3 = b(r4, r1, r3)
            return r3
        L51:
            float r4 = -r4
            float r4 = r4 * r0
            float r3 = b(r4, r1, r3)
            float r3 = -r3
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.z9.a(float, float, float, int):float");
    }

    public final float c(float f, float f2) {
        if (f2 == 0.0f) {
            return 0.0f;
        }
        int i = this.k;
        if (i == 0 || i == 1) {
            if (f < f2) {
                if (f >= 0.0f) {
                    return 1.0f - (f / f2);
                }
                if (this.s && i == 1) {
                    return 1.0f;
                }
            }
        } else if (i == 2 && f < 0.0f) {
            return f / (-f2);
        }
        return 0.0f;
    }

    public final void d() {
        int i = 0;
        if (this.q) {
            this.s = false;
            return;
        }
        a aVar = this.e;
        aVar.getClass();
        long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        int i2 = (int) (jCurrentAnimationTimeMillis - aVar.e);
        int i3 = aVar.b;
        if (i2 > i3) {
            i = i3;
        } else if (i2 >= 0) {
            i = i2;
        }
        aVar.i = i;
        aVar.h = aVar.a(jCurrentAnimationTimeMillis);
        aVar.g = jCurrentAnimationTimeMillis;
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x0020  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean f() {
        /*
            r8 = this;
            z9$a r0 = r8.e
            float r1 = r0.d
            float r2 = java.lang.Math.abs(r1)
            float r1 = r1 / r2
            int r1 = (int) r1
            float r0 = r0.c
            float r2 = java.lang.Math.abs(r0)
            float r0 = r0 / r2
            int r0 = (int) r0
            r2 = 0
            if (r1 == 0) goto L54
            b91 r8 = (defpackage.b91) r8
            android.widget.ListView r8 = r8.v
            int r3 = r8.getCount()
            r4 = 1
            if (r3 != 0) goto L22
        L20:
            r8 = r2
            goto L50
        L22:
            int r5 = r8.getChildCount()
            int r6 = r8.getFirstVisiblePosition()
            int r7 = r6 + r5
            if (r1 <= 0) goto L40
            if (r7 < r3) goto L4f
            int r5 = r5 - r4
            android.view.View r1 = r8.getChildAt(r5)
            int r1 = r1.getBottom()
            int r8 = r8.getHeight()
            if (r1 > r8) goto L4f
            goto L20
        L40:
            if (r1 >= 0) goto L20
            if (r6 > 0) goto L4f
            android.view.View r8 = r8.getChildAt(r2)
            int r8 = r8.getTop()
            if (r8 < 0) goto L4f
            goto L20
        L4f:
            r8 = r4
        L50:
            if (r8 != 0) goto L53
            goto L54
        L53:
            r2 = r4
        L54:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.z9.f():boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0016  */
    @Override // android.view.View.OnTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onTouch(android.view.View r8, android.view.MotionEvent r9) {
        /*
            r7 = this;
            boolean r0 = r7.t
            r1 = 0
            if (r0 != 0) goto L6
            return r1
        L6:
            int r0 = r9.getActionMasked()
            r2 = 1
            if (r0 == 0) goto L1a
            if (r0 == r2) goto L16
            r3 = 2
            if (r0 == r3) goto L1e
            r8 = 3
            if (r0 == r8) goto L16
            goto L7b
        L16:
            r7.d()
            goto L7b
        L1a:
            r7.r = r2
            r7.p = r1
        L1e:
            float r0 = r9.getX()
            int r3 = r8.getWidth()
            float r3 = (float) r3
            android.view.View r4 = r7.g
            int r5 = r4.getWidth()
            float r5 = (float) r5
            float r0 = r7.a(r0, r3, r5, r1)
            float r9 = r9.getY()
            int r8 = r8.getHeight()
            float r8 = (float) r8
            int r3 = r4.getHeight()
            float r3 = (float) r3
            float r8 = r7.a(r9, r8, r3, r2)
            z9$a r9 = r7.e
            r9.c = r0
            r9.d = r8
            boolean r8 = r7.s
            if (r8 != 0) goto L7b
            boolean r8 = r7.f()
            if (r8 == 0) goto L7b
            z9$b r8 = r7.h
            if (r8 != 0) goto L5f
            z9$b r8 = new z9$b
            r8.<init>()
            r7.h = r8
        L5f:
            r7.s = r2
            r7.q = r2
            boolean r8 = r7.p
            if (r8 != 0) goto L74
            int r8 = r7.l
            if (r8 <= 0) goto L74
            z9$b r9 = r7.h
            long r5 = (long) r8
            java.util.WeakHashMap<android.view.View, in3> r8 = defpackage.om3.a
            om3.d.l(r4, r9, r5)
            goto L79
        L74:
            z9$b r8 = r7.h
            r8.run()
        L79:
            r7.p = r2
        L7b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.z9.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }
}
