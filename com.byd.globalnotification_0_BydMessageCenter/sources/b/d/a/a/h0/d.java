package b.d.a.a.h0;

import android.animation.ValueAnimator;

/* JADX INFO: loaded from: classes.dex */
public class d implements ValueAnimator.AnimatorUpdateListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ a f1907a;

    public d(a aVar) {
        this.f1907a = aVar;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.f1907a.f1929c.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
    }
}
