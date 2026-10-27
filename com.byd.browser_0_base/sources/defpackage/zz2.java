package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import org.chromium.chrome.browser.tasks.tab_management.TabGridDialogView;

/* JADX INFO: compiled from: TabGridDialogView.java */
/* JADX INFO: loaded from: classes2.dex */
public final class zz2 extends AnimatorListenerAdapter {
    public final /* synthetic */ TabGridDialogView e;

    public zz2(TabGridDialogView tabGridDialogView) {
        this.e = tabGridDialogView;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        this.e.v = null;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        TabGridDialogView tabGridDialogView = this.e;
        Animator animator2 = tabGridDialogView.v;
        if (animator2 != null) {
            animator2.end();
        }
        tabGridDialogView.v = tabGridDialogView.z;
        tabGridDialogView.k.setVisibility(0);
        tabGridDialogView.k.setAlpha(0.0f);
    }
}
