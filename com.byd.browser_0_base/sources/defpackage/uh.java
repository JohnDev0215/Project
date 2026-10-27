package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import com.google.android.material.bottomappbar.BottomAppBar;

/* JADX INFO: compiled from: BottomAppBar.java */
/* JADX INFO: loaded from: classes.dex */
public final class uh extends AnimatorListenerAdapter {
    public final /* synthetic */ BottomAppBar e;

    public uh(BottomAppBar bottomAppBar) {
        this.e = bottomAppBar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        int i = BottomAppBar.v0;
        BottomAppBar bottomAppBar = this.e;
        bottomAppBar.getClass();
        bottomAppBar.e0 = null;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        int i = BottomAppBar.v0;
        this.e.getClass();
    }
}
