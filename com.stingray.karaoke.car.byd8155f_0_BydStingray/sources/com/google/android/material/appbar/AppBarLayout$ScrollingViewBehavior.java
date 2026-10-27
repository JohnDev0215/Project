package com.google.android.material.appbar;

import F1.H;
import M4.a;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import java.util.ArrayList;
import t1.AbstractC2425a;
import t1.C2428d;

/* JADX INFO: loaded from: classes.dex */
public class AppBarLayout$ScrollingViewBehavior extends a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f12741b;

    public AppBarLayout$ScrollingViewBehavior() {
        new Rect();
        new Rect();
    }

    public static void s(ArrayList arrayList) {
        int size = arrayList.size();
        for (int i7 = 0; i7 < size; i7++) {
        }
    }

    @Override // t1.AbstractC2425a
    public final boolean d(View view, View view2) {
        int i7;
        AbstractC2425a abstractC2425a = ((C2428d) view2.getLayoutParams()).f22429a;
        if (abstractC2425a instanceof AppBarLayout$BaseBehavior) {
            int bottom = view2.getBottom() - view.getTop();
            ((AppBarLayout$BaseBehavior) abstractC2425a).getClass();
            int i10 = this.f12741b;
            if (i10 == 0 || (i7 = (int) (0.0f * i10)) < 0) {
                i10 = 0;
            } else if (i7 <= i10) {
                i10 = i7;
            }
            int i11 = bottom - i10;
            int[] iArr = H.f2303a;
            view.offsetTopAndBottom(i11);
        }
        return false;
    }

    @Override // t1.AbstractC2425a
    public final boolean h(CoordinatorLayout coordinatorLayout, View view) {
        int i7 = view.getLayoutParams().height;
        if (i7 != -1 && i7 != -2) {
            return false;
        }
        s(coordinatorLayout.j(view));
        return false;
    }

    @Override // t1.AbstractC2425a
    public final void l(CoordinatorLayout coordinatorLayout, View view) {
        s(coordinatorLayout.j(view));
    }

    @Override // M4.a
    public final void r(CoordinatorLayout coordinatorLayout, View view, int i7) {
        s(coordinatorLayout.j(view));
        coordinatorLayout.q(view, i7);
    }

    public AppBarLayout$ScrollingViewBehavior(Context context, AttributeSet attributeSet) {
        new Rect();
        new Rect();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, K4.a.f3597q);
        this.f12741b = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, 0);
        typedArrayObtainStyledAttributes.recycle();
    }
}
