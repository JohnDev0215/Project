package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import com.google.android.material.floatingactionbutton.d;

/* JADX INFO: compiled from: FloatingActionButtonImpl.java */
/* JADX INFO: loaded from: classes.dex */
public final class b extends AnimatorListenerAdapter {
    public boolean e;
    public final /* synthetic */ boolean f;
    public final /* synthetic */ d.g g;
    public final /* synthetic */ d h;

    public b(d dVar, boolean z, a aVar) {
        this.h = dVar;
        this.f = z;
        this.g = aVar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        this.e = true;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        d dVar = this.h;
        dVar.r = 0;
        dVar.l = null;
        if (this.e) {
            return;
        }
        boolean z = this.f;
        dVar.v.b(z ? 8 : 4, z);
        d.g gVar = this.g;
        if (gVar != null) {
            a aVar = (a) gVar;
            aVar.a.a(aVar.b);
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        d dVar = this.h;
        dVar.v.b(0, this.f);
        dVar.r = 1;
        dVar.l = animator;
        this.e = false;
    }
}
