package org.libpag;

import android.animation.ValueAnimator;

/* JADX INFO: loaded from: classes3.dex */
public class n implements ValueAnimator.AnimatorUpdateListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ PAGView f4082a;

    public n(PAGView pAGView) {
        this.f4082a = pAGView;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.f4082a.w = valueAnimator.getCurrentPlayTime();
        PAGView.i(this.f4082a);
    }
}
