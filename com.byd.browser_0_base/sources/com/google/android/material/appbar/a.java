package com.google.android.material.appbar;

import android.animation.ValueAnimator;
import android.graphics.drawable.Drawable;
import com.google.android.material.appbar.AppBarLayout;
import defpackage.sc1;
import java.util.Iterator;

/* JADX INFO: compiled from: AppBarLayout.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ sc1 e;
    public final /* synthetic */ AppBarLayout f;

    public a(AppBarLayout appBarLayout, sc1 sc1Var) {
        this.f = appBarLayout;
        this.e = sc1Var;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        this.e.m(fFloatValue);
        AppBarLayout appBarLayout = this.f;
        Drawable drawable = appBarLayout.v;
        if (drawable instanceof sc1) {
            ((sc1) drawable).m(fFloatValue);
        }
        Iterator it = appBarLayout.t.iterator();
        while (it.hasNext()) {
            ((AppBarLayout.e) it.next()).a();
        }
    }
}
