package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class W3 extends AnimatorListenerAdapter {
    public final /* synthetic */ ViewGroup a;
    public final /* synthetic */ View b;
    public final /* synthetic */ K3 c;
    public final /* synthetic */ LayoutInflaterFactory2C0974j4 d;

    public W3(LayoutInflaterFactory2C0974j4 layoutInflaterFactory2C0974j4, ViewGroup viewGroup, View view, K3 k3) {
        this.d = layoutInflaterFactory2C0974j4;
        this.a = viewGroup;
        this.b = view;
        this.c = k3;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        this.a.endViewTransition(this.b);
        Animator animatorH = this.c.h();
        this.c.Z(null);
        if (animatorH == null || this.a.indexOfChild(this.b) >= 0) {
            return;
        }
        LayoutInflaterFactory2C0974j4 layoutInflaterFactory2C0974j4 = this.d;
        K3 k3 = this.c;
        layoutInflaterFactory2C0974j4.c0(k3, k3.u(), 0, 0, false);
    }
}
