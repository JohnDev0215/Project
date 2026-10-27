package defpackage;

import android.view.animation.Animation;
import android.view.animation.Transformation;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/* JADX INFO: compiled from: SwipeRefreshLayout.java */
/* JADX INFO: loaded from: classes.dex */
public final class hx2 extends Animation {
    public final /* synthetic */ SwipeRefreshLayout e;

    public hx2(SwipeRefreshLayout swipeRefreshLayout) {
        this.e = swipeRefreshLayout;
    }

    @Override // android.view.animation.Animation
    public final void applyTransformation(float f, Transformation transformation) {
        this.e.setAnimationProgress(f);
    }
}
