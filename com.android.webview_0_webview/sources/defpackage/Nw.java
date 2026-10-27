package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Nw extends AnimatorListenerAdapter {
    public final /* synthetic */ View a;
    public final /* synthetic */ Qw b;

    public Nw(Qw qw, View view) {
        this.b = qw;
        this.a = view;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        this.b.d.setVisibility(8);
        this.b.d.removeView(this.a);
    }
}
