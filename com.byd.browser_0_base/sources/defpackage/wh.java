package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import androidx.appcompat.widget.ActionMenuView;
import com.google.android.material.bottomappbar.BottomAppBar;

/* JADX INFO: compiled from: BottomAppBar.java */
/* JADX INFO: loaded from: classes.dex */
public final class wh extends AnimatorListenerAdapter {
    public boolean e;
    public final /* synthetic */ ActionMenuView f;
    public final /* synthetic */ int g;
    public final /* synthetic */ boolean h;
    public final /* synthetic */ BottomAppBar i;

    public wh(BottomAppBar bottomAppBar, ActionMenuView actionMenuView, int i, boolean z) {
        this.i = bottomAppBar;
        this.f = actionMenuView;
        this.g = i;
        this.h = z;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        this.e = true;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        if (this.e) {
            return;
        }
        BottomAppBar bottomAppBar = this.i;
        int i = bottomAppBar.m0;
        boolean z = i != 0;
        if (i != 0) {
            bottomAppBar.m0 = 0;
            bottomAppBar.getMenu().clear();
            bottomAppBar.o(i);
        }
        bottomAppBar.K(this.f, this.g, this.h, z);
    }
}
