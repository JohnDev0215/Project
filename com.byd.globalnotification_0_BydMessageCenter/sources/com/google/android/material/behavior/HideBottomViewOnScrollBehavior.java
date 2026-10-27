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
    public int f2361a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2362b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f2363c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ViewPropertyAnimator f2364d;

    public class a extends AnimatorListenerAdapter {
        public a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            HideBottomViewOnScrollBehavior.this.f2364d = null;
        }
    }

    public HideBottomViewOnScrollBehavior() {
        this.f2361a = 0;
        this.f2362b = 2;
        this.f2363c = 0;
    }

    public HideBottomViewOnScrollBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2361a = 0;
        this.f2362b = 2;
        this.f2363c = 0;
    }

    public final void B(V v, int i, long j, TimeInterpolator timeInterpolator) {
        this.f2364d = v.animate().translationY(i).setInterpolator(timeInterpolator).setDuration(j).setListener(new a());
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean k(CoordinatorLayout coordinatorLayout, V v, int i) {
        this.f2361a = v.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) v.getLayoutParams()).bottomMargin;
        return false;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void r(CoordinatorLayout coordinatorLayout, V v, View view, int i, int i2, int i3, int i4, int i5, int[] iArr) {
        if (i2 > 0) {
            if (this.f2362b == 1) {
                return;
            }
            ViewPropertyAnimator viewPropertyAnimator = this.f2364d;
            if (viewPropertyAnimator != null) {
                viewPropertyAnimator.cancel();
                v.clearAnimation();
            }
            this.f2362b = 1;
            B(v, this.f2361a + this.f2363c, 175L, b.d.a.a.l.a.f1968c);
            return;
        }
        if (i2 >= 0 || this.f2362b == 2) {
            return;
        }
        ViewPropertyAnimator viewPropertyAnimator2 = this.f2364d;
        if (viewPropertyAnimator2 != null) {
            viewPropertyAnimator2.cancel();
            v.clearAnimation();
        }
        this.f2362b = 2;
        B(v, 0, 225L, b.d.a.a.l.a.f1969d);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean x(CoordinatorLayout coordinatorLayout, V v, View view, View view2, int i, int i2) {
        return i == 2;
    }
}
