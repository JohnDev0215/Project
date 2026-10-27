package org.chromium.components.browser_ui.bottomsheet;

import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.view.View;
import android.view.Window;
import defpackage.t41;

/* JADX INFO: compiled from: BottomSheet.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b implements View.OnLayoutChangeListener {
    public int e;
    public final /* synthetic */ Window f;
    public final /* synthetic */ t41 g;
    public final /* synthetic */ BottomSheet h;

    public b(BottomSheet bottomSheet, Window window, t41 t41Var) {
        this.h = bottomSheet;
        this.f = window;
        this.g = t41Var;
    }

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        t41 t41Var;
        BottomSheet bottomSheet = this.h;
        int i9 = bottomSheet.m;
        int i10 = bottomSheet.n;
        int i11 = i3 - i;
        bottomSheet.m = i11;
        int i12 = i4 - i2;
        bottomSheet.n = i12;
        if (i9 != i11 || i10 != i12) {
            if (!bottomSheet.m()) {
                int i13 = bottomSheet.q;
                if (i13 == 2) {
                    bottomSheet.s(3, 0, false);
                } else if (i13 == 4 && bottomSheet.r == 2) {
                    bottomSheet.r = 3;
                }
            }
            bottomSheet.o = -1.0f;
            bottomSheet.v();
        }
        Window window = this.f;
        View decorView = window.getDecorView();
        Rect rect = bottomSheet.g;
        decorView.getWindowVisibleDisplayFrame(rect);
        int iMin = bottomSheet.y ? bottomSheet.n - Math.min(window.getDecorView().getHeight(), rect.height()) : 0;
        if (iMin != this.e) {
            TouchRestrictingFrameLayout touchRestrictingFrameLayout = bottomSheet.u;
            touchRestrictingFrameLayout.setPadding(touchRestrictingFrameLayout.getPaddingLeft(), bottomSheet.u.getPaddingTop(), bottomSheet.u.getPaddingRight(), iMin);
        }
        if (i10 != bottomSheet.n || this.e != iMin) {
            if (bottomSheet.k.d && (t41Var = this.g) != null) {
                t41Var.e(bottomSheet);
            } else if (bottomSheet.r != -1) {
                ValueAnimator valueAnimator = bottomSheet.l;
                if (valueAnimator != null) {
                    valueAnimator.cancel();
                    bottomSheet.l = null;
                }
                bottomSheet.a(bottomSheet.r, 0);
            } else {
                ValueAnimator valueAnimator2 = bottomSheet.l;
                if (valueAnimator2 != null) {
                    valueAnimator2.end();
                }
                bottomSheet.l = null;
                bottomSheet.s(bottomSheet.q, 0, false);
            }
        }
        this.e = iMin;
    }
}
