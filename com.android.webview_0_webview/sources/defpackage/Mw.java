package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Mw extends AnimatorListenerAdapter {
    public final /* synthetic */ Qw a;

    public Mw(Qw qw) {
        this.a = qw;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        Qw qw = this.a;
        qw.e.announceForAccessibility(AbstractC1491sO.d(qw.b));
        qw.e.setImportantForAccessibility(1);
        qw.e.requestFocus();
    }
}
