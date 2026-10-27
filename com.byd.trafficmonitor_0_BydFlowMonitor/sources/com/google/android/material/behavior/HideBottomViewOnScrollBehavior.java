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
    public int f4505a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f4506b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f4507c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ViewPropertyAnimator f4508d;

    public class a extends AnimatorListenerAdapter {
        public a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            HideBottomViewOnScrollBehavior.this.f4508d = null;
        }
    }

    public HideBottomViewOnScrollBehavior() {
        this.f4505a = 0;
        this.f4506b = 2;
        this.f4507c = 0;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean A(CoordinatorLayout coordinatorLayout, V v, View view, View view2, int i2, int i3) {
        return i2 == 2;
    }

    public final void F(V v, int i2, long j, TimeInterpolator timeInterpolator) {
        this.f4508d = v.animate().translationY(i2).setInterpolator(timeInterpolator).setDuration(j).setListener(new a());
    }

    public void G(V v, int i2) {
        this.f4507c = i2;
        if (this.f4506b == 1) {
            v.setTranslationY(this.f4505a + i2);
        }
    }

    public void H(V v) {
        if (this.f4506b == 1) {
            return;
        }
        ViewPropertyAnimator viewPropertyAnimator = this.f4508d;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
            v.clearAnimation();
        }
        this.f4506b = 1;
        F(v, this.f4505a + this.f4507c, 175L, c.b.a.a.m.a.f4010c);
    }

    public void I(V v) {
        if (this.f4506b == 2) {
            return;
        }
        ViewPropertyAnimator viewPropertyAnimator = this.f4508d;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
            v.clearAnimation();
        }
        this.f4506b = 2;
        F(v, 0, 225L, c.b.a.a.m.a.f4011d);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean l(CoordinatorLayout coordinatorLayout, V v, int i2) {
        this.f4505a = v.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) v.getLayoutParams()).bottomMargin;
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
        this.f4505a = 0;
        this.f4506b = 2;
        this.f4507c = 0;
    }
}
