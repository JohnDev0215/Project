package defpackage;

import android.animation.ValueAnimator;
import com.google.android.material.navigation.NavigationBarItemView;

/* JADX INFO: compiled from: NavigationBarItemView.java */
/* JADX INFO: loaded from: classes.dex */
public final class tk1 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ float e;
    public final /* synthetic */ NavigationBarItemView f;

    public tk1(NavigationBarItemView navigationBarItemView, float f) {
        this.f = navigationBarItemView;
        this.e = f;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        int[] iArr = NavigationBarItemView.G;
        this.f.a(fFloatValue, this.e);
    }
}
