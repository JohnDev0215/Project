package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import androidx.transition.Transition;

/* JADX INFO: compiled from: Transition.java */
/* JADX INFO: loaded from: classes.dex */
public final class bf3 extends AnimatorListenerAdapter {
    public final /* synthetic */ Transition e;

    public bf3(Transition transition) {
        this.e = transition;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        this.e.r();
        animator.removeListener(this);
    }
}
