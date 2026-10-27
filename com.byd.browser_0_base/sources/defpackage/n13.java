package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import org.chromium.components.browser_ui.modaldialog.ModalDialogView;
import org.chromium.components.browser_ui.modaldialog.TabModalPresenter;

/* JADX INFO: compiled from: TabModalPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class n13 extends AnimatorListenerAdapter {
    public final /* synthetic */ View e;
    public final /* synthetic */ TabModalPresenter f;

    public n13(TabModalPresenter tabModalPresenter, ModalDialogView modalDialogView) {
        this.f = tabModalPresenter;
        this.e = modalDialogView;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        TabModalPresenter tabModalPresenter = this.f;
        tabModalPresenter.h.setVisibility(8);
        tabModalPresenter.h.removeView(this.e);
    }
}
