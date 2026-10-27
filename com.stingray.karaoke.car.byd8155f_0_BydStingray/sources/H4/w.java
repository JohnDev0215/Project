package H4;

import android.graphics.Path;
import android.graphics.RectF;
import x3.AbstractC2673a;

/* JADX INFO: loaded from: classes.dex */
public final class w implements u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f2824a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f2825b;
    public final boolean c;
    public final boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f2826e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f2827f;

    public w(float f10, boolean z6, boolean z10, boolean z11, boolean z12, boolean z13) {
        this.f2824a = f10;
        this.f2825b = z6;
        this.c = z10;
        this.d = z11;
        this.f2826e = z12;
        this.f2827f = z13;
    }

    @Override // H4.u
    public final Path a(float f10, F4.c cVar) {
        Path path = new Path();
        float f11 = 0.0f;
        float fU = AbstractC2673a.u(this.f2824a, 0.0f, 0.5f) * f10;
        RectF rectF = new RectF(0.0f, 0.0f, f10, f10);
        boolean z6 = this.f2825b;
        boolean z10 = cVar.c;
        boolean z11 = cVar.d;
        boolean z12 = this.c;
        float f12 = (!z12 || (z6 && (z11 || z10))) ? 0.0f : fU;
        float f13 = (!z12 || (z6 && (z11 || z10))) ? 0.0f : fU;
        boolean z13 = cVar.f2415e;
        boolean z14 = this.f2826e;
        float f14 = (!z14 || (z6 && (z11 || z13))) ? 0.0f : fU;
        float f15 = (!z14 || (z6 && (z11 || z13))) ? 0.0f : fU;
        boolean z15 = cVar.f2417g;
        boolean z16 = this.f2827f;
        float f16 = (!z16 || (z6 && (z15 || z13))) ? 0.0f : fU;
        float f17 = (!z16 || (z6 && (z15 || z13))) ? 0.0f : fU;
        boolean z17 = this.d;
        float f18 = (!z17 || (z6 && (z15 || z10))) ? 0.0f : fU;
        if (z17 && (!z6 || (!z15 && !z10))) {
            f11 = fU;
        }
        path.addRoundRect(rectF, new float[]{f12, f13, f14, f15, f16, f17, f18, f11}, Path.Direction.CW);
        return path;
    }
}
