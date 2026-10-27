package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

/* JADX INFO: compiled from: FocusAnimator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class do0 extends AnimatorListenerAdapter {
    public final /* synthetic */ View e;
    public final /* synthetic */ int f;

    public do0(View view, int i) {
        this.e = view;
        this.f = i;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        View view = this.e;
        view.setTranslationY(0.0f);
        view.setBottom(view.getTop() + this.f);
    }
}
