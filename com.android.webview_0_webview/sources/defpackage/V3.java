package defpackage;

import android.view.ViewGroup;
import android.view.animation.Animation;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class V3 extends AbstractAnimationAnimationListenerC0472a4 {
    public final /* synthetic */ ViewGroup b;
    public final /* synthetic */ K3 c;
    public final /* synthetic */ LayoutInflaterFactory2C0974j4 d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public V3(LayoutInflaterFactory2C0974j4 layoutInflaterFactory2C0974j4, Animation.AnimationListener animationListener, ViewGroup viewGroup, K3 k3) {
        super(animationListener);
        this.d = layoutInflaterFactory2C0974j4;
        this.b = viewGroup;
        this.c = k3;
    }

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationEnd(Animation animation) {
        Animation.AnimationListener animationListener = this.a;
        if (animationListener != null) {
            animationListener.onAnimationEnd(animation);
        }
        this.b.post(new U3(this));
    }
}
