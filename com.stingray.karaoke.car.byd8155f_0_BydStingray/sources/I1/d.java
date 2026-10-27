package I1;

import B5.RunnableC0079c;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import r.AbstractC2231T;

/* JADX INFO: loaded from: classes.dex */
public final class d implements View.OnTouchListener {

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public static final int f2915C = ViewConfiguration.getTapTimeout();

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public boolean f2916A;
    public final AbstractC2231T B;
    public final a l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final AccelerateInterpolator f2917m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final ListView f2918n;
    public RunnableC0079c o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final float[] f2919p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final float[] f2920q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final int f2921r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final int f2922s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final float[] f2923t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final float[] f2924u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final float[] f2925v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f2926w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f2927x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f2928y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f2929z;

    public d(AbstractC2231T abstractC2231T) {
        a aVar = new a();
        aVar.f2910e = Long.MIN_VALUE;
        aVar.f2912g = -1L;
        aVar.f2911f = 0L;
        this.l = aVar;
        this.f2917m = new AccelerateInterpolator();
        float[] fArr = {0.0f, 0.0f};
        this.f2919p = fArr;
        float[] fArr2 = {Float.MAX_VALUE, Float.MAX_VALUE};
        this.f2920q = fArr2;
        float[] fArr3 = {0.0f, 0.0f};
        this.f2923t = fArr3;
        float[] fArr4 = {0.0f, 0.0f};
        this.f2924u = fArr4;
        float[] fArr5 = {Float.MAX_VALUE, Float.MAX_VALUE};
        this.f2925v = fArr5;
        this.f2918n = abstractC2231T;
        float f10 = Resources.getSystem().getDisplayMetrics().density;
        float f11 = ((int) ((1575.0f * f10) + 0.5f)) / 1000.0f;
        fArr5[0] = f11;
        fArr5[1] = f11;
        float f12 = ((int) ((f10 * 315.0f) + 0.5f)) / 1000.0f;
        fArr4[0] = f12;
        fArr4[1] = f12;
        this.f2921r = 1;
        fArr2[0] = Float.MAX_VALUE;
        fArr2[1] = Float.MAX_VALUE;
        fArr[0] = 0.2f;
        fArr[1] = 0.2f;
        fArr3[0] = 0.001f;
        fArr3[1] = 0.001f;
        this.f2922s = f2915C;
        aVar.f2908a = 500;
        aVar.f2909b = 500;
        this.B = abstractC2231T;
    }

    public static float b(float f10, float f11, float f12) {
        return f10 > f12 ? f12 : f10 < f11 ? f11 : f10;
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
            float[] r0 = r3.f2919p
            r0 = r0[r7]
            float[] r1 = r3.f2920q
            r1 = r1[r7]
            float r0 = r0 * r5
            r2 = 0
            float r0 = b(r0, r2, r1)
            float r1 = r3.c(r4, r0)
            float r5 = r5 - r4
            float r4 = r3.c(r5, r0)
            float r4 = r4 - r1
            int r5 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            android.view.animation.AccelerateInterpolator r0 = r3.f2917m
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
            float[] r0 = r3.f2923t
            r0 = r0[r7]
            float[] r1 = r3.f2924u
            r1 = r1[r7]
            float[] r3 = r3.f2925v
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
        throw new UnsupportedOperationException("Method not decompiled: I1.d.a(float, float, float, int):float");
    }

    public final float c(float f10, float f11) {
        if (f11 == 0.0f) {
            return 0.0f;
        }
        int i7 = this.f2921r;
        if (i7 == 0 || i7 == 1) {
            if (f10 < f11) {
                if (f10 >= 0.0f) {
                    return 1.0f - (f10 / f11);
                }
                if (this.f2929z && i7 == 1) {
                    return 1.0f;
                }
            }
        } else if (i7 == 2 && f10 < 0.0f) {
            return f10 / (-f11);
        }
        return 0.0f;
    }

    public final void d() {
        int i7 = 0;
        if (this.f2927x) {
            this.f2929z = false;
            return;
        }
        a aVar = this.l;
        long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        int i10 = (int) (jCurrentAnimationTimeMillis - aVar.f2910e);
        int i11 = aVar.f2909b;
        if (i10 > i11) {
            i7 = i11;
        } else if (i10 >= 0) {
            i7 = i10;
        }
        aVar.f2914i = i7;
        aVar.f2913h = aVar.a(jCurrentAnimationTimeMillis);
        aVar.f2912g = jCurrentAnimationTimeMillis;
    }

    public final boolean e() {
        AbstractC2231T abstractC2231T;
        int count;
        a aVar = this.l;
        float f10 = aVar.d;
        int iAbs = (int) (f10 / Math.abs(f10));
        Math.abs(aVar.c);
        if (iAbs == 0 || (count = (abstractC2231T = this.B).getCount()) == 0) {
            return false;
        }
        int childCount = abstractC2231T.getChildCount();
        int firstVisiblePosition = abstractC2231T.getFirstVisiblePosition();
        int i7 = firstVisiblePosition + childCount;
        if (iAbs > 0) {
            if (i7 >= count && abstractC2231T.getChildAt(childCount - 1).getBottom() <= abstractC2231T.getHeight()) {
                return false;
            }
        } else {
            if (iAbs >= 0) {
                return false;
            }
            if (firstVisiblePosition <= 0 && abstractC2231T.getChildAt(0).getTop() >= 0) {
                return false;
            }
        }
        return true;
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
            boolean r0 = r7.f2916A
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
            r7.d()
            goto L7c
        L1a:
            r7.f2928y = r2
            r7.f2926w = r1
        L1e:
            float r0 = r9.getX()
            int r3 = r8.getWidth()
            float r3 = (float) r3
            android.widget.ListView r4 = r7.f2918n
            int r5 = r4.getWidth()
            float r5 = (float) r5
            float r0 = r7.a(r0, r3, r5, r1)
            float r9 = r9.getY()
            int r8 = r8.getHeight()
            float r8 = (float) r8
            int r3 = r4.getHeight()
            float r3 = (float) r3
            float r8 = r7.a(r9, r8, r3, r2)
            I1.a r9 = r7.l
            r9.c = r0
            r9.d = r8
            boolean r8 = r7.f2929z
            if (r8 != 0) goto L7c
            boolean r8 = r7.e()
            if (r8 == 0) goto L7c
            B5.c r8 = r7.o
            if (r8 != 0) goto L60
            B5.c r8 = new B5.c
            r9 = 4
            r8.<init>(r7, r9)
            r7.o = r8
        L60:
            r7.f2929z = r2
            r7.f2927x = r2
            boolean r8 = r7.f2926w
            if (r8 != 0) goto L75
            int r8 = r7.f2922s
            if (r8 <= 0) goto L75
            B5.c r9 = r7.o
            long r5 = (long) r8
            int[] r8 = F1.H.f2303a
            r4.postOnAnimationDelayed(r9, r5)
            goto L7a
        L75:
            B5.c r8 = r7.o
            r8.run()
        L7a:
            r7.f2926w = r2
        L7c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: I1.d.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }
}
