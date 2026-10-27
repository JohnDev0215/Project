package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ZN extends AnimatorListenerAdapter {
    public final /* synthetic */ WindowAndroid a;

    public ZN(WindowAndroid windowAndroid) {
        this.a = windowAndroid;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        animator.removeListener(this);
        this.a.w.remove(animator);
        this.a.W();
    }
}
