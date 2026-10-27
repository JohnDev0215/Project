package defpackage;

import android.view.animation.Animation;
import android.view.animation.Transformation;
import org.chromium.third_party.android.swiperefresh.SwipeRefreshLayout;

/* JADX INFO: compiled from: SwipeRefreshLayout.java */
/* JADX INFO: loaded from: classes3.dex */
public final class lx2 extends Animation {
    public final /* synthetic */ SwipeRefreshLayout e;

    public lx2(SwipeRefreshLayout swipeRefreshLayout) {
        this.e = swipeRefreshLayout;
    }

    @Override // android.view.animation.Animation
    public final void applyTransformation(float f, Transformation transformation) {
        SwipeRefreshLayout swipeRefreshLayout = this.e;
        float f2 = swipeRefreshLayout.r;
        swipeRefreshLayout.setAnimationProgress(((-f2) * f) + f2);
        swipeRefreshLayout.g((swipeRefreshLayout.q + ((int) ((swipeRefreshLayout.s - r3) * f))) - swipeRefreshLayout.o.getTop());
    }
}
