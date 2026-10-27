package a5;

import android.animation.ValueAnimator;

/* JADX INFO: loaded from: classes.dex */
public final class g implements ValueAnimator.AnimatorUpdateListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f11146a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ h f11147b;

    public /* synthetic */ g(h hVar, int i7) {
        this.f11146a = i7;
        this.f11147b = hVar;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        switch (this.f11146a) {
            case 0:
                this.f11147b.c.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
                break;
            default:
                float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                h hVar = this.f11147b;
                hVar.c.setScaleX(fFloatValue);
                hVar.c.setScaleY(fFloatValue);
                break;
        }
    }
}
