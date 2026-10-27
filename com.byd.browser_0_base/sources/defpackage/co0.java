package defpackage;

import android.animation.ValueAnimator;
import android.view.View;

/* JADX INFO: compiled from: FocusAnimator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class co0 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ View e;
    public final /* synthetic */ int f;
    public final /* synthetic */ int g;
    public final /* synthetic */ int h;

    public co0(View view, int i, int i2, int i3) {
        this.e = view;
        this.f = i;
        this.g = i2;
        this.h = i3;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        float f = 1.0f - fFloatValue;
        View view = this.e;
        view.setTranslationY(this.f * f);
        int i = this.g;
        int i2 = this.h;
        if (i != i2) {
            view.setBottom(view.getTop() + ((int) ((i2 * fFloatValue) + (i * f))));
        }
    }
}
