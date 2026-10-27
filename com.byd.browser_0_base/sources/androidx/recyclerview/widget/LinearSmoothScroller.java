package androidx.recyclerview.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PointF;
import android.util.DisplayMetrics;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public class LinearSmoothScroller extends RecyclerView.v {

    @SuppressLint({"UnknownNullness"})
    public PointF k;
    public final DisplayMetrics l;
    public float n;
    public final LinearInterpolator i = new LinearInterpolator();
    public final DecelerateInterpolator j = new DecelerateInterpolator();
    public boolean m = false;
    public int o = 0;
    public int p = 0;

    @SuppressLint({"UnknownNullness"})
    public LinearSmoothScroller(Context context) {
        this.l = context.getResources().getDisplayMetrics();
    }

    public static int e(int i, int i2, int i3, int i4, int i5) {
        if (i5 == -1) {
            return i3 - i;
        }
        if (i5 != 0) {
            if (i5 == 1) {
                return i4 - i2;
            }
            throw new IllegalArgumentException("snap preference should be one of the constants defined in SmoothScroller, starting with SNAP_");
        }
        int i6 = i3 - i;
        if (i6 > 0) {
            return i6;
        }
        int i7 = i4 - i2;
        if (i7 < 0) {
            return i7;
        }
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0015  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x005f  */
    @Override // androidx.recyclerview.widget.RecyclerView.v
    @android.annotation.SuppressLint({"UnknownNullness"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void c(android.view.View r11, androidx.recyclerview.widget.RecyclerView.v.a r12) {
        /*
            r10 = this;
            android.graphics.PointF r0 = r10.k
            r1 = 1
            r2 = -1
            r3 = 0
            r4 = 0
            if (r0 == 0) goto L15
            float r0 = r0.x
            int r0 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r0 != 0) goto Lf
            goto L15
        Lf:
            if (r0 <= 0) goto L13
            r0 = r1
            goto L16
        L13:
            r0 = r2
            goto L16
        L15:
            r0 = r4
        L16:
            androidx.recyclerview.widget.RecyclerView$m r5 = r10.c
            if (r5 == 0) goto L4f
            boolean r6 = r5.h()
            if (r6 != 0) goto L21
            goto L4f
        L21:
            android.view.ViewGroup$LayoutParams r6 = r11.getLayoutParams()
            androidx.recyclerview.widget.RecyclerView$LayoutParams r6 = (androidx.recyclerview.widget.RecyclerView.LayoutParams) r6
            int r7 = r11.getLeft()
            int r8 = androidx.recyclerview.widget.RecyclerView.m.F(r11)
            int r7 = r7 - r8
            int r8 = r6.leftMargin
            int r7 = r7 - r8
            int r8 = r11.getRight()
            int r9 = androidx.recyclerview.widget.RecyclerView.m.M(r11)
            int r9 = r9 + r8
            int r6 = r6.rightMargin
            int r9 = r9 + r6
            int r6 = r5.H()
            int r8 = r5.n
            int r5 = r5.I()
            int r8 = r8 - r5
            int r0 = e(r7, r9, r6, r8, r0)
            goto L50
        L4f:
            r0 = r4
        L50:
            android.graphics.PointF r5 = r10.k
            if (r5 == 0) goto L5f
            float r5 = r5.y
            int r3 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r3 != 0) goto L5b
            goto L5f
        L5b:
            if (r3 <= 0) goto L60
            r2 = r1
            goto L60
        L5f:
            r2 = r4
        L60:
            androidx.recyclerview.widget.RecyclerView$m r3 = r10.c
            if (r3 == 0) goto L98
            boolean r5 = r3.i()
            if (r5 != 0) goto L6b
            goto L98
        L6b:
            android.view.ViewGroup$LayoutParams r4 = r11.getLayoutParams()
            androidx.recyclerview.widget.RecyclerView$LayoutParams r4 = (androidx.recyclerview.widget.RecyclerView.LayoutParams) r4
            int r5 = r11.getTop()
            int r6 = androidx.recyclerview.widget.RecyclerView.m.O(r11)
            int r5 = r5 - r6
            int r6 = r4.topMargin
            int r5 = r5 - r6
            int r6 = r11.getBottom()
            int r11 = androidx.recyclerview.widget.RecyclerView.m.y(r11)
            int r11 = r11 + r6
            int r4 = r4.bottomMargin
            int r11 = r11 + r4
            int r4 = r3.J()
            int r6 = r3.o
            int r3 = r3.G()
            int r6 = r6 - r3
            int r4 = e(r5, r11, r4, r6, r2)
        L98:
            int r11 = r0 * r0
            int r2 = r4 * r4
            int r2 = r2 + r11
            double r2 = (double) r2
            double r2 = java.lang.Math.sqrt(r2)
            int r11 = (int) r2
            int r11 = r10.g(r11)
            double r2 = (double) r11
            r5 = 4599717252057688074(0x3fd57a786c22680a, double:0.3356)
            double r2 = r2 / r5
            double r2 = java.lang.Math.ceil(r2)
            int r11 = (int) r2
            if (r11 <= 0) goto Lc3
            int r0 = -r0
            int r2 = -r4
            android.view.animation.DecelerateInterpolator r10 = r10.j
            r12.a = r0
            r12.b = r2
            r12.c = r11
            r12.e = r10
            r12.f = r1
        Lc3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.LinearSmoothScroller.c(android.view.View, androidx.recyclerview.widget.RecyclerView$v$a):void");
    }

    @SuppressLint({"UnknownNullness"})
    public float f(DisplayMetrics displayMetrics) {
        return 25.0f / displayMetrics.densityDpi;
    }

    public int g(int i) {
        float fAbs = Math.abs(i);
        if (!this.m) {
            this.n = f(this.l);
            this.m = true;
        }
        return (int) Math.ceil(fAbs * this.n);
    }
}
