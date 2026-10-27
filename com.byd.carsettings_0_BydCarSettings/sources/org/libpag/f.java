package org.libpag;

import android.animation.ValueAnimator;

/* JADX INFO: loaded from: classes3.dex */
public class f implements ValueAnimator.AnimatorUpdateListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ PAGImageView f4059a;

    public f(PAGImageView pAGImageView) {
        this.f4059a = pAGImageView;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        if (!this.f4059a.u.a() || this.f4059a.s == null) {
            return;
        }
        this.f4059a.z = valueAnimator.getCurrentPlayTime();
        l.a(this.f4059a);
    }
}
