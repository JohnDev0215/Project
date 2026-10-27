package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import org.chromium.chrome.browser.tasks.tab_management.TabGridDialogView;

/* JADX INFO: compiled from: TabGridDialogView.java */
/* JADX INFO: loaded from: classes2.dex */
public final class wz2 extends AnimatorListenerAdapter {
    public final /* synthetic */ TabGridDialogView e;

    public wz2(TabGridDialogView tabGridDialogView) {
        this.e = tabGridDialogView;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        View view = this.e.j;
        if (view == null) {
            return;
        }
        view.setAlpha(1.0f);
    }
}
