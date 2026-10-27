package spi;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import spi.uc;

/* JADX INFO: compiled from: ViewUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class xc extends AnimatorListenerAdapter {
    public final /* synthetic */ Animator.AnimatorListener a;
    public final /* synthetic */ uc.d b;
    public final /* synthetic */ View c;

    public xc(Animator.AnimatorListener animatorListener, uc.d dVar, View view) {
        this.a = animatorListener;
        this.b = dVar;
        this.c = view;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        this.a.onAnimationEnd(animator);
        uc.d dVar = this.b;
        if (dVar != null) {
            ((q6) dVar).a(this.c);
        }
    }
}
