package androidx.recyclerview.widget;

import android.animation.ValueAnimator;
import androidx.recyclerview.widget.l;

/* JADX INFO: compiled from: ItemTouchHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class n implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ l.f e;

    public n(l.f fVar) {
        this.e = fVar;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.e.q = valueAnimator.getAnimatedFraction();
    }
}
