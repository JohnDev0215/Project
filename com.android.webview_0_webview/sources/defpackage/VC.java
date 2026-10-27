package defpackage;

import android.animation.ValueAnimator;
import android.view.animation.LinearInterpolator;
import android.widget.Magnifier;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class VC implements InterfaceC0537bD {
    public WC a;
    public ValueAnimator b;
    public boolean c;
    public float d;
    public float e;
    public float f;
    public float g;
    public float h;
    public float i;

    public VC(WC wc) {
        this.a = wc;
        a();
        this.h = -1.0f;
        this.i = -1.0f;
    }

    public final void a() {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.b = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(100L);
        this.b.setInterpolator(new LinearInterpolator());
        this.b.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this) { // from class: UC
            public final VC a;

            {
                this.a = this;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                VC vc = this.a;
                float f = vc.f;
                vc.d = (valueAnimator.getAnimatedFraction() * (vc.h - f)) + f;
                float f2 = vc.g;
                float animatedFraction = (valueAnimator.getAnimatedFraction() * (vc.i - f2)) + f2;
                vc.e = animatedFraction;
                ((XC) vc.a).a(vc.d, animatedFraction);
            }
        });
    }

    public void b() {
        XC xc = (XC) this.a;
        Magnifier magnifier = xc.a;
        if (magnifier != null) {
            magnifier.dismiss();
            xc.a = null;
        }
        this.b.cancel();
        this.c = false;
    }
}
