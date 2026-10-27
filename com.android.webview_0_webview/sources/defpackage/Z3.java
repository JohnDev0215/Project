package defpackage;

import android.os.Build;
import android.view.View;
import android.view.animation.Animation;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Z3 extends AbstractAnimationAnimationListenerC0472a4 {
    public View b;

    public Z3(View view, Animation.AnimationListener animationListener) {
        super(animationListener);
        this.b = view;
    }

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationEnd(Animation animation) {
        if (AbstractC1251o3.t(this.b) || Build.VERSION.SDK_INT >= 24) {
            this.b.post(new Y3(this));
        } else {
            this.b.setLayerType(0, null);
        }
        Animation.AnimationListener animationListener = this.a;
        if (animationListener != null) {
            animationListener.onAnimationEnd(animation);
        }
    }
}
