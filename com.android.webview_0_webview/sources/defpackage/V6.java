package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class V6 extends AnimatorListenerAdapter {
    public final /* synthetic */ Z6 a;

    public V6(Z6 z6) {
        this.a = z6;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        this.a.m();
        animator.removeListener(this);
    }
}
