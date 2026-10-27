package com.google.android.material.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.Interpolator;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import defpackage.lu0;
import defpackage.u5;
import java.util.Iterator;
import java.util.LinkedHashSet;

/* JADX INFO: loaded from: classes.dex */
public class HideBottomViewOnScrollBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {
    public final LinkedHashSet<a> a;
    public int b;
    public int c;
    public int d;
    public ViewPropertyAnimator e;

    public interface a {
        void a();
    }

    public HideBottomViewOnScrollBehavior() {
        this.a = new LinkedHashSet<>();
        this.b = 0;
        this.c = 2;
        this.d = 0;
    }

    public void addOnScrollStateChangedListener(a aVar) {
        this.a.add(aVar);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean h(CoordinatorLayout coordinatorLayout, V v, int i) {
        this.b = v.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) v.getLayoutParams()).bottomMargin;
        return false;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final void l(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3, int[] iArr) {
        LinkedHashSet<a> linkedHashSet = this.a;
        if (i > 0) {
            if (this.c == 1) {
                return;
            }
            ViewPropertyAnimator viewPropertyAnimator = this.e;
            if (viewPropertyAnimator != null) {
                viewPropertyAnimator.cancel();
                view.clearAnimation();
            }
            this.c = 1;
            Iterator<a> it = linkedHashSet.iterator();
            while (it.hasNext()) {
                it.next().a();
            }
            s(view, this.b + this.d, 175L, u5.c);
            return;
        }
        if (i < 0) {
            if (this.c == 2) {
                return;
            }
            ViewPropertyAnimator viewPropertyAnimator2 = this.e;
            if (viewPropertyAnimator2 != null) {
                viewPropertyAnimator2.cancel();
                view.clearAnimation();
            }
            this.c = 2;
            Iterator<a> it2 = linkedHashSet.iterator();
            while (it2.hasNext()) {
                it2.next().a();
            }
            s(view, 0, 225L, u5.d);
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean p(CoordinatorLayout coordinatorLayout, V v, View view, View view2, int i, int i2) {
        return i == 2;
    }

    public void removeOnScrollStateChangedListener(a aVar) {
        this.a.remove(aVar);
    }

    public final void s(View view, int i, long j, Interpolator interpolator) {
        this.e = view.animate().translationY(i).setInterpolator(interpolator).setDuration(j).setListener(new lu0(this));
    }

    public HideBottomViewOnScrollBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = new LinkedHashSet<>();
        this.b = 0;
        this.c = 2;
        this.d = 0;
    }
}
