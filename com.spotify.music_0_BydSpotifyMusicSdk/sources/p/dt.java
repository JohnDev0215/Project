package p;

import android.animation.ValueAnimator;

/* JADX INFO: loaded from: classes.dex */
public final class dt implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ et b;

    public /* synthetic */ dt(et etVar, int i) {
        this.a = i;
        this.b = etVar;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        int i = this.a;
        et etVar = this.b;
        switch (i) {
            case 0:
                etVar.c.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
                break;
            default:
                float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                etVar.c.setScaleX(fFloatValue);
                etVar.c.setScaleY(fFloatValue);
                break;
        }
    }
}
