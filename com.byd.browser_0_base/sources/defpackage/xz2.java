package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import org.chromium.chrome.browser.tasks.tab_management.TabGridDialogView;

/* JADX INFO: compiled from: TabGridDialogView.java */
/* JADX INFO: loaded from: classes2.dex */
public final class xz2 extends AnimatorListenerAdapter {
    public final /* synthetic */ float e;
    public final /* synthetic */ TabGridDialogView f;

    public xz2(TabGridDialogView tabGridDialogView, float f) {
        this.f = tabGridDialogView;
        this.e = f;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        TabGridDialogView tabGridDialogView = this.f;
        View view = tabGridDialogView.j;
        if (view != null) {
            view.setAlpha(1.0f);
        }
        tabGridDialogView.o.setY(this.e);
    }
}
