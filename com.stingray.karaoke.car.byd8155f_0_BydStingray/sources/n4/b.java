package N4;

import F1.H;
import android.view.View;
import android.view.ViewParent;
import com.bumptech.glide.c;
import com.google.android.material.behavior.SwipeDismissBehavior;

/* JADX INFO: loaded from: classes.dex */
public final class b extends c {
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4591e = -1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ SwipeDismissBehavior f4592f;

    public b(SwipeDismissBehavior swipeDismissBehavior) {
        this.f4592f = swipeDismissBehavior;
    }

    @Override // com.bumptech.glide.c
    public final boolean E0(View view, int i7) {
        int i10 = this.f4591e;
        return (i10 == -1 || i10 == i7) && this.f4592f.r(view);
    }

    @Override // com.bumptech.glide.c
    public final int M(View view, int i7) {
        int width;
        int width2;
        int width3;
        int[] iArr = H.f2303a;
        boolean z6 = view.getLayoutDirection() == 1;
        int i10 = this.f4592f.c;
        if (i10 == 0) {
            if (z6) {
                width = this.d - view.getWidth();
                width2 = this.d;
            } else {
                width = this.d;
                width3 = view.getWidth();
                width2 = width3 + width;
            }
        } else if (i10 != 1) {
            width = this.d - view.getWidth();
            width2 = this.d + view.getWidth();
        } else if (z6) {
            width = this.d;
            width3 = view.getWidth();
            width2 = width3 + width;
        } else {
            width = this.d - view.getWidth();
            width2 = this.d;
        }
        return Math.min(Math.max(width, i7), width2);
    }

    @Override // com.bumptech.glide.c
    public final int N(View view, int i7) {
        return view.getTop();
    }

    @Override // com.bumptech.glide.c
    public final int k0(View view) {
        return view.getWidth();
    }

    @Override // com.bumptech.glide.c
    public final void s0(View view, int i7) {
        this.f4591e = i7;
        this.d = view.getLeft();
        ViewParent parent = view.getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
    }

    @Override // com.bumptech.glide.c
    public final void t0(int i7) {
        this.f4592f.getClass();
    }

    @Override // com.bumptech.glide.c
    public final void u0(View view, int i7, int i10) {
        float f10 = this.d;
        float width = view.getWidth();
        SwipeDismissBehavior swipeDismissBehavior = this.f4592f;
        float f11 = (width * swipeDismissBehavior.d) + f10;
        float width2 = (view.getWidth() * swipeDismissBehavior.f12749e) + this.d;
        float f12 = i7;
        if (f12 <= f11) {
            view.setAlpha(1.0f);
        } else if (f12 >= width2) {
            view.setAlpha(0.0f);
        } else {
            view.setAlpha(Math.min(Math.max(0.0f, 1.0f - ((f12 - f11) / (width2 - f11))), 1.0f));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0061  */
    @Override // com.bumptech.glide.c
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void v0(android.view.View r9, float r10, float r11) {
        /*
            r8 = this;
            r11 = -1
            r8.f4591e = r11
            int r11 = r9.getWidth()
            r0 = 0
            int r1 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            r2 = 1
            com.google.android.material.behavior.SwipeDismissBehavior r3 = r8.f4592f
            r4 = 0
            if (r1 == 0) goto L39
            int[] r5 = F1.H.f2303a
            int r5 = r9.getLayoutDirection()
            if (r5 != r2) goto L1a
            r5 = r2
            goto L1b
        L1a:
            r5 = r4
        L1b:
            int r6 = r3.c
            r7 = 2
            if (r6 != r7) goto L21
            goto L55
        L21:
            if (r6 != 0) goto L2d
            if (r5 == 0) goto L2a
            int r10 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r10 >= 0) goto L61
            goto L55
        L2a:
            if (r1 <= 0) goto L61
            goto L55
        L2d:
            if (r6 != r2) goto L61
            if (r5 == 0) goto L34
            if (r1 <= 0) goto L61
            goto L55
        L34:
            int r10 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r10 >= 0) goto L61
            goto L55
        L39:
            int r10 = r9.getLeft()
            int r0 = r8.d
            int r10 = r10 - r0
            int r0 = r9.getWidth()
            float r0 = (float) r0
            r3.getClass()
            r1 = 1056964608(0x3f000000, float:0.5)
            float r0 = r0 * r1
            int r0 = java.lang.Math.round(r0)
            int r10 = java.lang.Math.abs(r10)
            if (r10 < r0) goto L61
        L55:
            int r10 = r9.getLeft()
            int r8 = r8.d
            if (r10 >= r8) goto L5f
            int r8 = r8 - r11
            goto L64
        L5f:
            int r8 = r8 + r11
            goto L64
        L61:
            int r8 = r8.d
            r2 = r4
        L64:
            L1.d r10 = r3.f12747a
            int r11 = r9.getTop()
            boolean r8 = r10.o(r8, r11)
            if (r8 == 0) goto L7a
            h5.t r8 = new h5.t
            r8.<init>(r3, r9, r2)
            int[] r10 = F1.H.f2303a
            r9.postOnAnimation(r8)
        L7a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: N4.b.v0(android.view.View, float, float):void");
    }
}
