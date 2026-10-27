package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import androidx.transition.Transition;

/* JADX INFO: compiled from: Transition.java */
/* JADX INFO: loaded from: classes.dex */
public final class af3 extends AnimatorListenerAdapter {
    public final /* synthetic */ m8 e;
    public final /* synthetic */ Transition f;

    public af3(Transition transition, m8 m8Var) {
        this.f = transition;
        this.e = m8Var;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        this.e.remove(animator);
        this.f.q.remove(animator);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        this.f.q.add(animator);
    }
}
