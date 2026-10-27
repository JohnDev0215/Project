package com.google.android.material.transformation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import com.google.android.material.circularreveal.c;

/* JADX INFO: compiled from: FabTransformationBehavior.java */
/* JADX INFO: loaded from: classes.dex */
public final class c extends AnimatorListenerAdapter {
    public final /* synthetic */ com.google.android.material.circularreveal.c e;

    public c(com.google.android.material.circularreveal.c cVar) {
        this.e = cVar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        com.google.android.material.circularreveal.c cVar = this.e;
        c.d revealInfo = cVar.getRevealInfo();
        revealInfo.c = Float.MAX_VALUE;
        cVar.setRevealInfo(revealInfo);
    }
}
