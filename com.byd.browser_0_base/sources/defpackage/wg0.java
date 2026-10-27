package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.ui.tablet.emptybackground.EmptyBackgroundViewTablet;

/* JADX INFO: compiled from: EmptyBackgroundViewTablet.java */
/* JADX INFO: loaded from: classes2.dex */
public final class wg0 extends AnimatorListenerAdapter {
    public final /* synthetic */ EmptyBackgroundViewTablet e;

    public wg0(EmptyBackgroundViewTablet emptyBackgroundViewTablet) {
        this.e = emptyBackgroundViewTablet;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        EmptyBackgroundViewTablet emptyBackgroundViewTablet = this.e;
        emptyBackgroundViewTablet.setVisibility(8);
        emptyBackgroundViewTablet.g = null;
        emptyBackgroundViewTablet.j.setEnabled(true);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        EmptyBackgroundViewTablet emptyBackgroundViewTablet = this.e;
        emptyBackgroundViewTablet.setVisibility(0);
        emptyBackgroundViewTablet.getRootView().findViewById(R$id.control_container).setVisibility(0);
        emptyBackgroundViewTablet.j.setEnabled(false);
    }
}
