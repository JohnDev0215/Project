package defpackage;

import android.animation.ValueAnimator;
import defpackage.ft2;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class ht2 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ ht2(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        int i = this.e;
        Object obj = this.f;
        switch (i) {
            case 0:
                for (ft2.a aVar : ((qt2) obj).g) {
                    ((Integer) valueAnimator.getAnimatedValue()).intValue();
                    aVar.a();
                }
                break;
            case 1:
                qt2 qt2Var = (qt2) obj;
                qt2Var.getClass();
                int iIntValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                qt2Var.e.n(rt2.e, iIntValue);
                qt2Var.a(iIntValue);
                break;
            default:
                zt ztVar = (zt) obj;
                ztVar.getClass();
                ztVar.d.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
                break;
        }
    }
}
