package defpackage;

import android.animation.ValueAnimator;
import com.google.android.material.appbar.CollapsingToolbarLayout;

/* JADX INFO: compiled from: CollapsingToolbarLayout.java */
/* JADX INFO: loaded from: classes.dex */
public final class pu implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ CollapsingToolbarLayout e;

    public pu(CollapsingToolbarLayout collapsingToolbarLayout) {
        this.e = collapsingToolbarLayout;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.e.setScrimAlpha(((Integer) valueAnimator.getAnimatedValue()).intValue());
    }
}
