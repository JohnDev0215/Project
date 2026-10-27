package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class X3 extends AnimatorListenerAdapter {
    public final /* synthetic */ ViewGroup a;
    public final /* synthetic */ View b;
    public final /* synthetic */ K3 c;

    public X3(LayoutInflaterFactory2C0974j4 layoutInflaterFactory2C0974j4, ViewGroup viewGroup, View view, K3 k3) {
        this.a = viewGroup;
        this.b = view;
        this.c = k3;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        this.a.endViewTransition(this.b);
        animator.removeListener(this);
        View view = this.c.Z;
        if (view != null) {
            view.setVisibility(8);
        }
    }
}
