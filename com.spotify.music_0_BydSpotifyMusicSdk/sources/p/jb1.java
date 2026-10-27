package p;

import android.content.res.Resources;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.widget.ListView;

/* JADX INFO: loaded from: classes.dex */
public final class jb1 implements View.OnTouchListener {
    public static final int z = ViewConfiguration.getTapTimeout();
    public final ug c;
    public final AccelerateInterpolator e;
    public final View k;
    public rv l;
    public final float[] m;
    public final float[] n;
    public final int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int f139p;
    public final float[] q;
    public final float[] r;
    public final float[] s;
    public boolean t;
    public boolean u;
    public boolean v;
    public boolean w;
    public boolean x;
    public final ListView y;

    public jb1(ListView listView) {
        ug ugVar = new ug();
        ugVar.e = Long.MIN_VALUE;
        ugVar.g = -1L;
        ugVar.f = 0L;
        this.c = ugVar;
        this.e = new AccelerateInterpolator();
        this.m = new float[]{0.0f, 0.0f};
        this.n = new float[]{Float.MAX_VALUE, Float.MAX_VALUE};
        this.q = new float[]{0.0f, 0.0f};
        this.r = new float[]{0.0f, 0.0f};
        this.s = new float[]{Float.MAX_VALUE, Float.MAX_VALUE};
        this.k = listView;
        float f = Resources.getSystem().getDisplayMetrics().density;
        float[] fArr = this.s;
        float f2 = ((int) ((1575.0f * f) + 0.5f)) / 1000.0f;
        fArr[0] = f2;
        fArr[1] = f2;
        float[] fArr2 = this.r;
        float f3 = ((int) ((f * 315.0f) + 0.5f)) / 1000.0f;
        fArr2[0] = f3;
        fArr2[1] = f3;
        this.o = 1;
        float[] fArr3 = this.n;
        fArr3[0] = Float.MAX_VALUE;
        fArr3[1] = Float.MAX_VALUE;
        float[] fArr4 = this.m;
        fArr4[0] = 0.2f;
        fArr4[1] = 0.2f;
        float[] fArr5 = this.q;
        fArr5[0] = 0.001f;
        fArr5[1] = 0.001f;
        this.f139p = z;
        ugVar.a = 500;
        ugVar.b = 500;
        this.y = listView;
    }

    public static float b(float f, float f2, float f3) {
        return f > f3 ? f3 : f < f2 ? f2 : f;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:19:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final float a(int r4, float r5, float r6, float r7) {
        /*
            r3 = this;
            float[] r0 = r3.m
            r0 = r0[r4]
            float[] r1 = r3.n
            r1 = r1[r4]
            float r0 = r0 * r6
            r2 = 0
            float r0 = b(r0, r2, r1)
            float r1 = r3.c(r5, r0)
            float r6 = r6 - r5
            float r5 = r3.c(r6, r0)
            float r5 = r5 - r1
            int r6 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            android.view.animation.AccelerateInterpolator r0 = r3.e
            if (r6 >= 0) goto L25
            float r5 = -r5
            float r5 = r0.getInterpolation(r5)
            float r5 = -r5
            goto L2d
        L25:
            int r6 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r6 <= 0) goto L36
            float r5 = r0.getInterpolation(r5)
        L2d:
            r6 = -1082130432(0xffffffffbf800000, float:-1.0)
            r0 = 1065353216(0x3f800000, float:1.0)
            float r5 = b(r5, r6, r0)
            goto L37
        L36:
            r5 = r2
        L37:
            int r6 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r6 != 0) goto L3c
            goto L58
        L3c:
            float[] r0 = r3.q
            r0 = r0[r4]
            float[] r1 = r3.r
            r1 = r1[r4]
            float[] r2 = r3.s
            r4 = r2[r4]
            float r0 = r0 * r7
            if (r6 <= 0) goto L51
            float r5 = r5 * r0
            float r2 = b(r5, r1, r4)
            goto L58
        L51:
            float r5 = -r5
            float r5 = r5 * r0
            float r4 = b(r5, r1, r4)
            float r2 = -r4
        L58:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: p.jb1.a(int, float, float, float):float");
    }

    public final float c(float f, float f2) {
        if (f2 == 0.0f) {
            return 0.0f;
        }
        int i = this.o;
        if (i == 0 || i == 1) {
            if (f < f2) {
                return f >= 0.0f ? 1.0f - (f / f2) : (this.w && i == 1) ? 1.0f : 0.0f;
            }
            return 0.0f;
        }
        if (i == 2 && f < 0.0f) {
            return f / (-f2);
        }
        return 0.0f;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0016  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean d(android.view.View r8, android.view.MotionEvent r9) {
        /*
            r7 = this;
            boolean r0 = r7.x
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
            goto L7c
        L16:
            r7.e()
            goto L7c
        L1a:
            r7.v = r2
            r7.t = r1
        L1e:
            float r0 = r9.getX()
            int r3 = r8.getWidth()
            float r3 = (float) r3
            android.view.View r4 = r7.k
            int r5 = r4.getWidth()
            float r5 = (float) r5
            float r0 = r7.a(r1, r0, r3, r5)
            float r9 = r9.getY()
            int r8 = r8.getHeight()
            float r8 = (float) r8
            int r3 = r4.getHeight()
            float r3 = (float) r3
            float r8 = r7.a(r2, r9, r8, r3)
            p.ug r9 = r7.c
            r9.c = r0
            r9.d = r8
            boolean r8 = r7.w
            if (r8 != 0) goto L7c
            boolean r8 = r7.f()
            if (r8 == 0) goto L7c
            p.rv r8 = r7.l
            if (r8 != 0) goto L60
            p.rv r8 = new p.rv
            r9 = 4
            r8.<init>(r9, r7)
            r7.l = r8
        L60:
            r7.w = r2
            r7.u = r2
            boolean r8 = r7.t
            if (r8 != 0) goto L75
            int r8 = r7.f139p
            if (r8 <= 0) goto L75
            p.rv r9 = r7.l
            long r5 = (long) r8
            java.util.WeakHashMap r8 = p.zt2.a
            p.it2.n(r4, r9, r5)
            goto L7a
        L75:
            p.rv r8 = r7.l
            r8.run()
        L7a:
            r7.t = r2
        L7c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p.jb1.d(android.view.View, android.view.MotionEvent):boolean");
    }

    public final void e() {
        int i = 0;
        if (this.u) {
            this.w = false;
            return;
        }
        ug ugVar = this.c;
        ugVar.getClass();
        long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        int i2 = (int) (jCurrentAnimationTimeMillis - ugVar.e);
        int i3 = ugVar.b;
        if (i2 > i3) {
            i = i3;
        } else if (i2 >= 0) {
            i = i2;
        }
        ugVar.i = i;
        ugVar.h = ugVar.a(jCurrentAnimationTimeMillis);
        ugVar.g = jCurrentAnimationTimeMillis;
    }

    public final boolean f() {
        ListView listView;
        int count;
        ug ugVar = this.c;
        float f = ugVar.d;
        int iAbs = (int) (f / Math.abs(f));
        Math.abs(ugVar.c);
        if (iAbs == 0 || (count = (listView = this.y).getCount()) == 0) {
            return false;
        }
        int childCount = listView.getChildCount();
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        int i = firstVisiblePosition + childCount;
        if (iAbs > 0) {
            if (i >= count && listView.getChildAt(childCount - 1).getBottom() <= listView.getHeight()) {
                return false;
            }
        } else {
            if (iAbs >= 0) {
                return false;
            }
            if (firstVisiblePosition <= 0 && listView.getChildAt(0).getTop() >= 0) {
                return false;
            }
        }
        return true;
    }

    @Override // android.view.View.OnTouchListener
    public final /* bridge */ /* synthetic */ boolean onTouch(View view, MotionEvent motionEvent) {
        d(view, motionEvent);
        return false;
    }
}
