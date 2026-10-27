package defpackage;

import android.animation.ValueAnimator;

/* JADX INFO: renamed from: p5, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1308p5 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ C1363q5 a;

    public C1308p5(C1363q5 c1363q5) {
        this.a = c1363q5;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        int iFloatValue = (int) (((Float) valueAnimator.getAnimatedValue()).floatValue() * 255.0f);
        this.a.c.setAlpha(iFloatValue);
        this.a.d.setAlpha(iFloatValue);
        this.a.s.invalidate();
    }
}
