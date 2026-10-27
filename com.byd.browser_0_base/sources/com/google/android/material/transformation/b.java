package com.google.android.material.transformation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.drawable.Drawable;

/* JADX INFO: compiled from: FabTransformationBehavior.java */
/* JADX INFO: loaded from: classes.dex */
public final class b extends AnimatorListenerAdapter {
    public final /* synthetic */ com.google.android.material.circularreveal.c e;
    public final /* synthetic */ Drawable f;

    public b(com.google.android.material.circularreveal.c cVar, Drawable drawable) {
        this.e = cVar;
        this.f = drawable;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        this.e.setCircularRevealOverlayDrawable(null);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        this.e.setCircularRevealOverlayDrawable(this.f);
    }
}
