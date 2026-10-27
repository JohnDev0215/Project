package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import com.google.android.material.floatingactionbutton.d;

/* JADX INFO: compiled from: FloatingActionButtonImpl.java */
/* JADX INFO: loaded from: classes.dex */
public final class c extends AnimatorListenerAdapter {
    public final /* synthetic */ boolean e;
    public final /* synthetic */ d.g f;
    public final /* synthetic */ d g;

    public c(d dVar, boolean z, a aVar) {
        this.g = dVar;
        this.e = z;
        this.f = aVar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        d dVar = this.g;
        dVar.r = 0;
        dVar.l = null;
        d.g gVar = this.f;
        if (gVar != null) {
            ((a) gVar).a.b();
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        d dVar = this.g;
        dVar.v.b(0, this.e);
        dVar.r = 2;
        dVar.l = animator;
    }
}
