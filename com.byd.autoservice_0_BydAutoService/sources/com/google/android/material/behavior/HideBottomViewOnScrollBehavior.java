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
    public int f1977a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1978b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1979c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ViewPropertyAnimator f1980d;

    public class a extends AnimatorListenerAdapter {
        public a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            HideBottomViewOnScrollBehavior.this.f1980d = null;
        }
    }

    public HideBottomViewOnScrollBehavior() {
        this.f1977a = 0;
        this.f1978b = 2;
        this.f1979c = 0;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean A(CoordinatorLayout coordinatorLayout, V v3, View view, View view2, int i3, int i4) {
        return i3 == 2;
    }

    public final void F(V v3, int i3, long j3, TimeInterpolator timeInterpolator) {
        this.f1980d = v3.animate().translationY(i3).setInterpolator(timeInterpolator).setDuration(j3).setListener(new a());
    }

    public void G(V v3, int i3) {
        this.f1979c = i3;
        if (this.f1978b == 1) {
            v3.setTranslationY(this.f1977a + i3);
        }
    }

    public void H(V v3) {
        if (this.f1978b == 1) {
            return;
        }
        ViewPropertyAnimator viewPropertyAnimator = this.f1980d;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
            v3.clearAnimation();
        }
        this.f1978b = 1;
        F(v3, this.f1977a + this.f1979c, 175L, s1.a.f4903c);
    }

    public void I(V v3) {
        if (this.f1978b == 2) {
            return;
        }
        ViewPropertyAnimator viewPropertyAnimator = this.f1980d;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
            v3.clearAnimation();
        }
        this.f1978b = 2;
        F(v3, 0, 225L, s1.a.f4904d);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean l(CoordinatorLayout coordinatorLayout, V v3, int i3) {
        this.f1977a = v3.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) v3.getLayoutParams()).bottomMargin;
        return super.l(coordinatorLayout, v3, i3);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void t(CoordinatorLayout coordinatorLayout, V v3, View view, int i3, int i4, int i5, int i6, int i7, int[] iArr) {
        if (i4 > 0) {
            H(v3);
        } else if (i4 < 0) {
            I(v3);
        }
    }

    public HideBottomViewOnScrollBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1977a = 0;
        this.f1978b = 2;
        this.f1979c = 0;
    }
}
