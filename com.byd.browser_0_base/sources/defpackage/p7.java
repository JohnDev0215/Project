package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

/* JADX INFO: compiled from: AppMenuUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public final class p7 extends AnimatorListenerAdapter {
    public final /* synthetic */ View e;

    public p7(View view) {
        this.e = view;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        this.e.setAlpha(0.0f);
    }
}
