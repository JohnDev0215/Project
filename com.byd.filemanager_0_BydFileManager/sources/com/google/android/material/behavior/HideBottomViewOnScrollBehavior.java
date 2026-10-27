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
    private int f4018a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f4019b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f4020c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private ViewPropertyAnimator f4021d;

    class a extends AnimatorListenerAdapter {
        a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            HideBottomViewOnScrollBehavior.this.f4021d = null;
        }
    }

    public HideBottomViewOnScrollBehavior() {
        this.f4018a = 0;
        this.f4019b = 2;
        this.f4020c = 0;
    }

    private void F(V v, int i, long j, TimeInterpolator timeInterpolator) {
        this.f4021d = v.animate().translationY(i).setInterpolator(timeInterpolator).setDuration(j).setListener(new a());
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean A(CoordinatorLayout coordinatorLayout, V v, View view, View view2, int i, int i2) {
        return i == 2;
    }

    public void G(V v, int i) {
        this.f4020c = i;
        if (this.f4019b == 1) {
            v.setTranslationY(this.f4018a + i);
        }
    }

    public void H(V v) {
        if (this.f4019b == 1) {
            return;
        }
        ViewPropertyAnimator viewPropertyAnimator = this.f4021d;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
            v.clearAnimation();
        }
        this.f4019b = 1;
        F(v, this.f4018a + this.f4020c, 175L, c.a.a.a.m.a.f2307c);
    }

    public void I(V v) {
        if (this.f4019b == 2) {
            return;
        }
        ViewPropertyAnimator viewPropertyAnimator = this.f4021d;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
            v.clearAnimation();
        }
        this.f4019b = 2;
        F(v, 0, 225L, c.a.a.a.m.a.f2308d);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean l(CoordinatorLayout coordinatorLayout, V v, int i) {
        this.f4018a = v.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) v.getLayoutParams()).bottomMargin;
        return super.l(coordinatorLayout, v, i);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void t(CoordinatorLayout coordinatorLayout, V v, View view, int i, int i2, int i3, int i4, int i5, int[] iArr) {
        if (i2 > 0) {
            H(v);
        } else if (i2 < 0) {
            I(v);
        }
    }

    public HideBottomViewOnScrollBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f4018a = 0;
        this.f4019b = 2;
        this.f4020c = 0;
    }
}
