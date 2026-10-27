package k4;

import android.animation.Animator;

/* JADX INFO: compiled from: BydAnimatorListener.java */
/* JADX INFO: loaded from: classes2.dex */
public interface a extends Animator.AnimatorListener {
    @Override // android.animation.Animator.AnimatorListener
    default void onAnimationCancel(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    default void onAnimationEnd(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    default void onAnimationRepeat(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    default void onAnimationStart(Animator animator) {
    }
}
