package defpackage;

import android.animation.ValueAnimator;

/* JADX INFO: renamed from: m5, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC1143m5 implements Runnable {
    public final /* synthetic */ C1363q5 s;

    public RunnableC1143m5(C1363q5 c1363q5) {
        this.s = c1363q5;
    }

    @Override // java.lang.Runnable
    public void run() {
        C1363q5 c1363q5 = this.s;
        int i = c1363q5.A;
        if (i == 1) {
            c1363q5.z.cancel();
        } else if (i != 2) {
            return;
        }
        c1363q5.A = 3;
        ValueAnimator valueAnimator = c1363q5.z;
        valueAnimator.setFloatValues(((Float) valueAnimator.getAnimatedValue()).floatValue(), 0.0f);
        c1363q5.z.setDuration(500);
        c1363q5.z.start();
    }
}
