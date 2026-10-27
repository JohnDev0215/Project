package F4;

import h2.AbstractC1347d;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final c f2412i = new c(false, false, false, false, false, false, false, false);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f2413a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f2414b;
    public final boolean c;
    public final boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f2415e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f2416f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f2417g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final boolean f2418h;

    public c(boolean z6, boolean z10, boolean z11, boolean z12, boolean z13, boolean z14, boolean z15, boolean z16) {
        this.f2413a = z6;
        this.f2414b = z10;
        this.c = z11;
        this.d = z12;
        this.f2415e = z13;
        this.f2416f = z14;
        this.f2417g = z15;
        this.f2418h = z16;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        return this.f2413a == cVar.f2413a && this.f2414b == cVar.f2414b && this.c == cVar.c && this.d == cVar.d && this.f2415e == cVar.f2415e && this.f2416f == cVar.f2416f && this.f2417g == cVar.f2417g && this.f2418h == cVar.f2418h;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r1v1, types: [int] */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r2v1, types: [int] */
    /* JADX WARN: Type inference failed for: r2v11, types: [int] */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v17 */
    /* JADX WARN: Type inference failed for: r2v18 */
    /* JADX WARN: Type inference failed for: r2v19 */
    /* JADX WARN: Type inference failed for: r2v20 */
    /* JADX WARN: Type inference failed for: r2v21 */
    /* JADX WARN: Type inference failed for: r2v22 */
    /* JADX WARN: Type inference failed for: r2v23 */
    /* JADX WARN: Type inference failed for: r2v3, types: [int] */
    /* JADX WARN: Type inference failed for: r2v5, types: [int] */
    /* JADX WARN: Type inference failed for: r2v7, types: [int] */
    /* JADX WARN: Type inference failed for: r2v9, types: [int] */
    public final int hashCode() {
        boolean z6 = this.f2413a;
        ?? r12 = z6;
        if (z6) {
            r12 = 1;
        }
        int i7 = r12 * 31;
        boolean z10 = this.f2414b;
        ?? r22 = z10;
        if (z10) {
            r22 = 1;
        }
        int i10 = (i7 + r22) * 31;
        boolean z11 = this.c;
        ?? r23 = z11;
        if (z11) {
            r23 = 1;
        }
        int i11 = (i10 + r23) * 31;
        boolean z12 = this.d;
        ?? r24 = z12;
        if (z12) {
            r24 = 1;
        }
        int i12 = (i11 + r24) * 31;
        boolean z13 = this.f2415e;
        ?? r25 = z13;
        if (z13) {
            r25 = 1;
        }
        int i13 = (i12 + r25) * 31;
        boolean z14 = this.f2416f;
        ?? r26 = z14;
        if (z14) {
            r26 = 1;
        }
        int i14 = (i13 + r26) * 31;
        boolean z15 = this.f2417g;
        ?? r27 = z15;
        if (z15) {
            r27 = 1;
        }
        int i15 = (i14 + r27) * 31;
        boolean z16 = this.f2418h;
        return i15 + (z16 ? 1 : z16);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Neighbors(topLeft=");
        sb.append(this.f2413a);
        sb.append(", topRight=");
        sb.append(this.f2414b);
        sb.append(", left=");
        sb.append(this.c);
        sb.append(", top=");
        sb.append(this.d);
        sb.append(", right=");
        sb.append(this.f2415e);
        sb.append(", bottomLeft=");
        sb.append(this.f2416f);
        sb.append(", bottom=");
        sb.append(this.f2417g);
        sb.append(", bottomRight=");
        return AbstractC1347d.i(sb, this.f2418h, ')');
    }
}
