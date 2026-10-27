package y;

import android.content.Context;
import android.widget.EdgeEffect;
import g1.AbstractC1274a;

/* JADX INFO: loaded from: classes.dex */
public final class Q extends EdgeEffect {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f23862a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f23863b;

    public Q(Context context) {
        super(context);
        this.f23862a = AbstractC1274a.f(context).l * 1;
    }

    @Override // android.widget.EdgeEffect
    public final void onAbsorb(int i7) {
        this.f23863b = 0.0f;
        super.onAbsorb(i7);
    }

    @Override // android.widget.EdgeEffect
    public final void onPull(float f10, float f11) {
        this.f23863b = 0.0f;
        super.onPull(f10, f11);
    }

    @Override // android.widget.EdgeEffect
    public final void onRelease() {
        this.f23863b = 0.0f;
        super.onRelease();
    }

    @Override // android.widget.EdgeEffect
    public final void onPull(float f10) {
        this.f23863b = 0.0f;
        super.onPull(f10);
    }
}
