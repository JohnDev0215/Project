package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.ViewGroup;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Ix extends AnimatorListenerAdapter {
    public final /* synthetic */ Kx a;

    public Ix(Kx kx) {
        this.a = kx;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        super.onAnimationEnd(animator);
        if (this.a.getParent() != null) {
            ((ViewGroup) this.a.getParent()).removeView(this.a);
        }
    }
}
