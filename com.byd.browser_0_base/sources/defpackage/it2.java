package defpackage;

import android.animation.ValueAnimator;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class it2 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ qt2 f;

    public /* synthetic */ it2(qt2 qt2Var, int i) {
        this.e = i;
        this.f = qt2Var;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        int i = this.e;
        qt2 qt2Var = this.f;
        switch (i) {
            case 0:
                qt2Var.getClass();
                qt2Var.e.m(rt2.f, ((Float) valueAnimator.getAnimatedValue()).floatValue());
                break;
            default:
                qt2Var.getClass();
                int iIntValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                qt2Var.e.n(rt2.e, iIntValue);
                qt2Var.a(iIntValue);
                break;
        }
    }
}
