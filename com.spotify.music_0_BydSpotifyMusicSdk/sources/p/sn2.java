package p;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* JADX INFO: loaded from: classes.dex */
public final class sn2 extends AnimatorListenerAdapter {
    public final /* synthetic */ Object a;
    public final /* synthetic */ Object b;

    public /* synthetic */ sn2(Object obj, nb nbVar) {
        this.b = obj;
        this.a = nbVar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        ((nb) this.a).remove(animator);
        ((wn2) this.b).u.remove(animator);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        ((wn2) this.b).u.add(animator);
    }
}
