package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* JADX INFO: renamed from: o5, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1253o5 extends AnimatorListenerAdapter {
    public boolean a = false;
    public final /* synthetic */ C1363q5 b;

    public C1253o5(C1363q5 c1363q5) {
        this.b = c1363q5;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animator) {
        this.a = true;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        if (this.a) {
            this.a = false;
            return;
        }
        if (((Float) this.b.z.getAnimatedValue()).floatValue() == 0.0f) {
            C1363q5 c1363q5 = this.b;
            c1363q5.A = 0;
            c1363q5.f(0);
        } else {
            C1363q5 c1363q52 = this.b;
            c1363q52.A = 2;
            c1363q52.s.invalidate();
        }
    }
}
