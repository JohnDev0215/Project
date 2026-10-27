package H4;

import android.graphics.Path;
import h2.AbstractC1347d;

/* JADX INFO: loaded from: classes.dex */
public final class d implements e, u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f2800a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f2801b = true;
    public final boolean c = true;
    public final boolean d = true;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f2802e = true;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ w f2803f;

    public d(float f10) {
        this.f2800a = f10;
        this.f2803f = new w(f10, false, true, true, true, true);
    }

    @Override // H4.u
    public final Path a(float f10, F4.c cVar) {
        return this.f2803f.a(f10, F4.c.f2412i);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        return Float.valueOf(this.f2800a).equals(Float.valueOf(dVar.f2800a)) && this.f2801b == dVar.f2801b && this.c == dVar.c && this.d == dVar.d && this.f2802e == dVar.f2802e;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [int] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r2v1, types: [int] */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v3, types: [int] */
    /* JADX WARN: Type inference failed for: r2v5, types: [int] */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r2v9 */
    public final int hashCode() {
        int iHashCode = Float.hashCode(this.f2800a) * 31;
        boolean z6 = this.f2801b;
        ?? r22 = z6;
        if (z6) {
            r22 = 1;
        }
        int i7 = (iHashCode + r22) * 31;
        boolean z10 = this.c;
        ?? r23 = z10;
        if (z10) {
            r23 = 1;
        }
        int i10 = (i7 + r23) * 31;
        boolean z11 = this.d;
        ?? r24 = z11;
        if (z11) {
            r24 = 1;
        }
        int i11 = (i10 + r24) * 31;
        boolean z12 = this.f2802e;
        return i11 + (z12 ? 1 : z12);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("RoundCorners(radius=");
        sb.append(this.f2800a);
        sb.append(", topLeft=");
        sb.append(this.f2801b);
        sb.append(", bottomLeft=");
        sb.append(this.c);
        sb.append(", topRight=");
        sb.append(this.d);
        sb.append(", bottomRight=");
        return AbstractC1347d.i(sb, this.f2802e, ')');
    }
}
