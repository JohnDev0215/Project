package com.google.android.material.behavior;

import L4.a;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import t1.AbstractC2425a;

/* JADX INFO: loaded from: classes.dex */
public class HideBottomViewOnScrollBehavior<V extends View> extends AbstractC2425a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f12745a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f12746b = 2;
    public ViewPropertyAnimator c;

    public HideBottomViewOnScrollBehavior() {
    }

    @Override // t1.AbstractC2425a
    public boolean g(CoordinatorLayout coordinatorLayout, View view, int i7) {
        this.f12745a = view.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).bottomMargin;
        return false;
    }

    @Override // t1.AbstractC2425a
    public final void k(CoordinatorLayout coordinatorLayout, View view, int i7, int i10, int i11, int[] iArr) {
        if (i7 > 0) {
            if (this.f12746b == 1) {
                return;
            }
            ViewPropertyAnimator viewPropertyAnimator = this.c;
            if (viewPropertyAnimator != null) {
                viewPropertyAnimator.cancel();
                view.clearAnimation();
            }
            this.f12746b = 1;
            this.c = view.animate().translationY(this.f12745a).setInterpolator(a.c).setDuration(175L).setListener(new N4.a(this, 0));
            return;
        }
        if (i7 >= 0 || this.f12746b == 2) {
            return;
        }
        ViewPropertyAnimator viewPropertyAnimator2 = this.c;
        if (viewPropertyAnimator2 != null) {
            viewPropertyAnimator2.cancel();
            view.clearAnimation();
        }
        this.f12746b = 2;
        this.c = view.animate().translationY(0).setInterpolator(a.d).setDuration(225L).setListener(new N4.a(this, 0));
    }

    @Override // t1.AbstractC2425a
    public boolean o(View view, int i7, int i10) {
        return i7 == 2;
    }

    public HideBottomViewOnScrollBehavior(Context context, AttributeSet attributeSet) {
    }
}
