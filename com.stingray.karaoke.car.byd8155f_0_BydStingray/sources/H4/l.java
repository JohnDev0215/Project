package H4;

import android.graphics.Path;
import android.graphics.RectF;
import h2.AbstractC1347d;
import x3.AbstractC2673a;

/* JADX INFO: loaded from: classes.dex */
public final class l implements m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f2810a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f2811b = 1.0f;
    public final boolean c = true;
    public final boolean d = true;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f2812e = true;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f2813f = true;

    public l(float f10) {
        this.f2810a = f10;
    }

    @Override // H4.u
    public final Path a(float f10, F4.c cVar) {
        float fR = AbstractC2673a.r(this.f2811b, 0.0f) * (f10 / 7.0f);
        float f11 = this.f2810a;
        float f12 = f11 * f10;
        float f13 = (f10 - (4 * fR)) * f11;
        Path path = new Path();
        RectF rectF = new RectF(0.0f, 0.0f, f10, f10);
        boolean z6 = this.c;
        float f14 = z6 ? f12 : 0.0f;
        float f15 = z6 ? f12 : 0.0f;
        boolean z10 = this.f2812e;
        float f16 = z10 ? f12 : 0.0f;
        float f17 = z10 ? f12 : 0.0f;
        boolean z11 = this.f2813f;
        float f18 = z11 ? f12 : 0.0f;
        float f19 = z11 ? f12 : 0.0f;
        boolean z12 = this.d;
        float f20 = z12 ? f12 : 0.0f;
        if (!z12) {
            f12 = 0.0f;
        }
        path.addRoundRect(rectF, new float[]{f14, f15, f16, f17, f18, f19, f20, f12}, Path.Direction.CW);
        Path path2 = new Path();
        float f21 = f10 - fR;
        RectF rectF2 = new RectF(fR, fR, f21, f21);
        float f22 = z6 ? f13 : 0.0f;
        float f23 = z6 ? f13 : 0.0f;
        float f24 = z10 ? f13 : 0.0f;
        float f25 = z10 ? f13 : 0.0f;
        float f26 = z11 ? f13 : 0.0f;
        float f27 = z11 ? f13 : 0.0f;
        float f28 = z12 ? f13 : 0.0f;
        if (!z12) {
            f13 = 0.0f;
        }
        path2.addRoundRect(rectF2, new float[]{f22, f23, f24, f25, f26, f27, f28, f13}, Path.Direction.CCW);
        Path path3 = new Path(path);
        path3.op(path2, Path.Op.DIFFERENCE);
        return path3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof l)) {
            return false;
        }
        l lVar = (l) obj;
        return Float.valueOf(this.f2810a).equals(Float.valueOf(lVar.f2810a)) && Float.valueOf(this.f2811b).equals(Float.valueOf(lVar.f2811b)) && this.c == lVar.c && this.d == lVar.d && this.f2812e == lVar.f2812e && this.f2813f == lVar.f2813f;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v2, types: [int] */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r3v1, types: [int] */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v3, types: [int] */
    /* JADX WARN: Type inference failed for: r3v5, types: [int] */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v7 */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference failed for: r3v9 */
    public final int hashCode() {
        int iB = AbstractC1347d.b(Float.hashCode(this.f2810a) * 31, 31, this.f2811b);
        boolean z6 = this.c;
        ?? r32 = z6;
        if (z6) {
            r32 = 1;
        }
        int i7 = (iB + r32) * 31;
        boolean z10 = this.d;
        ?? r33 = z10;
        if (z10) {
            r33 = 1;
        }
        int i10 = (i7 + r33) * 31;
        boolean z11 = this.f2812e;
        ?? r34 = z11;
        if (z11) {
            r34 = 1;
        }
        int i11 = (i10 + r34) * 31;
        boolean z12 = this.f2813f;
        return i11 + (z12 ? 1 : z12);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("RoundCorners(corner=");
        sb.append(this.f2810a);
        sb.append(", width=");
        sb.append(this.f2811b);
        sb.append(", topLeft=");
        sb.append(this.c);
        sb.append(", bottomLeft=");
        sb.append(this.d);
        sb.append(", topRight=");
        sb.append(this.f2812e);
        sb.append(", bottomRight=");
        return AbstractC1347d.i(sb, this.f2813f, ')');
    }
}
