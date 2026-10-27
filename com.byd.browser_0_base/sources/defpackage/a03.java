package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import org.chromium.chrome.browser.tasks.tab_management.TabGridDialogView;

/* JADX INFO: compiled from: TabGridDialogView.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a03 extends AnimatorListenerAdapter {
    public final /* synthetic */ TabGridDialogView e;

    public a03(TabGridDialogView tabGridDialogView) {
        this.e = tabGridDialogView;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        TabGridDialogView tabGridDialogView = this.e;
        tabGridDialogView.k.setVisibility(4);
        tabGridDialogView.v = null;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        TabGridDialogView tabGridDialogView = this.e;
        Animator animator2 = tabGridDialogView.v;
        if (animator2 != null) {
            animator2.end();
        }
        tabGridDialogView.v = tabGridDialogView.A;
    }
}
