package p;

import android.animation.ValueAnimator;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.textfield.TextInputLayout;

/* JADX INFO: loaded from: classes.dex */
public final class jn implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ jn(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        int i = this.a;
        Object obj = this.b;
        switch (i) {
            case 0:
                float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                qf1 qf1Var = ((BottomSheetBehavior) obj).i;
                if (qf1Var != null) {
                    pf1 pf1Var = qf1Var.c;
                    if (pf1Var.j != fFloatValue) {
                        pf1Var.j = fFloatValue;
                        qf1Var.m = true;
                        qf1Var.invalidateSelf();
                    }
                }
                break;
            case 1:
                ((pb0) obj).c.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
                break;
            default:
                ((TextInputLayout) obj).P0.i(((Float) valueAnimator.getAnimatedValue()).floatValue());
                break;
        }
    }
}
