package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

/* JADX INFO: compiled from: ShrinkTransition.java */
/* JADX INFO: loaded from: classes2.dex */
public final class am2 extends AnimatorListenerAdapter {
    public final /* synthetic */ View e;

    public am2(View view) {
        this.e = view;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        View view = this.e;
        view.setScaleX(1.0f);
        view.setScaleY(1.0f);
        super.onAnimationEnd(animator);
    }
}
