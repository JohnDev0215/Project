package com.google.android.material.tabs;

import android.animation.ValueAnimator;
import android.view.View;
import com.google.android.material.tabs.TabLayout;

/* JADX INFO: compiled from: TabLayout.java */
/* JADX INFO: loaded from: classes.dex */
public final class b implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ View e;
    public final /* synthetic */ View f;
    public final /* synthetic */ TabLayout.f g;

    public b(TabLayout.f fVar, View view, View view2) {
        this.g = fVar;
        this.e = view;
        this.f = view2;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.g.c(this.e, this.f, valueAnimator.getAnimatedFraction());
    }
}
