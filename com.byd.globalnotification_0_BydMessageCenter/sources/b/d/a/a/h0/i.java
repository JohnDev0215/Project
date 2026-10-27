package b.d.a.a.h0;

import android.animation.ValueAnimator;

/* JADX INFO: loaded from: classes.dex */
public class i implements ValueAnimator.AnimatorUpdateListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ h f1922a;

    public i(h hVar) {
        this.f1922a = hVar;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.f1922a.f1929c.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
    }
}
