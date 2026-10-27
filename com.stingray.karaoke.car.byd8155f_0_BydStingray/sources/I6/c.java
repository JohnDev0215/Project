package I6;

import A7.m;
import android.animation.ValueAnimator;
import android.media.SoundPool;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class c implements ValueAnimator.AnimatorUpdateListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ SoundPool f3033a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f3034b;

    public /* synthetic */ c(SoundPool soundPool, int i7) {
        this.f3033a = soundPool;
        this.f3034b = i7;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        m.f(valueAnimator, "animation");
        Object animatedValue = valueAnimator.getAnimatedValue();
        m.d(animatedValue, "null cannot be cast to non-null type kotlin.Float");
        float fFloatValue = ((Float) animatedValue).floatValue();
        SoundPool soundPool = this.f3033a;
        if (soundPool != null) {
            soundPool.setVolume(this.f3034b, fFloatValue, fFloatValue);
        }
    }
}
