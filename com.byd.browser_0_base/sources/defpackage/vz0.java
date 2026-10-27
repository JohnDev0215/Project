package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import org.chromium.chrome.browser.infobar.InfoBarContainerView;

/* JADX INFO: compiled from: InfoBarContainerView.java */
/* JADX INFO: loaded from: classes.dex */
public final class vz0 extends AnimatorListenerAdapter {
    public final /* synthetic */ InfoBarContainerView e;

    public vz0(InfoBarContainerView infoBarContainerView) {
        this.e = infoBarContainerView;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        this.e.s = null;
    }
}
