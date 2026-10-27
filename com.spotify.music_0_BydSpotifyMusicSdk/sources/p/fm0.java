package p;

import android.animation.ValueAnimator;

/* JADX INFO: loaded from: classes.dex */
public final class fm0 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ gm0 a;

    public fm0(gm0 gm0Var) {
        this.a = gm0Var;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        int iFloatValue = (int) (((Float) valueAnimator.getAnimatedValue()).floatValue() * 255.0f);
        gm0 gm0Var = this.a;
        gm0Var.c.setAlpha(iFloatValue);
        gm0Var.d.setAlpha(iFloatValue);
        gm0Var.s.invalidate();
    }
}
