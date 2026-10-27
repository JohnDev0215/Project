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

/* JADX INFO: loaded from: classes.dex */
public class HideBottomViewOnScrollBehavior<V extends View> extends CoordinatorLayout.c<V> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f3715a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f3716b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f3717c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private ViewPropertyAnimator f3718d;

    class a extends AnimatorListenerAdapter {
        a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            HideBottomViewOnScrollBehavior.this.f3718d = null;
        }
    }

    public HideBottomViewOnScrollBehavior() {
        this.f3715a = 0;
        this.f3716b = 2;
        this.f3717c = 0;
    }

    private void F(V v, int i2, long j, TimeInterpolator timeInterpolator) {
        this.f3718d = v.animate().translationY(i2).setInterpolator(timeInterpolator).setDuration(j).setListener(new a());
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean A(CoordinatorLayout coordinatorLayout, V v, View view, View view2, int i2, int i3) {
        return i2 == 2;
    }

    public void G(V v, int i2) {
        this.f3717c = i2;
        if (this.f3716b == 1) {
            v.setTranslationY(this.f3715a + i2);
        }
    }

    public void H(V v) {
        if (this.f3716b == 1) {
            return;
        }
        ViewPropertyAnimator viewPropertyAnimator = this.f3718d;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
            v.clearAnimation();
        }
        this.f3716b = 1;
        F(v, this.f3715a + this.f3717c, 175L, d.b.a.a.m.a.f4676c);
    }

    public void I(V v) {
        if (this.f3716b == 2) {
            return;
        }
        ViewPropertyAnimator viewPropertyAnimator = this.f3718d;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
            v.clearAnimation();
        }
        this.f3716b = 2;
        F(v, 0, 225L, d.b.a.a.m.a.f4677d);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean l(CoordinatorLayout coordinatorLayout, V v, int i2) {
        this.f3715a = v.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) v.getLayoutParams()).bottomMargin;
        return super.l(coordinatorLayout, v, i2);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void t(CoordinatorLayout coordinatorLayout, V v, View view, int i2, int i3, int i4, int i5, int i6, int[] iArr) {
        if (i3 > 0) {
            H(v);
        } else if (i3 < 0) {
            I(v);
        }
    }

    public HideBottomViewOnScrollBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3715a = 0;
        this.f3716b = 2;
        this.f3717c = 0;
    }
}
