package defpackage;

import android.view.animation.Animation;
import android.view.animation.Transformation;
import org.chromium.third_party.android.swiperefresh.SwipeRefreshLayout;

/* JADX INFO: compiled from: SwipeRefreshLayout.java */
/* JADX INFO: loaded from: classes3.dex */
public final class gx2 extends Animation {
    public final /* synthetic */ SwipeRefreshLayout e;

    public gx2(SwipeRefreshLayout swipeRefreshLayout) {
        this.e = swipeRefreshLayout;
    }

    @Override // android.view.animation.Animation
    public final void applyTransformation(float f, Transformation transformation) {
        this.e.setAnimationProgress(f);
    }
}
