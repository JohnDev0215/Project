package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior;

/* JADX INFO: compiled from: HideBottomViewOnScrollBehavior.java */
/* JADX INFO: loaded from: classes.dex */
public final class lu0 extends AnimatorListenerAdapter {
    public final /* synthetic */ HideBottomViewOnScrollBehavior e;

    public lu0(HideBottomViewOnScrollBehavior hideBottomViewOnScrollBehavior) {
        this.e = hideBottomViewOnScrollBehavior;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        this.e.e = null;
    }
}
