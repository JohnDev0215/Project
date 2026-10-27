package defpackage;

import android.view.animation.Animation;
import android.view.animation.Transformation;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/* JADX INFO: compiled from: SwipeRefreshLayout.java */
/* JADX INFO: loaded from: classes.dex */
public final class kx2 extends Animation {
    public final /* synthetic */ int e;
    public final /* synthetic */ int f;
    public final /* synthetic */ SwipeRefreshLayout g;

    public kx2(SwipeRefreshLayout swipeRefreshLayout, int i, int i2) {
        this.g = swipeRefreshLayout;
        this.e = i;
        this.f = i2;
    }

    @Override // android.view.animation.Animation
    public final void applyTransformation(float f, Transformation transformation) {
        this.g.C.setAlpha((int) (((this.f - r0) * f) + this.e));
    }
}
