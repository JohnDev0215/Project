package defpackage;

import android.view.animation.Animation;
import android.view.animation.Transformation;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/* JADX INFO: compiled from: SwipeRefreshLayout.java */
/* JADX INFO: loaded from: classes.dex */
public final class jx2 extends Animation {
    public final /* synthetic */ SwipeRefreshLayout e;

    public jx2(SwipeRefreshLayout swipeRefreshLayout) {
        this.e = swipeRefreshLayout;
    }

    @Override // android.view.animation.Animation
    public final void applyTransformation(float f, Transformation transformation) {
        this.e.setAnimationProgress(1.0f - f);
    }
}
