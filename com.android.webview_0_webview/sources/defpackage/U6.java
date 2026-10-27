package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class U6 extends AnimatorListenerAdapter {
    public final /* synthetic */ C0971j1 a;
    public final /* synthetic */ Z6 b;

    public U6(Z6 z6, C0971j1 c0971j1) {
        this.b = z6;
        this.a = c0971j1;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        this.a.remove(animator);
        this.b.O.remove(animator);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
        this.b.O.add(animator);
    }
}
