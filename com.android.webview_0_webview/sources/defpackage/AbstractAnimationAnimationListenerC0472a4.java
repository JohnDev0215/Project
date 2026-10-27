package defpackage;

import android.view.animation.Animation;

/* JADX INFO: renamed from: a4, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractAnimationAnimationListenerC0472a4 implements Animation.AnimationListener {
    public final Animation.AnimationListener a;

    public AbstractAnimationAnimationListenerC0472a4(Animation.AnimationListener animationListener) {
        this.a = animationListener;
    }

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationRepeat(Animation animation) {
        Animation.AnimationListener animationListener = this.a;
        if (animationListener != null) {
            animationListener.onAnimationRepeat(animation);
        }
    }

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationStart(Animation animation) {
        Animation.AnimationListener animationListener = this.a;
        if (animationListener != null) {
            animationListener.onAnimationStart(animation);
        }
    }
}
