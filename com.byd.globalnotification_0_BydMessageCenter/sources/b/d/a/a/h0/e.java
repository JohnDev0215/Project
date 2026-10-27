package b.d.a.a.h0;

import android.animation.ValueAnimator;

/* JADX INFO: loaded from: classes.dex */
public class e implements ValueAnimator.AnimatorUpdateListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ a f1908a;

    public e(a aVar) {
        this.f1908a = aVar;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        this.f1908a.f1929c.setScaleX(fFloatValue);
        this.f1908a.f1929c.setScaleY(fFloatValue);
    }
}
