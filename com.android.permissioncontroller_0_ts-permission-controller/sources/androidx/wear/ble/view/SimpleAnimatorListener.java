package androidx.wear.ble.view;

import android.animation.Animator;

/* JADX INFO: loaded from: classes7.dex */
public class SimpleAnimatorListener implements Animator.AnimatorListener {
    private boolean mWasCanceled;

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animator) {
        this.mWasCanceled = true;
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        if (!this.mWasCanceled) {
            onAnimationComplete(animator);
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationRepeat(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
        this.mWasCanceled = false;
    }

    public void onAnimationComplete(Animator animator) {
    }

    public boolean wasCanceled() {
        return this.mWasCanceled;
    }
}
