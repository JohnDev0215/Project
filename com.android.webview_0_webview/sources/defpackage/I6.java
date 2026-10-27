package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class I6 extends AnimatorListenerAdapter {
    public final View a;
    public boolean b = false;

    public I6(View view) {
        this.a = view;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        AbstractC1419r7.a.e(this.a, 1.0f);
        if (this.b) {
            this.a.setLayerType(0, null);
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
        if (AbstractC1251o3.s(this.a) && this.a.getLayerType() == 0) {
            this.b = true;
            this.a.setLayerType(2, null);
        }
    }
}
