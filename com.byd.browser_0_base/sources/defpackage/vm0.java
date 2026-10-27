package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.ViewGroup;
import org.chromium.components.find_in_page.FindResultBar;

/* JADX INFO: compiled from: FindResultBar.java */
/* JADX INFO: loaded from: classes2.dex */
public final class vm0 extends AnimatorListenerAdapter {
    public final /* synthetic */ FindResultBar e;

    public vm0(FindResultBar findResultBar) {
        this.e = findResultBar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        super.onAnimationEnd(animator);
        FindResultBar findResultBar = this.e;
        if (findResultBar.getParent() != null) {
            ((ViewGroup) findResultBar.getParent()).removeView(findResultBar);
        }
    }
}
