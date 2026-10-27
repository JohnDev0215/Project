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
    private int f4123a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f4124b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f4125c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private ViewPropertyAnimator f4126d;

    class a extends AnimatorListenerAdapter {
        a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            HideBottomViewOnScrollBehavior.this.f4126d = null;
        }
    }

    public HideBottomViewOnScrollBehavior() {
        this.f4123a = 0;
        this.f4124b = 2;
        this.f4125c = 0;
    }

    private void F(V v6, int i7, long j7, TimeInterpolator timeInterpolator) {
        this.f4126d = v6.animate().translationY(i7).setInterpolator(timeInterpolator).setDuration(j7).setListener(new a());
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean A(CoordinatorLayout coordinatorLayout, V v6, View view, View view2, int i7, int i8) {
        return i7 == 2;
    }

    public void G(V v6, int i7) {
        this.f4125c = i7;
        if (this.f4124b == 1) {
            v6.setTranslationY(this.f4123a + i7);
        }
    }

    public void H(V v6) {
        if (this.f4124b == 1) {
            return;
        }
        ViewPropertyAnimator viewPropertyAnimator = this.f4126d;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
            v6.clearAnimation();
        }
        this.f4124b = 1;
        F(v6, this.f4123a + this.f4125c, 175L, p2.a.f8822c);
    }

    public void I(V v6) {
        if (this.f4124b == 2) {
            return;
        }
        ViewPropertyAnimator viewPropertyAnimator = this.f4126d;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
            v6.clearAnimation();
        }
        this.f4124b = 2;
        F(v6, 0, 225L, p2.a.f8823d);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean l(CoordinatorLayout coordinatorLayout, V v6, int i7) {
        this.f4123a = v6.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) v6.getLayoutParams()).bottomMargin;
        return super.l(coordinatorLayout, v6, i7);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void t(CoordinatorLayout coordinatorLayout, V v6, View view, int i7, int i8, int i9, int i10, int i11, int[] iArr) {
        if (i8 > 0) {
            H(v6);
        } else if (i8 < 0) {
            I(v6);
        }
    }

    public HideBottomViewOnScrollBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f4123a = 0;
        this.f4124b = 2;
        this.f4125c = 0;
    }
}
