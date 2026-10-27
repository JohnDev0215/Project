package com.google.android.material.behavior;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* JADX INFO: loaded from: classes2.dex */
public class HideBottomViewOnScrollBehavior<V extends View> extends CoordinatorLayout.c<V> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f3560a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f3561b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f3562c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ViewPropertyAnimator f3563d;

    public class a extends AnimatorListenerAdapter {
        public a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            HideBottomViewOnScrollBehavior.this.f3563d = null;
        }
    }

    public HideBottomViewOnScrollBehavior() {
        this.f3560a = 0;
        this.f3561b = 2;
        this.f3562c = 0;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean A(CoordinatorLayout coordinatorLayout, V v7, View view, View view2, int i8, int i9) {
        return i8 == 2;
    }

    public final void F(V v7, int i8, long j8, TimeInterpolator timeInterpolator) {
        this.f3563d = v7.animate().translationY(i8).setInterpolator(timeInterpolator).setDuration(j8).setListener(new a());
    }

    public void G(V v7, int i8) {
        this.f3562c = i8;
        if (this.f3561b == 1) {
            v7.setTranslationY(this.f3560a + i8);
        }
    }

    public void H(V v7) {
        if (this.f3561b == 1) {
            return;
        }
        ViewPropertyAnimator viewPropertyAnimator = this.f3563d;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
            v7.clearAnimation();
        }
        this.f3561b = 1;
        F(v7, this.f3560a + this.f3562c, 175L, j5.a.f5580c);
    }

    public void I(V v7) {
        if (this.f3561b == 2) {
            return;
        }
        ViewPropertyAnimator viewPropertyAnimator = this.f3563d;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
            v7.clearAnimation();
        }
        this.f3561b = 2;
        F(v7, 0, 225L, j5.a.f5581d);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean l(CoordinatorLayout coordinatorLayout, V v7, int i8) {
        this.f3560a = v7.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) v7.getLayoutParams()).bottomMargin;
        return super.l(coordinatorLayout, v7, i8);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void t(CoordinatorLayout coordinatorLayout, V v7, View view, int i8, int i9, int i10, int i11, int i12, int[] iArr) {
        if (i9 > 0) {
            H(v7);
        } else if (i9 < 0) {
            I(v7);
        }
    }

    public HideBottomViewOnScrollBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3560a = 0;
        this.f3561b = 2;
        this.f3562c = 0;
    }
}
