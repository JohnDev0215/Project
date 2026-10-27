package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/* JADX INFO: compiled from: BottomAppBar.java */
/* JADX INFO: loaded from: classes.dex */
public final class xh extends AnimatorListenerAdapter {
    public final /* synthetic */ BottomAppBar e;

    public xh(BottomAppBar bottomAppBar) {
        this.e = bottomAppBar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        BottomAppBar bottomAppBar = this.e;
        bottomAppBar.t0.onAnimationStart(animator);
        FloatingActionButton floatingActionButtonC = bottomAppBar.C();
        if (floatingActionButtonC != null) {
            floatingActionButtonC.setTranslationX(bottomAppBar.getFabTranslationX());
        }
    }
}
